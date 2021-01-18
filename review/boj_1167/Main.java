package boj_1167;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Node {
	public int distance;
	public int number;
	
	public Node(int number, int distance) {
		this.number = number;
		this.distance = distance;
	}
}

class ListGraph{
	private ArrayList<ArrayList<Node>> list;
	
	public ListGraph(int initSize) {
		this.list = new ArrayList<ArrayList<Node>>();

		for(int i=0; i<initSize+1; i++) {
			list.add(new ArrayList<Node>());
		}
	}

	public ArrayList<Node> getNodes(int nodeNum) {
		return list.get(nodeNum);
	}

	
	public void addEdge(int startNode, int endNode, int distance) {
		list.get(startNode).add(new Node(endNode, distance));
		list.get(endNode).add(new Node(startNode, distance));
	}
}

public class Main {
	
	static int numOfNodes;
	static ListGraph adjList;
	static Boolean[] isVisited;
	static int maxDist = -1;
	static int farthestPoint = 0;
	
	static int getTreeRadius() {
		
		// 1에서부터 dfs를 해서 제일 먼 점을 찾는다.
		Arrays.fill(isVisited, false);
		dfs(1, 0);
		// 그 제일 먼 점에서부터 제일 먼 점을 구하면 그 사이 거리가 트리의 지름임.
		maxDist = -1;
		Arrays.fill(isVisited, false);
		dfs(farthestPoint, 0);
		
		return maxDist;
	}
	
	static void dfs(int startNode, int distance) {
		
		if(isVisited[startNode]) {
			return;
		}
		isVisited[startNode] = true;
		
		if(distance > maxDist) {
			farthestPoint = startNode;
			maxDist = distance;
		}
		
		ArrayList<Node> nextNodes = adjList.getNodes(startNode);
		
		for(Node i : nextNodes) {
			dfs(i.number, i.distance + distance);
		}
		
	}
	
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		numOfNodes = Integer.parseInt(br.readLine());
		isVisited = new Boolean[numOfNodes + 1];
		adjList = new ListGraph(numOfNodes);
		
		int startNode, endNode, distance;
		
		for(int i=0; i<numOfNodes; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			startNode = Integer.parseInt(st.nextToken());
			while(st.hasMoreTokens()) {
				
				endNode = Integer.parseInt(st.nextToken());
				if(endNode == -1) {
					break;
				}
				distance = Integer.parseInt(st.nextToken());
				
				adjList.addEdge(startNode, endNode, distance);
				
			}
		}
		
		br.close();
		System.out.println( getTreeRadius() );
		
	}
}













