package boj_2448;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	
	static Boolean map[][]; 
	static void drawTriangle(int startA, int startB, int k) {
		
		if(k == 0) {
			drawTriangularUnit(startA, startB);
			return;
		}
		
		drawTriangle(startA, startB, k-1);
		drawTriangle(startA + 3*(int)Math.pow(2,k-1), startB - 3*(int)Math.pow(2,k-1), k-1);
		drawTriangle(startA + 3*(int)Math.pow(2,k-1), startB + 3*(int)Math.pow(2,k-1), k-1);
	}
	
	static void drawTriangularUnit(int m, int n){
		map[m][n] = map[m+1][n-1] = map[m+1][n+1] = map[m+2][n-2] = map[m+2][n-1] = map[m+2][n] = map[m+2][n+1] = map[m+2][n+2] = true;
		
	}

	public static void main(String[] args) throws IOException {
	
		Scanner scan = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int size = scan.nextInt();
		map = new Boolean[size][2*size + 1];
		
		for(int i=0; i<size; i++) {
			Arrays.fill(map[i], false);
		}
		
		int k=0;
		for(int i=0; i<=10; i++) {
			if(Math.pow(2,i) * 3 == size) {
				k = i;
				break;
			}
		}
		drawTriangle(0, size-1, k);
		scan.close();
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size*2+1; j++) {
				if(map[i][j]) {
					bw.write("*");
				}
				else {
					bw.write(" ");
				}
			}
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
	}
}
