package boj_10830;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] arr;
	static int size;
	static final int MOD = 1000;

	static int[][] powerArr(long power){
		if(power == 1) {
			return arr;
		}
		
		int[][] result = new int[size][size];
		result = powerArr(power/2);
		if(power % 2 == 0) {
			return multiplyArr(result, result);
		}
		return multiplyArr(arr, multiplyArr(result, result));
	}
	
	static int[][] multiplyArr(int[][] one, int[][] two) {
		int[][] ret = new int[size][size];
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				ret[i][j] = 0;
				for(int k=0; k<size; k++) {
					ret[i][j] += one[i][k] * two[k][j];
						ret[i][j] %= MOD;
				}
			}
		}
		
		return ret;
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		size = Integer.parseInt(st.nextToken());
		long power = Long.parseLong(st.nextToken());
		
		arr = new int[size][size];
		for(int i=0; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<size; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] ans = powerArr(power);
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				bw.write(ans[i][j]%MOD +" ");
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
		
		
	}
}
