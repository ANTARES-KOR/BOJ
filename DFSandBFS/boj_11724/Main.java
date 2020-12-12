package boj_11724;

import java.io.*;
import java.util.Arrays;

public class Main{
    
    static Boolean[][] map;
    static Boolean[] isVisited;
    static int numberOfNodes, numberOfEdges;
    
    
    static void getGraphMapAndInitialize() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");
        numberOfNodes = Integer.parseInt(input[0]);
        numberOfEdges = Integer.parseInt(input[1]);
        
        initialize();
        
        for(int i=1; i<=numberOfEdges; i++){
            input = br.readLine().split(" ");
            int node1 = Integer.parseInt(input[0]);
            int node2 = Integer.parseInt(input[1]);
            
            map[node1][node2] = map[node2][node1] = true;
        }
        br.close();
    }
    
    static void initialize(){
        map = new Boolean[numberOfNodes+1][numberOfNodes+1];
        isVisited = new Boolean[numberOfNodes+1];
        for(int i = 0; i <= numberOfNodes; i++){
            Arrays.fill(map[i], false);
        }
        Arrays.fill(isVisited, false);
    }
    
    static int getNumberOfConnectedComp(){
        
        int numberOfComponents = 0;
        
        for(int i = 1; i <= numberOfNodes; i++){
            if(!isVisited[i]){
                searchThroughConnectedComps(i);
                numberOfComponents++;
            }
        }
        
        return numberOfComponents;
    }
    
    static void searchThroughConnectedComps(int start){
        
        isVisited[start] = true;
        
        for(int destination=1; destination<= numberOfNodes; destination++){
            if(map[start][destination] && !isVisited[destination] ){
                searchThroughConnectedComps(destination);
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        
        getGraphMapAndInitialize();
        
        int answer = getNumberOfConnectedComp();
        
        System.out.println(answer);
        
    }
}