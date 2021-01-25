package boj_1629;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int A,C;
	
	static int[] dp = new int[1000000];
	
	static int multiply(int power) {
		if(power < 1000000 && dp[power] != -1) {
			return dp[power];
		}
		if(power == 1) {
			return A;
		}
		
		if(power < 1000000) {
			if(power%2 == 1) {
				return dp[power] = (int)(Math.pow(multiply(power/2), 2) * multiply(1) % C);
			}
			else {
				return dp[power] = (int)(Math.pow(multiply(power/2), 2) % C);

			}
		}
		
		
		if(power%2 == 1) {
			return (int)(Math.pow(multiply(power/2), 2) * multiply(1) % C);
		}
		else {
			return (int)(Math.pow(multiply(power/2), 2) % C);

		}
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		A = scan.nextInt();
		int B = scan.nextInt();
		C = scan.nextInt();
		
		Arrays.fill(dp, -1);
		System.out.println(multiply(B)%C);
		scan.close();
	}
}
