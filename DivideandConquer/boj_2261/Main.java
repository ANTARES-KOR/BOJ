package boj_2261;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Point {
	public int x, y;
	
	public Point(int x, int y) {
		this.x= x;
		this.y = y;
	}
	
	public int getSquareDistance(Point p) {
		return (int)Math.pow( (p.x-this.x), 2 ) + (int)Math.pow( (p.y-this.y), 2);
	}
	
}

public class Main {
	
	static int numOfPoints;
	static Point[] arr;
	static int closestDist = 1000000001;
	
	static double getClosestDistance(int start, int end){
		
		// base case
		if(start == end) {
			return 100000;
		}
		
		// divide
		int mid = (start+end)/2;
		double dl = getClosestDistance(start, mid);
		double dr = getClosestDistance(mid + 1, end);
		double d = Math.min(dl, dr);
		
		// conquer
		
		// 아 구현하기싫다ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
		// 기준선을 정하구
		int pivot = (arr[mid].x + arr[mid+1].x)/2;
		// 기준선 기준 d사이에 있는 애들 구하구
		int leftBound = 0, rightBound = 0;
		for(int i=mid; i>=start; i--) {
			if( Math.abs(pivot - arr[i].x) <= d ) {
				leftBound = i;
			}
		}
		for(int j=mid; j<=end; j++) {
			if( Math.abs(pivot - arr[j].x) <= d ) {
				rightBound = j;
			}
		}
		// 걔들을 y축 순서대로 가져와서
		Point[] pivotBand = Arrays.copyOfRange(arr, leftBound, rightBound+1);
		Arrays.sort(pivotBand, new Comparator<Point>() {
			public int compare(Point p1, Point p2) {
				return Integer.compare(p1.y, p2.y);
			}
		});
		// 거리비교해서
		int squareDist = 0;
		double dc = 100000;
		for(int i=0; i<pivotBand.length-1; i++) {
			for(int j=i+1; j<pivotBand.length; j++) {
				if(pivotBand[j].y - pivotBand[i].y >= d) {
					break;
				}
				squareDist = pivotBand[i].getSquareDistance(pivotBand[j]);
				closestDist = Math.min(squareDist, closestDist);
				dc = Math.min(dc, Math.sqrt((double)squareDist));
			}
		}
		// d랑 비교해서 더 작은게
		d = Math.min(d, dc);
		// 그 단계의 최솟값이다~ 이말이야.
		return d;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		numOfPoints = Integer.parseInt(br.readLine());
		arr = new Point[numOfPoints];
		
		for(int i=0; i<numOfPoints; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[i] = new Point(x, y);
		}
		
		Arrays.sort(arr, new Comparator<Point>() {
			public int compare(Point p1, Point p2) {
				return Integer.compare(p1.x, p2.x);
			}
		});
		
		getClosestDistance(0, numOfPoints-1);
		System.out.println(closestDist);
	}
}
