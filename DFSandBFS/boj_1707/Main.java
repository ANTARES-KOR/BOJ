package boj_1707;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

class ListGraph {
    
    private ArrayList<ArrayList<Integer>> listGraph;
    
    public ListGraph(int initialSize){
        this.listGraph = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<=initialSize; i++){
            this.listGraph.add(new ArrayList<Integer>());
        }
    }
    
    public void addEdge(int node1, int node2){
        this.listGraph.get(node1).add(node2);
        this.listGraph.get(node2).add(node1);
    }
    
    public ArrayList<Integer> getNode(int node){
        return this.listGraph.get(node);
    }
}

public class Main {
    
    static int vertexNum, edgeNum;
    static ListGraph adjList;
    static Boolean[] isVisited;
    static Boolean[] nodeColor;
    
    static Boolean isBipartiteGraph(){
        
        // 이분 그래프 찾는방법
        // dfs를 진행하면서 인접정점은 전부 색을 칠해줌.
        isVisited = new Boolean[vertexNum+1];
        nodeColor = new Boolean[vertexNum+1];
        
        Arrays.fill(isVisited,false);
        Arrays.fill(nodeColor,false);
        
        for(int i=1; i<=vertexNum; i++){
            if(!isVisited[i]){
                colorNodesWhileDFS(i);
            }
        }
        
        Boolean isBipartite = true;
        for(int i=1; i<=vertexNum; i++){
            ArrayList<Integer>adjNodes = adjList.getNode(i);
            for(Integer j : adjNodes){
                if(nodeColor[i] == nodeColor[j]){
                    isBipartite = false;
                    break;
                }
            }
        }
        
        if(isBipartite){
            return true;
        }
        return false;
        
    }
    
    static void colorNodesWhileDFS(int startNode){
        ArrayList<Integer> adjNodes = adjList.getNode(startNode);
        isVisited[startNode] = true;
        
        for(Integer i : adjNodes){
            if(!isVisited[i]){
                nodeColor[i] = !nodeColor[startNode];
                colorNodesWhileDFS(i);
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int testCaseNumber = Integer.parseInt(br.readLine());
        String[] input = new String[2];
        int node1, node2;
        
        for(int i = 0; i < testCaseNumber; i++){
            input = br.readLine().split(" ");
            vertexNum = Integer.parseInt(input[0]);
            edgeNum = Integer.parseInt(input[1]);
            
            adjList = new ListGraph(vertexNum);
            for(int j = 0; j < edgeNum; j++){
                input = br.readLine().split(" ");
                node1 = Integer.parseInt(input[0]);
                node2 = Integer.parseInt(input[1]);
                adjList.addEdge(node1, node2);
            }
            
            if(isBipartiteGraph()){
                bw.write("YES\n");
            }
            else{
                bw.write("NO\n");
            }
        }
        
        br.close();
        bw.flush();
        
    }
}











