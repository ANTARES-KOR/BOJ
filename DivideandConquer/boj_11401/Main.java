package boj_11401;

import java.util.Scanner;

public class Main {
	
	static final long P = 1000000007;
	static long B;
	
	
	
	static long getBPowerP(long power) {
		if(power == 0) {
			return 1;
		}
		
		if(power % 2 == 0) {
			long half = getBPowerP(power/2);
			return ((long)half * half % P);
		}
		else {
			return ((long)getBPowerP(power-1) * B % P);
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		int k = scan.nextInt();
		
		
		long A = 1;
		B = 1;
	
		for(int i=1; i<=n; i++) {
			A *= i;
			A %= P;
		}
		for(int i=1; i<=n-k; i++) {
			B *= i;
			B %= P;
		}
		for(int i=1; i<=k; i++) {
			B *= i;
			B %= P;
		}
		
		long result = A * getBPowerP(P-2) % P;
		System.out.println(result);
		scan.close();
	}
}
