//
//  Dijkstra.cpp
//  
//
//  Created by Ravi Patel on 4/19/15.
//
//

#include <iostream>
#include <fstream>
using namespace std;


int main(int argc, char* argv[]){
    ifstream inputFile;
    inputFile.open(argv[1]);
    ofstream outputFile;
    outputFile.open(argv[2]);
    int nodes, from, to, weight;
    inputFile>>nodes;
    int parent[nodes];
    int marked[nodes];
    int bestCost[nodes];
    int costMatrix[nodes][nodes];
    while(inputFile>>from && inputFile>>to && inputFile>>weight){
        costMatrix[from][to]=weight;
    }
    for(int row = 0; row < nodes; row++){
        for(int col=0; col < nodes; col++){
            outputFile<<costMatrix[row][col]<<" ";
        }
        outputFile<<endl;
    }
    int i=1;
    while(i <= nodes+1){
        int minNode=i;
        marked[minNode]=1;
        bool finish=false;
        while(finish == false){
        
            int newNode;
            for(int row=0; row <=nodes; row++ ){
                if(marked[row]!=1){
                    break;
                }
                newNode=marked[row];
            }
            int tempCost= bestCost[minNode] + costMatrix[minNode][newNode];
            if(tempCost < bestCost[newNode]){
                bestCost[newNode]=tempCost;
                parent[newNode]= minNode;
            }
            finish=true;
        }
        i++;
    return 0;
    }
}
