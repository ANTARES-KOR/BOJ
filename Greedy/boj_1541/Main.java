package boj_1541;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split("((?<=\\+)|(?=\\+)|(?<=\\-)|(?=\\-))");
		
		int ans = 0;
		Boolean plusFlag = true;
		
		for(int i=0; i<input.length; i++) {
			if(input[i].equals("-")) {
				plusFlag = false;
			}
			else if(!input[i].equals("+")){
				if(plusFlag) {
					ans += Integer.parseInt(input[i]);
				}
				else {
					ans -= Integer.parseInt(input[i]);
				}
			}
		}
		
		System.out.println(ans);
	}

}
