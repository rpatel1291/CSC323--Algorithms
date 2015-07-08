import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws IOException{
		if(args.length==0) System.out.println("No File Specified");
		else{
			LinkedList list = new LinkedList();
			FileReader input1 = new FileReader(args[0]);
			PrintWriter output1 = new PrintWriter(args[1]);//output1 histogram
			PrintWriter output2 = new PrintWriter(args[2]);//output2 sorted linkedlist
			PrintWriter output3 = new PrintWriter(args[3]);//output3 binarytree
			PrintWriter output4 = new PrintWriter(args[4]);//output4 entropy
			FileReader input2 = new FileReader(args[5]);
			PrintWriter output5 = new PrintWriter(args[6]);//encoding
			PrintWriter output6 = new PrintWriter(args[7]);//decoding
			try{
				int count=0;
				char character;
				String letters;
				double frequency;
				int size=256;
				double histogram[]= new double[size];
				for(int start=0; start<256 ;start++){
					histogram[start]=0;
				}
				int read;
				while((read = input1.read())!=-1){
					for(int inital = -128; inital<128; inital++){
						if((char)inital == (char)read){
							histogram[inital+128]+=1;
							count++;
						}
					}
					//System.out.println((char)read);
				}
				for(int inital = 0; inital < size; inital++ ){
					histogram[inital] = (histogram[inital]/count)*100;
				}
				for(int inital = 0; inital < size; inital++){
			        if(histogram[inital]!=0){
			            character = (char)(inital-128);
			            if(character == ' ') character = '|';
			            else if(character == '\r') character = '*';
			            else if(character == '\n') character = '^';
			            output1.println(character +" "+histogram[inital]);
			        }
			    }
				output1.close();
				Scanner input3 = new Scanner(new FileReader(args[1]));
				while(input3.hasNext()){
					list.insert(input3.next(), input3.nextDouble());
					//output1.print("Iteration "+ counter++ +": \n");
					//output1.println(LinkedList.print(list));
				}
				output2.print(LinkedList.print(list));
				LinkedList.BinaryTreeConstruction(list);
				output3.print(LinkedList.print(list));
				LinkedList.HuffmanCode(LinkedList.BinaryTreeConstruction(list), "");
				output4.print(LinkedList.EntropyTable(list));
				while((read = input2.read())!= -1){
					output5.print(LinkedList.encoding(list, (char)read));
					
				}
				output5.close();
				FileReader input4 = new FileReader(args[6]);
				output6.print(LinkedList.decoding(LinkedList.BinaryTreeConstruction(list), input4));
//				while((read = input2.read())!=-1){
//					output5.println(LinkedList.encoding(list,(char)read));
//				}
//				while(input2.hasNext()){
//					character = input2.next().charAt(0);
//					output5.print(LinkedList.encoding(list, character));
//				}
//				output5.close();
//				Scanner input4 = new Scanner(new FileReader(args[6])).useDelimiter("'");
//				output6.print(LinkedList.decoding(LinkedList.BinaryTreeConstruction(list),input4));
//				
			}//try
			finally{
				input1.close();
				input2.close(); 
				output1.close();
				output2.close();
				output3.close();
				output4.close();
				output5.close();
				output6.close();
				
			}//finally
		}//else
	}//Main Method
}//Main Class
