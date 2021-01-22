package boj_1780;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] paper;
	static final int[][] piecesArr = { {0,0}, {0,1}, {0,2}, {1,0}, {1,1}, {1,2}, {2,0}, {2,1}, {2,2} };
	static int zeroCnt = 0, oneCnt = 0, minusOneCnt = 0;
	
	static Boolean isFullPaper(int startX, int startY, int size) {
		int number = paper[startX][startY];
		for(int i=startX; i<startX + size; i++) {
			for(int j=startY; j<startY + size; j++) {
				if(number != paper[i][j]) {
					return false;
				}
			}
		}
		
		if(number == 1) {
			oneCnt++;
		}
		else if(number == 0) {
			zeroCnt++;
		}
		else {
			minusOneCnt++;
		}
		
		return true;
	}
	
	static void getNumberofPieces(int size, int startX, int startY) {
		if(size == 1) {
			if(paper[startX][startY] == 1) {
				oneCnt++;
			}
			else if(paper[startX][startY] == 0) {
				zeroCnt++;
			}
			else {
				minusOneCnt++;
			}
			return;
		}

		int pieceSize = size/3;
		
		for(int i=0; i<9; i++) {
			int pieceStartX = startX + piecesArr[i][0]*pieceSize;
			int pieceStartY = startY + piecesArr[i][1]*pieceSize;
			if(!isFullPaper(pieceStartX, pieceStartY, pieceSize)) {
				getNumberofPieces(pieceSize, pieceStartX, pieceStartY);
			}
		}
		
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int sizeOfPaper = Integer.parseInt(br.readLine());
		paper = new int[sizeOfPaper][sizeOfPaper];
		
		for(int i=0; i<sizeOfPaper; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<sizeOfPaper; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		
		if(!isFullPaper(0, 0, sizeOfPaper)) {
			getNumberofPieces(sizeOfPaper, 0, 0);
		}
		System.out.println(minusOneCnt + "\n" + zeroCnt + "\n" + oneCnt);
	}
}
