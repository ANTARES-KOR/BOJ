package boj_11728;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] A = new int[N+1];
		int[] B = new int[M+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		A[N] = 2000000000;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		B[M] = 2000000000;
		
		Arrays.sort(A);
		Arrays.sort(B);
		
		int indexA=0, indexB=0;
		while(indexA<A.length-1 || indexB<B.length-1) {
			if(A[indexA] < B[indexB]) {
				bw.write(A[indexA++] + " ");
			}
			else {
				bw.write(B[indexB++] + " ");
			}
		}
		br.close();
		bw.flush();
		bw.close();
	}
}
