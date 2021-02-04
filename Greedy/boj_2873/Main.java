package boj_2873;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	static int numOfRow, numOfCol;
	static int[][] planetCoasterSite;
	static int minX, minY, min;
	static StringBuilder sb = new StringBuilder();
	
	static void zeroTraverse() {
		
		Boolean[][] isVisited = new Boolean[2][numOfCol];
		Arrays.fill(isVisited[0], false);
		Arrays.fill(isVisited[1], false);
		
		int x=0, y=0;
		
		while(true) {
			isVisited[x][y] = true;
			if(x == 1) {
				if(y == minY || isVisited[x-1][y]) {
					y++;
					sb.append("R");
				}
				else {
					x--;
					sb.append("U");
				}

			}
			else {
				if(isVisited[x+1][y]) {
					y++;
					sb.append("R");
				}
				else {
					x++;
					sb.append("D");
				}
			}
			if(x == 1 && y == numOfCol-1) {
				break;
			}
		}
		if(numOfRow != 2) {
			sb.append("D");
		}
	}
	
	static void traverse() {
		
		Boolean[][] isVisited = new Boolean[2][numOfCol];
		Arrays.fill(isVisited[0], false);
		Arrays.fill(isVisited[1], false);


		int x = 0, y = 0;
		
		while(true) {
			isVisited[x][y] = true;
			if(x==0) {
				if(y == minY || isVisited[x+1][y]) {
					y++;
					sb.append("R");
				}
				else {
					x++;
					sb.append("D");
				}
			}
			else {
				if(isVisited[x-1][y]) {
					y++;
					sb.append("R");
				}
				else {
					x--;
					sb.append("U");
				}
			}
			
			if(x == 1 && y == numOfCol-1) {
				break;
			}
		}
		
		if(minX != numOfRow-1) {
			sb.append("D");
		}
	}
	
	static String getPlanetCoaster() {
		
		// numOfRow, numOfCol 둘 다 짝수인 경우에는 계산이 필요.
		if(isEven(numOfRow) && isEven(numOfCol)) {

			StringBuilder rightBuild = new StringBuilder();
			StringBuilder leftBuild = new StringBuilder();
			for(int i=0; i<numOfCol-1; i++) {
				rightBuild.append("R");
				leftBuild.append("L");
			}
			String right = rightBuild.toString();
			String left = leftBuild.toString();
			
			for(int i=0; i<minX-1; i++) {
				if(i%2 == 0) {
					sb.append(right);
				}
				else {
					sb.append(left);
				}
				sb.append("D");
			}
			
			if(minX == 0) {
				zeroTraverse();
				minX++;
			}
			else {
				traverse();
			}
			
			for(int i=minX+1; i<numOfRow; i++) {
				if(i%2 == 0) {
					sb.append(left);
				}
				else {
					sb.append(right);
				}
				if(i != numOfRow-1) {
					sb.append("D");
				}
			}

		}
		// 아닌 경우에는 홀수인데를 기준으로 완전탐색이 가능하다.
		else {
			if(isEven(numOfCol)){
				//numOfRow 기준, RRRRDLLLLDRRRRDLLLLDRRRR
				StringBuilder rightBuild = new StringBuilder();
				StringBuilder leftBuild = new StringBuilder();
				for(int i=0; i<numOfCol-1; i++) {
					rightBuild.append("R");
					leftBuild.append("L");
				}
				String right = rightBuild.toString();
				String left = leftBuild.toString();
				for(int i=0; i<numOfRow; i++) {
					if(i%2 == 0) {
						sb.append(right);
					}
					else {
						sb.append(left);
					}
					if(i!=numOfRow-1) {
						sb.append("D");
					}
				}
			}
			else {
				//numOfCol standard
				StringBuilder upBuild = new StringBuilder();
				StringBuilder downBuild = new StringBuilder();
				for(int i=0; i<numOfRow-1; i++) {
					upBuild.append("U");
					downBuild.append("D");
				}
				String down = downBuild.toString();
				String up = upBuild.toString();
				for(int i=0; i<numOfCol; i++) {
					if(i%2 == 0) {
						sb.append(down);
					}
					else {
						sb.append(up);
					}
					if(i!=numOfCol-1) {
						sb.append("R");
					}
				}
			}
		}
		return sb.toString();
	}
	
	static Boolean isEven(int num) {
		if(num%2 == 0) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args)throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		numOfRow = Integer.parseInt(input[0]);
		numOfCol = Integer.parseInt(input[1]);
		planetCoasterSite = new int[numOfRow][numOfCol];
		min = 10000;

		for(int i=0; i<numOfRow; i++) {
			input = br.readLine().split(" ");
			for(int j=0; j<numOfCol; j++) {
				int number = Integer.parseInt(input[j]);
				planetCoasterSite[i][j] = number;
				if(number < min && (i+j)%2 == 1) {
					min = number;
					minX = i;
					minY = j;
				}
				
			}
		}
		
		String answer = getPlanetCoaster();
		br.close();
		bw.write(answer);
		bw.flush();
		bw.close();
	}
}
