package boj_1783;

import java.util.Scanner;

public class Main {
	
	static int getMaxVisits(int height, int length) {
		if(height == 1) {
			return 1;	
		}
		else if(height == 2) {
			if(length <= 2) {
				return 1;
			}
			else if(length <= 4) {
				return 2;
			}
			else if(length <= 6) {
				return 3;
			}
			return 4;
		}
		else {
			if(length == 1) {
				return 1;
			}
			else if(length == 2) {
				return 2;
			}
			else if(length == 3) {
				return 3;
			}
			else if(length <= 6) {
				return 4;
			}
			else {
				return 2 + (length-4);
			}
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int height = scan.nextInt();
		int length  = scan.nextInt();
		
		scan.close();
		System.out.println(getMaxVisits(height, length));
	}
}
