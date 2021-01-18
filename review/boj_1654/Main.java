package boj_1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int numOfLanCables;
	static int neededNumOfCables;
	static long[] cableLength;
	
	static long getMaxCuttingLength() {
	
		long left = 1, right = cableLength[numOfLanCables-1];
		long mid;
		long cuttingLength = 0;
		
		while(left <= right) {
			mid = (left + right + 1)/2;
			long cableCnt = 0;
			for( long i : cableLength) {
				cableCnt += (long)i/mid;
				if(cableCnt > neededNumOfCables) {
					break;
				}
			}
			
			if(cableCnt >= neededNumOfCables) {
				left = mid + 1;
				cuttingLength = Math.max(mid, cuttingLength);
			}
			else {
				right = mid - 1;
			}
		}
		
		return cuttingLength;
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
	
		numOfLanCables = Integer.parseInt(input[0]);
		neededNumOfCables = Integer.parseInt(input[1]);
	
		cableLength = new long[numOfLanCables];
		
		for(int i=0; i<numOfLanCables; i++) {
			cableLength[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		
		Arrays.sort(cableLength);
		
		System.out.println(getMaxCuttingLength());
	}
}
