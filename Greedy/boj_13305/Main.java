package boj_13305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int numOfCities;
	static int[] oilPrice;
	static int[] highwayLength;
	
	static long getMinPriceToEnd() {
		int previousOilPrice = 2000000000;
		int previousMovingDist = 0;
		long ret=0;
		
		for(int i=0; i<numOfCities; i++) {
			previousMovingDist += highwayLength[i];
			if(oilPrice[i] < previousOilPrice || isEndPos(i)) {
				ret += (long)previousMovingDist * previousOilPrice;
				previousMovingDist = 0;
				previousOilPrice = oilPrice[i];
			}
		}
		
		return ret;
	}
	
	static Boolean isEndPos(int pos) {
		if(pos == numOfCities-1) {
			return true;
		}
		else {
			return false;
		}
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		numOfCities = Integer.parseInt(br.readLine());
		highwayLength = new int[numOfCities];
		oilPrice = new int[numOfCities];
		String[] input = new String[numOfCities];

		input = br.readLine().split(" ");
		highwayLength[0] = 0;
		for(int i=1; i<=numOfCities-1; i++) {
			highwayLength[i] = Integer.parseInt(input[i-1]);
		}
		
		input = br.readLine().split(" ");
		for(int i=0; i<numOfCities; i++) {
			oilPrice[i] = Integer.parseInt(input[i]);
		}
		
		System.out.println(getMinPriceToEnd());
		br.close();
	}

}
