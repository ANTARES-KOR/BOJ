package boj_2873;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main2 {

	static int height, length;
	static int[][] happinessMap;
	static int oddMin = 100000, oddMinX, oddMinY;
	
	static String getRollerCoaster() {
		
		if(height%2 == 0 && length%2 == 0) {
			return getBothEvenRollerCoaster();
		}
		else if(height%2 == 0) {
			return getLengthOddRollerCoaster(length);
		}
		else {
			return getHeightOddRollerCoaster(height);
		}
	}
	
	static String getLengthOddRollerCoaster(int givenLength) {
		String downString = getSuccesiveDirection("D", height-1);
		String upString = getSuccesiveDirection("U", height-1);
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<givenLength; i++) {
			if(i%2 == 0) {
				sb.append(downString);
			}
			else {
				sb.append(upString);
			}
			
			if(i < givenLength-1) {
				sb.append("R");
			}
		}
		
		return sb.toString();
	}
	
	static String getHeightOddRollerCoaster(int givenHeight) {

		String rightString = getSuccesiveDirection("R", length-1);
		String leftString = getSuccesiveDirection("L", length-1);
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<givenHeight; i++) {
			if(i%2 == 0) {
				sb.append(rightString);
			}
			else {
				sb.append(leftString);
			}
			
			if(i < givenHeight-1) {
				sb.append("D");
			}
		}
		
		return sb.toString();
		
	}
	
	static String getBothEvenRollerCoaster() {
		StringBuilder sb = new StringBuilder();
		String leftString = getSuccesiveDirection("L", length-1);

		if(oddMinX == 0) {
			sb.append( getRollerCoaster__Min_Part() );
			if(height == 2) {
				return sb.toString();
			}
			sb.append("D");
			sb.append(leftString);
			sb.append("D");
			
			sb.append(getHeightOddRollerCoaster(height-3));
			return sb.toString();
		}
		else {
			if(height == 2) {
				return getRollerCoaster__Min_Part();
			}
			sb.append( getHeightOddRollerCoaster(oddMinX-2) );
			sb.append("D");
			sb.append(leftString);
			sb.append("D");
			sb.append(getRollerCoaster__Min_Part());
			if(oddMinX == height-1) {
				return sb.toString();
			}
			sb.append("D");
			sb.append(leftString);
			sb.append("D");
			
			sb.append(getHeightOddRollerCoaster(height-oddMinX-2));
			return sb.toString();
		}
	}
	
	static String getRollerCoaster__Min_Part() {
		StringBuilder sb = new StringBuilder();
		Boolean[][] isVisited = new Boolean[2][length];
		Arrays.fill(isVisited[0], false);
		Arrays.fill(isVisited[1], false);
		
		int x=0, y=0;
		if(oddMinX == 0) {
			isVisited[oddMinX][oddMinY] = true;
		}
		else {
			isVisited[1][oddMinY] = true;
		}

		while(true) {
			isVisited[x][y] = true;
			if(x==1 && y==length-1) {
				break;
			}
			
			if(x==0) {
				if(isVisited[x+1][y]) {
					y++;
					sb.append("R");
				}
				else {
					x++;
					sb.append("D");
				}
			}
			else {
				if(isVisited[x-1][y]) {
					y++;
					sb.append("R");				
				}
				else {
					x--;
					sb.append("U");
				}
			}
		}
		
		return sb.toString();
		
	}
	
	
	static String getSuccesiveDirection(String direction, int number) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<number; i++) {
			sb.append(direction);
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		height = Integer.parseInt(input[0]);
		length = Integer.parseInt(input[1]);
		happinessMap = new int[height][length];
		
		for(int i=0; i<height; i++) {
			input = br.readLine().split(" ");
			for(int j=0; j<length; j++) {
				int inputNum = Integer.parseInt(input[j]);
				happinessMap[i][j] = inputNum;
				if((i+j)%2 == 1 && oddMin > inputNum) {
					oddMin = inputNum;
					oddMinX = i;
					oddMinY = j;
				}
			}
		}
		
		System.out.println(getRollerCoaster());
		br.close();
		
	}
}
