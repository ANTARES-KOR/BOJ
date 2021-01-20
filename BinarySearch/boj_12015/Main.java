package boj_12015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

	static int sizeOfArr;
	static int[] activeList;
	static int longestLength = 0;
	
	static void addToActiveList(int number) {
		Boolean isBiggest = true;
		int leastBiggest = 0;
		
		for(int i=0; i<=longestLength; i++) {
			if(isBiggest && number <= activeList[i]) {
				isBiggest = false;
			}
			else if(number > activeList[i]) {
				leastBiggest = i;
			}
		}
		
		if(isBiggest) {
			activeList[++longestLength] = number;
		}
		else {
			if( activeList[leastBiggest+1] > number ) {
				activeList[leastBiggest+1] = number;
			}
		}
	}
	
	public static void main(String[] args)throws IOException {
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		sizeOfArr = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		activeList = new int[sizeOfArr+1];
		Arrays.fill(activeList, 0);

		
		for(int i=0; i<sizeOfArr; i++) {
			addToActiveList(Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(longestLength);
		br.close();
	}
}
