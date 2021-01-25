package boj_1629;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int A,C;
	
	static long[] dp = new long[1000000];
	
	static long multiply(int power) {
		if(power < 1000000 && dp[power] != -1) {
			return dp[power];
		}
		if(power == 1) {
			return A%C;
		}
		
		if(power < 1000000) {
			if(power%2 == 1) {
				return dp[power] = (multiply(power/2) * multiply(power/2) % C) * multiply(1) % C;
			}
			else {
				return dp[power] = multiply(power/2) * multiply(power/2) % C;

			}
		}
		
		
		if(power%2 == 1) {
			return (multiply(power/2) * multiply(power/2) % C) * multiply(1) % C;
		}
		else {
			return multiply(power/2) * multiply(power/2) % C;

		}
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		A = scan.nextInt();
		int B = scan.nextInt();
		C = scan.nextInt();
		
		Arrays.fill(dp, -1);
		System.out.println((long)multiply(B)%C);
		scan.close();
	}
}
