package boj_1300;

import java.util.Scanner;

public class Main {

	static long getKthNumber(int n, int k) {
		
		long left = 1, right = k;
		long mid;
		long ret=k;
		
		while(left <= right) {
			mid = (left +right)/2;
			
			long smallerNumCnt = 0;
			for(int i=1;i<=n; i++) {
				smallerNumCnt += Math.min(n, mid/i);
			}
			
			if(smallerNumCnt >= k) {
				right = mid - 1;
				ret = Math.min(ret, mid);
			}
			else {
				left = mid + 1;
			}
		}
		return ret;
	}

	public static void main(String[] args){
		
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		int k = scan.nextInt();
		
		System.out.println(getKthNumber(n, k));
		scan.close();
	}
}
