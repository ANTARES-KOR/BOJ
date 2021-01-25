package boj_2630;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] paper;
	static int blueCnt = 0, whiteCnt = 0;
	static final int[][] startPoint = {{0,0}, {0,1}, {1,0}, {1,1}};
	
	static void countBlueandWhite(int size, int startX, int startY){
		if(isFull(size, startX, startY)) {
			return;
		}
		
		int nextSize = size/2;
		for(int i=0; i<4; i++) {
			countBlueandWhite(nextSize, startX + startPoint[i][0]*nextSize, startY + startPoint[i][1]*nextSize);
		}
	}
	
	static Boolean isFull(int size, int startX, int startY) {
		int checkNum = paper[startX][startY];
		for(int i=startX; i<startX+size; i++) {
			for(int j=startY; j <startY+size; j++) {
				if(paper[i][j] != checkNum) {
					return false;
				}
			}
		}
		
		if(checkNum == 0) {
			whiteCnt++;
		}
		else {
			blueCnt++;
		}
		return true;
	}

	public static void main(String[] args)throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int size = Integer.parseInt(br.readLine());
		paper = new int[size][size];
		
		for(int i=0; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<size; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		countBlueandWhite(size, 0, 0);
		System.out.println(whiteCnt + "\n" + blueCnt);
		br.close();
		
	}
}
