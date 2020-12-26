package boj_1991;

import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

class ListGraph {
    private ArrayList<ArrayList<Integer>> binaryTree = new ArrayList<ArrayList<Integer>>();
    
    public ListGraph(int numberOfNode){
        for(int i=0; i<numberOfNode; i++){
            binaryTree.add(new ArrayList<Integer>());
        }
    }
    
    public void addChild(int parent, int left, int right){
        binaryTree.get(parent).add(left);
        binaryTree.get(parent).add(right);
    }
    
    public ArrayList<Integer> getNode(int node){
        return binaryTree.get(node);
    }
}

public class Main {
    
    static ListGraph BTree;
    
    static void preorderTraverse(int node){
        ArrayList<Integer> childNodes = BTree.getNode(node);
        System.out.print( (char)(node + 'A'));
        for(int i : childNodes){
            if(i>0){
                preorderTraverse(i);
            }
        }
    }
    
    static void inorderTraverse(int node){
        ArrayList<Integer> childNodes = BTree.getNode(node);
        int child;
        Iterator<Integer> itr = childNodes.iterator();
        
        child = itr.next();
        if(child > 0){
            inorderTraverse(child);
        }
        
        System.out.print( (char)(node + 'A'));
        
        child = itr.next();
        if(child > 0){
            inorderTraverse(child);
        }
    }
    
    static void postorderTraverse(int node){
        ArrayList<Integer> childNodes = BTree.getNode(node);
        int child;
        Iterator<Integer> itr = childNodes.iterator();
        
        child = itr.next();
        if(child > 0){
            postorderTraverse(child);
        }
        
        child = itr.next();
        if(child > 0){
            postorderTraverse(child);
        }
        
        System.out.print( (char)(node + 'A'));
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[];
        int numberOfNode = Integer.parseInt(br.readLine());
        BTree = new ListGraph(numberOfNode);
        
        int parent, leftChild, rightChild;
        for(int i=0; i<numberOfNode; i++){
            input = br.readLine().split(" ");
            
            parent = (int)(input[0].charAt(0) - 'A');
            leftChild = (int)(input[1].charAt(0) - 'A');
            rightChild = (int)(input[2].charAt(0) - 'A');
            
            BTree.addChild(parent, leftChild, rightChild);  
        }
        
        preorderTraverse(0);
        System.out.println();
        inorderTraverse(0);
        System.out.println();
        postorderTraverse(0);
        
        br.close();
        
    }
    
}











