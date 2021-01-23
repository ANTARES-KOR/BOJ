package boj_1517;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] arr ; 
	static long swapCnt=0;
	
	static int[] mergeSort(int start, int end) {
		
		int[] ret = new int[end-start+1];
		if(start == end) {
			ret[0] = arr[start];
			return ret; 
		}
		
		int mid = (start + end) / 2;
		int[] arr1 = mergeSort(start, mid);
		int[] arr2 = mergeSort(mid + 1, end);
		
		int index = 0, index1 = 0, index2 = 0;
		int inputNum = 0;
		
		// 이제 여기서 자리바꿀때다 swapCnt를 늘려주면 되는데 그 공식에 대해 고민이 필요함.
		while(index < ret.length) {
			if(index1 < arr1.length && index2 < arr2.length) {
				if(arr1[index1] <= arr2[index2]) {
					inputNum = arr1[index1++];
				}
				else {
					swapCnt += (arr1.length - index1);
					inputNum = arr2[index2++];
				}
			}
			else if(index1 >= arr1.length ) {
				inputNum = arr2[index2++];
			}
			else {
				inputNum = arr1[index1++];
			}
			
			ret[index++] = inputNum;
		}
		
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int length = Integer.parseInt(br.readLine());
		arr = new int[length];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
			
		mergeSort(0, length-1);
		
		System.out.println(swapCnt);
		br.close();
	}
}
