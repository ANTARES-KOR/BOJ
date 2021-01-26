package boj_6549;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[] histogram = new int[100001];
	
	static long getLargestRectangle(int left, int right) {
		
		if(left == right) {
			return (long)histogram[left];
		}
		
		int mid = (left + right) / 2;
		long maxLeft = getLargestRectangle(left, mid);
		long maxRight = getLargestRectangle(mid+1, right);
		long ret = Math.max(maxLeft, maxRight);
		
		int height = Math.min(histogram[mid], histogram[mid + 1]);
		long area = (long)height * 2;
		
		int midLeft = mid-1, midRight = mid + 2;
		while(midLeft >= left || midRight <= right) {
			int smaller=0;
			if(midLeft >= left && midRight <= right) {
				smaller = (histogram[midLeft] >= histogram[midRight]) ? histogram[midLeft--] : histogram[midRight++];
			}
			else if(midLeft < left) {
				smaller = histogram[midRight++];
			}
			else {
				smaller = histogram[midLeft--];
			}

			height = Math.min(smaller, height);
			area = Math.max(area, (long)height * (midRight - midLeft - 1));
		}
		
		ret = Math.max(area, ret);
		return ret;
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int size;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			size = Integer.parseInt(st.nextToken());
			if(size == 0) {
				break;
			}
			
			Arrays.fill(histogram, 0);
			for(int i=0; i<size; i++) {
				histogram[i] = Integer.parseInt(st.nextToken());
			}
			
			bw.write( getLargestRectangle(0, size-1) + "\n" );
		}
		
		bw.flush();
		bw.close();
		br.close();

		
	}
}
