package boj_10451;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

class ListGraph{
    private ArrayList<ArrayList<Integer>> listGraph;
    
    public ListGraph(int initialSize){
        this.listGraph = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i<=initialSize+1; i++){
            this.listGraph.add(new ArrayList<Integer>());
        }
    }
    
    public ArrayList<Integer> getNode(int node){
        return this.listGraph.get(node);
    }
    
    public void addEdge(int node1, int node2){
        this.listGraph.get(node1).add(node2);
        this.listGraph.get(node2).add(node1);
    }
}

public class Main{
    
    static int sizeOfGraph;
    static ListGraph map;
    static Boolean[] isVisited;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    static int getNumberOfCycles() {
        
        isVisited = new Boolean[sizeOfGraph+1];
        Arrays.fill(isVisited, false);
        int cycleCount = 0;
        
        for(int i = 1; i<=sizeOfGraph; i++){
            if(!isVisited[i]){
                if(isCycle(i)){
                    cycleCount++;
                }
            }
        }
        
        return cycleCount;
    }
    
    static Boolean isCycle(int startNode){
        
        Boolean ret = false;
        isVisited[startNode] = true;
        ArrayList<Integer> adjList = map.getNode(startNode);
        
        for(int i : adjList){
            if(!isVisited[i]){
                if(ret){
                    isCycle(i);
                }
                else {
                    ret = isCycle(i);
                } 
            }
            else{
                ret = true;
            }
        }
        return ret;
    }
    
    static void getGraph() throws IOException{
        
        
        sizeOfGraph = Integer.parseInt(br.readLine());
        map = new ListGraph(sizeOfGraph);
        
        String[] input = br.readLine().split(" ");
        for(int i=1; i<=sizeOfGraph; i++){
            int node = Integer.parseInt(input[i-1]);
            map.addEdge(i, node);
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int testCaseNum = Integer.parseInt(br.readLine());
        
        
        int cycleNum = 0;
        for(int i=0; i<testCaseNum; i++){
            getGraph();
            cycleNum = getNumberOfCycles();
            bw.write(cycleNum + "\n");
        }
        
        br.close();
        bw.flush();
    }
}