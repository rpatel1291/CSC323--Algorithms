import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		 if(args.length == 0) System.out.println("No File Specified");
		 else{
			 Scanner inputFile = new Scanner(args[0]);
			 PrintWriter outputFile = new PrintWriter(args[1]);
			 try{
				
			    int NumberOfNodes, from, to;
			    NumberOfNodes=inputFile.nextInt();
			    NumberOfNodes++;
			    int matrix[][] = new int[NumberOfNodes][NumberOfNodes];
			    int nodeColor[] = new int[NumberOfNodes];
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
			    while(inputFile.hasNext()){
			    	from=inputFile.nextInt();
			    	to=inputFile.nextInt();
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
			        outputFile.println("New Color: "+newColor);
			        outputFile.println("Node Color Array: ");
			        for(int row=0; row < NumberOfNodes; row++){
			        	for(int col =0; col <NumberOfNodes; col++){
			        		outputFile.print(nodeColor[row]);
			        	}
			        }
			    }
			    for(int row=0; row<NumberOfNodes; row++){
			        for(int col=0; col<NumberOfNodes; col++){
			            if(row == col){
			                matrix[row][col]=nodeColor[row];
			            }
			            outputFile.println(matrix[row][col]+ " ");
			        }
			        outputFile.println();
			    }
			 }
			 finally{
				outputFile.close();
				inputFile.close();
			 }
		 } 


	}

	private static boolean checkColor(int[] nodeColor, int numberOfNodes) {
		for (int row=1; row < numberOfNodes ; row++) {
	        if(nodeColor[row]==0){
	            return true;
	        }
	    }
	    return false;
	}

}
