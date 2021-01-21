package boj_10815;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int numOfCards;
	static int numOfFindings;
	static int[] cards;
	
	static int isNumberInArr(int number) {
		
		int left=0, right=numOfCards-1;
		int mid;
		Boolean isNumExist = false;
		
		while(left <= right) {
			mid = (left + right + 1)/2;
			
			if(cards[mid] == number) {
				isNumExist = true;
				break;
			}
			else if(cards[mid] > number) {
				right = mid - 1;
			}
			else {
				left = mid + 1;
			}
		}
		
		if(isNumExist) {
			return 1;
		}
		return 0;
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		numOfCards = Integer.parseInt(br.readLine());
		cards = new int[numOfCards];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<numOfCards; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(cards);
		
		numOfFindings = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<numOfFindings; i++) {
			bw.write( isNumberInArr(Integer.parseInt(st.nextToken())) + " " );
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
