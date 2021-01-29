package boj_11399;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int numOfPeople = Integer.parseInt(br.readLine());
		int[] atmMinutes = new int[numOfPeople];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<numOfPeople; i++) {
			atmMinutes[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(atmMinutes);
		
		int ans=0;
		for(int i:atmMinutes) {
			ans += i * (numOfPeople--);
		}
		
		System.out.println(ans);
	}

}
