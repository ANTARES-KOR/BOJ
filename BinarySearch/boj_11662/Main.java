package boj_11662;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] a = new int[2];
	static int[] b = new int[2];
	static int[] c = new int[2];
	static int[] d = new int[2];
	static final int ACCURACY = 1000005; // 오차범위 맞추기 위해서.
	
	static double getDistance(double percent) {
		
		double minhoX = percent / ACCURACY * (b[0] - a[0]) + a[0];
		double minhoY = percent / ACCURACY * (b[1] - a[1]) + a[1];
		double kanghoX = percent / ACCURACY * (d[0] - c[0]) + c[0];
		double kanghoY = percent / ACCURACY * (d[1] - c[1]) + c[1];
		
		return Math.sqrt( Math.pow(kanghoX - minhoX, 2) + Math.pow(kanghoY - minhoY, 2) );
	}
	
	static double getClosestDist() {
		
		
		double left = 0, right = ACCURACY;
		double p, q;
		
		while(left+3 <= right) {
			p = (left*2 + right)/3;
			q = (left +right*2)/3;
			
			double pDist = getDistance(p);
			double qDist = getDistance(q);
			
			if(pDist >= qDist) {
				left = p;
			}
			else {
				right = q;
			}
		}
		
		double result = 20000;
		for(int i=(int)left; i<=(int)right; i++) {
			result = Math.min(result, getDistance((double)i));
		}
		
		return result;
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		a[0] = Integer.parseInt(st.nextToken());
		a[1] = Integer.parseInt(st.nextToken());
		b[0] = Integer.parseInt(st.nextToken());
		b[1] = Integer.parseInt(st.nextToken());
		c[0] = Integer.parseInt(st.nextToken());
		c[1] = Integer.parseInt(st.nextToken());
		d[0] = Integer.parseInt(st.nextToken());
		d[1] = Integer.parseInt(st.nextToken());

		System.out.println(getClosestDist());
		br.close();
	}
}
