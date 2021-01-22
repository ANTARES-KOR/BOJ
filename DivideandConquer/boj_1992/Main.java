package boj_1992;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int[][] image;
	static final int[][] pieces = {{0,0}, {0,1}, {1,0}, {1,1} };
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static void compressInQuadTree(int x, int y, int size)throws IOException{
		
		if(size == 1 || checkFull(x, y, size)) {
			bw.write( image[x][y] + "" );
			return;
		}
		
		bw.write("(");
		
		for(int i=0; i<4; i++) {
			int pieceSize = size / 2;
			int pieceX = pieces[i][0] * pieceSize + x;
			int pieceY = pieces[i][1] * pieceSize + y;
			compressInQuadTree(pieceX, pieceY, pieceSize);
		}
		
		bw.write(")");
	}
	
	static Boolean checkFull(int x, int y, int size) {

		int num = image[x][y];
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				if(num != image[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int sizeOfImage = Integer.parseInt(br.readLine());
		image = new int[sizeOfImage][sizeOfImage];
		
		for(int i=0; i<sizeOfImage; i++) {
			String[] input = br.readLine().split("");
			for(int j=0; j<sizeOfImage; j++) {
				image[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		compressInQuadTree(0,0,sizeOfImage);
		br.close();
		bw.flush();
		bw.close();
	}

}
