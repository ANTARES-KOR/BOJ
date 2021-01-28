package boj_11047;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int numOfCoins = scan.nextInt();
		int requiredMoney = scan.nextInt();
		int[] coin = new int[numOfCoins];
		
		int usingCoinPos = 0;
		for(int i=0; i<numOfCoins; i++) {
			coin[i] = scan.nextInt();
			if(coin[i] <= requiredMoney) {
				usingCoinPos = i;
			}
		}
		
		int usedCoinCnt = 0;
		while(requiredMoney > 0) {
			usedCoinCnt++;
			requiredMoney -= coin[usingCoinPos];
			
			if(requiredMoney < coin[usingCoinPos]) {
				for(int i=usingCoinPos-1; i>=0; i--) {
					if(coin[i] <= requiredMoney) {
						usingCoinPos = i;
						break;
					}
				}
			}
		}
		
		System.out.println(usedCoinCnt);
		scan.close();
	}
}
