package boj_10610;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	
	static String[] input;
	
	static void make30() throws IOException{
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		if(!input[0].equals("0")) {
			System.out.println("-1");
			return;
		}
		int sumOfAllDigits = 0;
		for(int i=0; i<input.length; i++) {
			sumOfAllDigits += Integer.parseInt(input[i]);
		}

		if(sumOfAllDigits % 3 == 0) {
			for(int i=input.length-1; i>=0; i--) {
				bw.write(input[i]);
			}
		}
		else {
			System.out.println("-1");
		}
		bw.flush();
		bw.close();
		return;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		input = br.readLine().split("");
		Arrays.sort(input);
		make30();
		br.close();
	}

}
