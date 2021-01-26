package boj_11444;

import java.util.Scanner;

public class Main {
	
	static final long MOD = 1000000007;
	static long[][] fibo1 = { {1,1}, {1,0} }; 
	
	static long[][] fibo(long power){
		if(power == 1) {
			return fibo1;
		}
		
		long[][] result = new long[2][2];
		result = fibo(power/2);
		if(power % 2 == 0) {
			return multiplyArr(result, result);
		}
		return multiplyArr(fibo1, multiplyArr(result, result));
	}
	
	static long[][] multiplyArr(long[][] one, long[][] two) {
		long[][] ret = new long[2][2];
		
		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				ret[i][j] = 0;
				for(int k=0; k<2; k++) {
					ret[i][j] += one[i][k] * two[k][j];
					ret[i][j] %= MOD;
				}
			}
		}
		
		return ret;
	}

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		long n = scan.nextLong();
		
		long[][] ans = fibo(n);
		System.out.println(ans[0][1]);
		scan.close();
		
	}
}
