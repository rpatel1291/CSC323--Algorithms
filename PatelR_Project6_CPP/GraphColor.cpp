#include <iostream>
#include <fstream>
using namespace std;

bool checkColor(int* nodeColor, int NumberOfNodes){
    for (int row=1; row < NumberOfNodes ; row++) {
        if(nodeColor[row]==0){
            return true;
        }
    }
    return false;
}
void printNodeColor(int* nodeColor, int NumberOfNodes, ostream& outputFile){
    for (int row=1; row < NumberOfNodes ; row++) {
        outputFile<<nodeColor[row];
    }
    outputFile<<endl;
}
int main( int argc , char* argv[]){
    ifstream inputFile;
    inputFile.open(argv[1]);
    ofstream outputFile;
    outputFile.open(argv[2]);
    int NumberOfNodes, from, to;
    inputFile>>NumberOfNodes;
    cout<<NumberOfNodes<<endl;
    int** matrix;
    int* nodeColor;
    NumberOfNodes++;
    matrix = new int* [NumberOfNodes];
    nodeColor = new int [NumberOfNodes];
    int newColor = 0;
    int newNode=1;
    for(int row=0; row<NumberOfNodes; row++){
        matrix[row] = new int[NumberOfNodes];
        for(int col=0; col<NumberOfNodes; col++){
            matrix[row][col]=0;
        }
    }
    for(int row=0; row < NumberOfNodes; row++){
        nodeColor[row]=0;
    }
    while(inputFile>>from && inputFile>>to){
        for (int row=0; row < NumberOfNodes; row++) {
            for(int col=0; col < NumberOfNodes; col++){
                if(row==from && col==to){
                    matrix[from][to]=1;
                }
            }
        }
    }
    while(checkColor(nodeColor, NumberOfNodes)){
        newColor++;
        for(int row=1; row < NumberOfNodes; row++){
            if(nodeColor[row]==0){
                newNode = row;
            }
            for (int col=1; col < NumberOfNodes; col++) {
                if (matrix[row][col] == 0 || matrix[row][col] == newColor ) {
                    break;
                }
                nodeColor[newNode] = newColor;
            }
        }
        outputFile<<"New Color: "<<newColor<<endl;
        outputFile<<"Node Color Array: ";
        printNodeColor(nodeColor, NumberOfNodes, outputFile);
    }
    for(int row=0; row<NumberOfNodes; row++){
        for(int col=0; col<NumberOfNodes; col++){
            if(row == col){
                matrix[row][col]=nodeColor[row];
            }
            outputFile<<matrix[row][col]<<" ";
        }
        outputFile<<endl;
    }
    
    
}