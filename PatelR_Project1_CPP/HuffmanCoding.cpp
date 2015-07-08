//
//  Huffman.cpp
//  Created by Ravi Patel on 2/12/15.
//
//
#include <iostream>
#include <sstream>
#include <cstring>
#include <fstream>
using namespace std;

class Node{
    friend class LinkedList;
private:
    string letters,code;
    double frequency;
    Node* left;
    Node* right;
    Node* next;
public:
    Node(){
        letters="Dummy";
        frequency=0;
        left=NULL;
        right=NULL;
        next=NULL;
        code="";
    }
    Node(string let , double freq){
        letters=let;
        frequency=freq;
        left=NULL;
        right=NULL;
        next=NULL;
        code="";
    }
    void setLetters(string let){
        this->letters=let;
    }
    void setFrequency(double freq){
        frequency=freq;
    }
    void setNext(Node *nt){
        next=nt;
    }
    void setLeft(Node* lt){
        left=lt;
    }
    void setRight(Node* rt){
        right=rt;
    }
    void setCode(string cd){
        code=cd;
    }
    string getLetters(){
        return letters;
    }
    double getFrequency(){
        return frequency;
    }
    Node* getLeft(){
        return left;
    }
    Node* getRight(){
        return right;
    }
    Node* getNext(){
        return next;
    }
    string getCode(){
        return code;
    }
};

class LinkedList{
private:
    Node *head;
    int size;
public:
    LinkedList(){
        head = new Node("dummy",0.0);
        size=0;
    }
    int getSize(){
        return size;
    }
    bool isEmpty(){
        return size==0;
    }
    void insert(string letters, double frequency){
        Node *newNode = new Node(letters,frequency);
        Node *walker;
        Node *pointer;
        if(isEmpty()){
            head->setNext(newNode);
            size++;
        }
        else{
            walker=head;
            pointer=walker->getNext();
            while (pointer!=NULL){
                if(frequency <= pointer->getFrequency()){
                    break;
                }
                walker=pointer;
                pointer=walker->getNext();
            }
            newNode->setNext(pointer);
            walker->setNext(newNode);
            size++;
        }
    }
    Node* BinaryTreeConstruction(){
        Node *LH = head;
        Node *OLH = new Node("Dummy",0.0);
        OLH->setNext(LH->getNext());
        while(LH->getNext()->getNext() != NULL){
            Node *before = LH->getNext();
            Node *after = before->getNext();
            Node *newNode = new Node();
            newNode->setLetters(before->getLetters()+after->getLetters());
            newNode->setFrequency(before->getFrequency()+after->getFrequency());
            newNode->setLeft(before);
            newNode->setRight(after);
            Node *walker = OLH;
            Node *pointer = before->getNext();
            while(pointer!=NULL){
                if(newNode->getFrequency() <= pointer->getFrequency()){
                    break;
                }
                walker=pointer;
                pointer=pointer->getNext();
            }
            newNode->setNext(pointer);
            walker->setNext(newNode);
            LH=before->getNext();
            /*
            for(Node *w=LH; w!=NULL;w=w->getNext()){
                output<<"|"<<w->getLetters()<<","<<w->getFrequency()<<"|->";
            }
            output<<endl;
            */
        }
        Node *root=LH->getNext();
        LH=OLH;
        return root;
    }
    void HuffmanCode(Node* root, string code){
        Node *walker = root;
        if(walker == NULL){
            return;
        }
        if(walker->getLeft() == NULL && walker->getRight() == NULL){
            walker->setCode(code);
            return;
        }
        HuffmanCode(walker->getLeft(), code+"0");
        HuffmanCode(walker->getRight(), code+"1");
    }
    void printList(ostream& output){
        Node *walker;
        for(walker=head; walker!=NULL; walker=walker->getNext() ){
            if(walker->getLetters()=="Dummy"){
                output<<"listHead -->("<<walker->getLetters()<<", "<<walker->getFrequency()<<", "<<walker->getNext()->getLetters()<<")-->";
            }
            output<<"("<<walker->getLetters()<<", "<<walker->getFrequency();
            if(walker->getNext()!=NULL){
                output<<", "<<walker->getNext()->getLetters()<<")-->";
            }
            else{
                output<<")-->NULL"<<endl;
            }
        }
        output<<endl;
    }
    void EntropyTable(ostream& output){
        int size=getSize();
        string *character = new string[size];
        double char_freq[size];
        string *char_code = new string[size];
        int bits[size];
        double entropy[size];
        int i=0;
        Node *walker;
        for(walker=head->getNext(); walker!=NULL;walker=walker->getNext()){
            if(walker->getCode() != ""){
                character[i] = walker->getLetters();
                char_freq[i] = walker->getFrequency();
                char_code[i] = walker->getCode();
                bits[i] = walker->getCode().size();
                entropy[i] = (char_freq[i] * bits[i]);
                i++;
            }
        }
        output<<"Char \t"<<"Prob \t"<<"Code \t \t"<<"#bits \t \t"<<"Entropy"<<endl;
        output<<"-----------------------------------------"<<endl;
        for(int j=0; j<size; j++){
            output<<character[j]<<"\t"<<char_freq[j]<<"\t"<<char_code[j]<<"\t \t"<<bits[j]<<"\t \t"<<entropy[j]<<endl;
        }
    }
    
    void encoding(char Frominput, ostream& output){
        Node *walker;
        stringstream ss;
        string letters;
        if(Frominput == ' '){
            Frominput = '|';
        }
        if(Frominput == '\r'){
            Frominput = '*';
        }
        if(Frominput == '\n'){
            Frominput = '^';
        }
        ss<<Frominput;
        ss>>letters;
        
        for(walker=head->getNext(); walker!=NULL; walker=walker->getNext()){
            if(walker->getCode()!=""){
                if(letters == walker->getLetters()){
                    output<<walker->getCode();
                }
            }
        }
    }
    void decoding(Node* root, istream& input, ostream& output){
        char digit;
        //start from bottom of tree
        Node *walker= root;
        //read in bit
        while (input >> digit){
            if(walker->getLeft()==NULL && walker->getRight()==NULL){
                if(walker->getLetters() =="*"){
                    output<<"\r";
                }
                else if(walker->getLetters() =="|"){
                    output<<" ";
                }
                else if(walker->getLetters() =="^"){
                    output<<endl;
                }
                else{
                    output<<walker->getLetters();
                }
                walker=root;
            }
            //move left if 0
            if(digit == '0'){
                walker = walker->getLeft();
            }
            //move right if 1
            if(digit == '1'){
                walker = walker->getRight();
            }
            //if bits lead to a leaf
            
        }
    }
    /*
    ~LinkedList(){
        Node* walker = head;
        while (walker != NULL) {
            cout<<walker->getLetters();
            head = head->getNext();
            delete walker;
            walker = head;
        }
        head = NULL;
    }
    */
};




int main(int argc, char* argv[]){
    LinkedList list;
    list = LinkedList();
    //input 1
    ifstream inputFile;
    inputFile.open(argv[1]);
    //histogram
    ofstream outputFile1;
    outputFile1.open(argv[2]);
    //sorted linked list
    ofstream outputFile2;
    outputFile2.open(argv[3]);
    //binary tree
    ofstream outputFile3;
    outputFile3.open(argv[4]);
    //entropy
    ofstream outputFile4;
    outputFile4.open(argv[5]);
    //input 2
    ifstream inputFile2;
    inputFile2.open(argv[6]);
    //encoding
    ofstream outputFile5;
    outputFile5.open(argv[7]);
   
    //decoding
    ofstream outputFile6;
    outputFile6.open(argv[8]);
    int count=0;
    char character;
    string letters;
    double frequency;
    int size=256;
    double histogram[size];
    for(int start=0; start<size; start++ ){
        histogram[start]=0;
    }
    inputFile>>noskipws;
    while(inputFile>>character){// && inputFile>>frequency){
        for (int inital = -128; inital < 128; inital++) {
            if((char)inital == character){
                histogram[inital+128] +=1;
                count++;
            }
        }
    }
    for (int inital = 0; inital < size; inital++){
        histogram[inital] = (histogram[inital]/count)*100;
    }
    for(int inital = 0; inital < size; inital++){
        if(histogram[inital]!=0){
            character = (char)(inital-128);
            if(character == ' ') character = '|';
            if(character == '\r') character = '*';
            if(character == '\n') character = '^';
           outputFile1<<character<<" "<<histogram[inital]<<endl;
        }
    }
    
    ifstream inputFile3;
    inputFile3.open(argv[2]);
    while(inputFile3>>character && inputFile3>>frequency){
        stringstream ss;
        ss<<character;
        ss>>letters;
        list.insert(letters, frequency);
    }
    list.printList(outputFile2);
    list.HuffmanCode(list.BinaryTreeConstruction(),"");
    list.printList(outputFile3);
    list.EntropyTable(outputFile4);
    inputFile2>>noskipws;
    while(inputFile2>>character){
        list.encoding(character, outputFile5);
    }
    outputFile5.close();
    ifstream inputFile4;
    inputFile4.open(argv[7]);
    list.decoding(list.BinaryTreeConstruction(), inputFile4 , outputFile6);
    return 0;
}

