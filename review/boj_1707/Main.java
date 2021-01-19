package boj_1707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class ListGraph {
	private ArrayList<ArrayList<Integer>> list;
	
	public ListGraph(int initSize) {
		list = new ArrayList<ArrayList<Integer>>();
		
		for(int i=0; i<initSize+1; i++) {
			list.add( new ArrayList<Integer>() );
		}
	}
	
	public ArrayList<Integer> getNode(int nodeNum) {
		return list.get(nodeNum);
	}
	
	public void addEdge(int startNode, int endNode) {
		list.get(startNode).add(endNode);
		list.get(endNode).add(startNode);
	}
}

public class Main {
	
	static ListGraph adjList;
	static int[] isVisited;
	
	static Boolean isBipartite() {
		return dfs(1, 1);
		
	}
	
	static Boolean dfs(int node, int teamNum) {
		if(isVisited[node] != 0) {
			if(teamNum == isVisited[node]) {
				return true;
			}
			else {
				return false;
			}
		}
		isVisited[node] = teamNum;
		
		int nextNum;
		if(teamNum == 1) {
			nextNum = 2;
		}
		else {
			nextNum = 1;
		}

		for(int i : adjList.getNode(node)) {
			if(!dfs(i, nextNum)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args)throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int numOfTestCases = Integer.parseInt(st.nextToken());
		int numOfNodes;
		int numOfEdges;
		int startNode, endNode;
		
		for(int i=0; i<numOfTestCases; i++) {
			st = new StringTokenizer(br.readLine());
			numOfNodes = Integer.parseInt(st.nextToken());
			numOfEdges = Integer.parseInt(st.nextToken());
			adjList = new ListGraph(numOfNodes);
			isVisited = new int[numOfNodes+1];
			Arrays.fill(isVisited, 0);
			
			for(int j=0; j<numOfEdges; j++) {
				st = new StringTokenizer(br.readLine());
				startNode = Integer.parseInt(st.nextToken());
				endNode = Integer.parseInt(st.nextToken());
				adjList.addEdge(startNode, endNode);
			}
			
			if(isBipartite()) {
				System.out.println("YES");
			}
			else {
				System.out.println("NO");
			}
		}
		
		
	}
}
