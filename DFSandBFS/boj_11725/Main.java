package boj_11725;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

class ListGraph{
    private ArrayList<ArrayList<Integer>> nodeList = new ArrayList<ArrayList<Integer>>();
    
    public ListGraph(int numOfNodes){
        for(int i=0; i<numOfNodes + 1; i++){
            nodeList.add(new ArrayList<Integer>());
        }
    }
    public void addEdge(int node1, int node2){
        nodeList.get(node1).add(node2);
        nodeList.get(node2).add(node1);
    }
    public ArrayList<Integer> getNode(int node){
        return nodeList.get(node);
    }
}

public class Main {
    
    static ListGraph tree;
    static int[] parentsList;
    static Boolean[] isVisited;
    
    static void getParentsList(int parentNode){
        isVisited[parentNode] = true;
        ArrayList<Integer> childList = tree.getNode(parentNode);
        
        for(int i : childList){
					if(parentsList[i] == 0){
						//System.out.println(i + " " + parentNode);
            parentsList[i] = parentNode;
						if(!isVisited[i]){
							getParentsList(i);
						}
					}
        }
    }
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int numOfNodes = Integer.parseInt(br.readLine());
        tree = new ListGraph(numOfNodes);
        parentsList = new int[numOfNodes+1];
        isVisited = new Boolean[numOfNodes+1];
        Arrays.fill(isVisited, false);
				Arrays.fill(parentsList, 0);

        String[] input;
        int node1, node2;
        for(int i=0; i<numOfNodes - 1; i++){
            input = br.readLine().split(" ");
            node1 = Integer.parseInt(input[0]);
            node2 = Integer.parseInt(input[1]);
            tree.addEdge(node1, node2);
        }
        
        getParentsList(1);
        
        for(int i=2; i<=numOfNodes; i++){
            bw.write(parentsList[i] + "\n");
        }
        
        bw.flush();
        br.close();
    }
}