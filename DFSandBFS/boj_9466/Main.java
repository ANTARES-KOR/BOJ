package boj_9466;

import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

public class Main{

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static ArrayList<Integer> studentTeam;
	static Boolean[] isVisited;
	static int studentNum;
	static int cycleStartDepth;
	static int[] depthArr;
	static Boolean[] isSearched;

	static void getStudentInfoAndInitialize() throws IOException{
		
		studentNum = Integer.parseInt(br.readLine());
		studentTeam = new ArrayList<Integer>();
		isVisited = new Boolean[studentNum + 1];
		isSearched = new Boolean[studentNum + 1];
		depthArr = new int[studentNum + 1];

		Arrays.fill(isVisited, false);
		Arrays.fill(isSearched, false);
		Arrays.fill(depthArr, 0);
		studentTeam.add(0); // 1부터 시작해야해서

		
		String[] input = br.readLine().split(" ");
		for(int i=0; i<studentNum; i++){
			studentTeam.add(Integer.parseInt(input[i]));
		}
	}

	static int getNumberOfTeams(){
		int teamStudentsNum = 0;

		for(int i = 1; i <= studentNum; i++){
			if(!isVisited[i]){
				cycleStartDepth = studentNum + 2;
				teamStudentsNum += getTeamMemberNum(i,1); 
			}
		}
		return teamStudentsNum;
	}

	static int getTeamMemberNum(int node, int depth){

		isVisited[node] = true;
		depthArr[node] = depth;
		int nextNode = studentTeam.get(node);
		int ret = 0;
		if(isVisited[nextNode] ){
			if(!isSearched[nextNode]){
				cycleStartDepth = depthArr[nextNode];
				//System.out.println(cycleStartDepth + " | " + depth);
			}
		}
		else {
			ret = getTeamMemberNum(nextNode, depth+1);
		}
		if(depth >= cycleStartDepth){
			ret += 1;
		}
		isSearched[node] = true;
		return ret;
	}

	public static void main(String[] args) throws IOException{

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int testCaseNum = Integer.parseInt(br.readLine());
		for(int i = 0; i < testCaseNum; i++){
			getStudentInfoAndInitialize();
			bw.write( (studentNum - getNumberOfTeams()) + "\n");
		}

		bw.flush();
		br.close();
	}
}