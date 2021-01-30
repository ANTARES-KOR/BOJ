package boj_2873;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int numOfRow, numOfCol;
	static int[][] planetCoasterSite;
	
	static String getPlanetCoaster() {
		StringBuilder sb = new StringBuilder();
		
		// numOfRow, numOfCol 둘 다 짝수인 경우에는 계산이 필요.
		
		// 아닌 경우에는 홀수인데를 기준으로 완전탐색이 가능하다.
	}
	
	public static void main(String[] args)throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		numOfRow = Integer.parseInt(input[0]);
		numOfCol = Integer.parseInt(input[1]);
		planetCoasterSite = new int[numOfRow][numOfCol];

		for(int i=0; i<numOfRow; i++) {
			input = br.readLine().split(" ");
			for(int j=0; j<numOfCol; j++) {
				planetCoasterSite[i][j] = Integer.parseInt(input[i]);
			}
		}
		
		String answer = getPlanetCoaster();
		br.close();
	}
}
