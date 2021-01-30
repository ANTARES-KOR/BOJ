package boj_2875;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int numOfGirls = scan.nextInt();
		int numOfBoys = scan.nextInt();
		int requiredInterns = scan.nextInt();
		
		int maxTeamNum = Math.min(numOfBoys, numOfGirls/2);
		
		int internNum = numOfBoys + numOfGirls - 3*maxTeamNum;

		while(internNum < requiredInterns) {
			maxTeamNum--;
			internNum = numOfBoys + numOfGirls - 3*maxTeamNum;
		}
		
		System.out.println(maxTeamNum);
		scan.close();
	}

}
