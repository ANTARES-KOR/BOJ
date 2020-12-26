package boj_1167;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

class nextNode{
    public int next;
    public int distance;
    
    public nextNode(int next, int distance){
        this.next = next;
        this.distance = distance;
    }
}

class ListGraph {
    private ArrayList<ArrayList<nextNode>> nodeList = new ArrayList<ArrayList<nextNode>>();
    
    public ListGraph(int numOfNodes){
        for(int i=0; i<=numOfNodes; i++){
            nodeList.add(new ArrayList<nextNode>());
        }
    }
    
    public void add(int start, int next, int distance){
        nodeList.get(start).add(new nextNode(next, distance));
        //nodeList.get(nextNode).add(new nextNode(next, distance));
    }
    
    public ArrayList<nextNode> getAdjList(int node){
        return nodeList.get(node);
    }
}

public class Main{
    
    static ListGraph tree;
    static Boolean[] isVisited;
    static int numOfNodes;
    static int max_length;
    static int farthestPoint;
    
    static void addEdgeInfo(String input[]){
        
        int node = Integer.parseInt(input[0]);
        int next, dist;
        for(int i=1; i<input.length; i+=2){
            next = Integer.parseInt(input[i]);
            if(next == -1){
                break;
            }
            dist = Integer.parseInt(input[i+1]);
            tree.add(node, next, dist);
        }
        
    }
    
    static int getTreeRadius(){
        
        isVisited = new Boolean[numOfNodes+1];
        Arrays.fill(isVisited, false);
        // 임의의 점에서 가장 먼 점 a를 찾자.
        getFarthestPoint(1, 0);
        
        Arrays.fill(isVisited, false);
        max_length = -1;
        
        getFarthestPoint(farthestPoint, 0);
        
        return max_length;
    }
    
    static void getFarthestPoint(int current_node, int length){
        isVisited[current_node] = true;
        if(max_length < length){
            max_length = length;
            farthestPoint = current_node;
        }
        
        ArrayList<nextNode> nextList = tree.getAdjList(current_node);
        for(nextNode i : nextList){
            if(!isVisited[i.next]){
                getFarthestPoint(i.next, length + i.distance);
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        numOfNodes = Integer.parseInt(br.readLine());
        tree = new ListGraph(numOfNodes);
        
        for(int i=0; i<numOfNodes; i++){
            addEdgeInfo(br.readLine().split(" "));
        }
        
        System.out.println( getTreeRadius() );
        
        br.close();
    }
}