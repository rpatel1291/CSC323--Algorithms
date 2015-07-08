import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class LinkedList{
	private Node head;
	private int size;
	public LinkedList(){
		Node dummy = new Node("Dummy",0.0);
		head=dummy;
		size=0;
	}//LinkedList
	public int size(){
		return size;
	}//size
	public boolean isEmpty(){
		return size==0;
	}//isEmpty
	public void makeEmpty(){
		head=null;
		size=0;
	}//makeEmpty
	public void insert(String letters, double frequency){
		Node newNode = new Node(letters,frequency);
		Node walker, pointer;
		//if empty new node is head & tail
		if(isEmpty()){
			head.setNext(newNode);
			size++;
		}//if list is empty
		else{
			walker=head; pointer=walker.getNext();
			while(pointer!=null){
				if(frequency <= pointer.getFrequency()){
					break;
				}
				walker=pointer;
				pointer=pointer.getNext();
			}
			newNode.setNext(pointer);
			walker.setNext(newNode);
			size++;
		}
	}//insert	
	public static Node BinaryTreeConstruction(LinkedList list){
		Node LH = list.head;
		Node OLH = new Node("Dummy",0.0);
		OLH.setNext(LH.getNext());
		//String result="";
		while(LH.getNext().getNext()!=null){
			Node before = LH.getNext();
			Node after = before.getNext();
			Node newNode = new Node();
			newNode.setLetters(before.getLetters()+after.getLetters());
			newNode.setFrequency(before.getFrequency()+after.getFrequency());
			newNode.setLeft(before);
			newNode.setRight(after);
			Node walker = OLH;
			Node pointer = before.getNext();
			while(pointer!=null){
				if(newNode.getFrequency() <= pointer.getFrequency()){
					break;
				}
				walker=pointer;
				pointer=pointer.getNext();
			}
			newNode.setNext(pointer);
			walker.setNext(newNode);
			LH=before.getNext();
			/*
			for(Node w=LH; w!=null;w=w.getNext()){
				//System.out.println(w.getLetters()+" "+w.getFrequency()+" ");
				result+=" "+ w.getLetters() +" "+w.getFrequency()+"->";
			}
			//System.out.println();
			 * 
			 */
			//result+="\n";
		}
		Node root=LH.getNext();
		LH=OLH;
		return root;
	}
	public static String print(LinkedList list){
		String result="";
		for(Node walker=list.head; walker!=null; walker=walker.getNext()){
			if(walker.getLetters()=="Dummy"){
                result+="listHead -->("+walker.getLetters()+", "+walker.getFrequency()+", "+walker.getNext().getLetters()+")-->";
            }
            result+="("+walker.getLetters()+", "+walker.getFrequency();
            if(walker.getNext()!=null){
                result+=", "+walker.getNext().getLetters()+")-->";
            }
            else{
                result+=")-->NULL";
            }
		}//for
		return result;
	}//print
	public static void HuffmanCode(Node root, String code){
		if(root == null) return;
		if(root.getLeft()==null && root.getRight() ==null){
			root.setCode(code);
			return;
		}
		HuffmanCode(root.getLeft(), code+"0");
		HuffmanCode(root.getRight(), code+"1");
	}//HuffmanCode
	public static String EntropyTable(LinkedList list){
		int size = list.size();
		String[] character = new String[size];
		double[] char_freq = new double[size];
		String[] char_code = new String[size];
		int[] bits = new int[size];
		double[] entrophy = new double[size];
		int i = 0;
		for(Node walker = list.head.getNext(); walker!=null ; walker=walker.getNext()){
			if(walker.getCode() != null){
				character[i] = walker.getLetters();
				char_freq[i] = walker.getFrequency();
				char_code[i] = walker.getCode();
				bits[i] = walker.getCode().length();
				entrophy[i] = char_freq[i] * bits[i];
				i++;
			}
		}
		String result = "";
		result+="Char "+"\t"+"Prob "+"\t"+"Code "+"\t"+"#Bits "+"\t"+"Entrophy"+"\n";
		result +="---------------------------------------------------"+"\n";
		for(int j=0; j<size; j++){
			result += character[j]+"\t"+char_freq[j]+"\t"+char_code[j]+"\t"+
		bits[j]+"\t"+entrophy[j]+"\n";
		}
		return result;
	}
	public static String encoding(LinkedList list, char bit){
		if(bit == ' '){
			bit = '|';
		}
		else if(bit == '\r'){
			bit = '*';
		}
		else if(bit == '\n'){
			bit = '^';
		}
		String letters=""+bit;
		String result="";
		for(Node walker=list.head;walker!=null;walker=walker.getNext()){
			if(walker.getLetters().equals(letters)){
				result =walker.getCode();
			}
		}
		return result;
	}
	public static String decoding(Node root, FileReader input) throws IOException{
		String result="";
		String digit="";
		int read;
		Node walker=root;
			while((read = input.read())!=-1){
				digit = ""+(char)read;
				if(walker.getLeft() == null && walker.getRight() == null){
					if(walker.getLetters().equals("*")) result +="\r";
					else if(walker.getLetters().equals("|")) result +=" ";
					else if(walker.getLetters().equals("^")) result +="\n";
					else result += walker.getLetters();
					walker=root;
				}
				if(digit.equals("0")){
					walker=walker.getLeft();
				}
				if(digit.equals("1")){
					walker=walker.getRight();
				}
			}
		return result;
	}
}//LinkedList Class
