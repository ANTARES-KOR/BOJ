import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{

	static int numOfTrees;
	static int[] treeLength;

	static int getMaxCuttingHeight(int pivot){

		Arrays.sort(treeLength);
		int left = 1, right = treeLength[numOfTrees-1];
		int mid;
		int ret = 0;

		while(left <= right){
			mid = (left + right + 1)/2;
			long lengthCnt = 0;
			for(int i=numOfTrees-1; i>=0; i--){
				if(treeLength[i] >= mid){
					lengthCnt += (treeLength[i] - mid);
				}
				else {
					break;
				}
			}

			if(lengthCnt >= pivot){
				left = mid + 1;
				ret = Math.max(mid, ret);
			}
			else {
				right = mid - 1;
			}
		}

		return ret;
	}
	

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		numOfTrees = Integer.parseInt(st.nextToken());
		int requiredLength = Integer.parseInt(st.nextToken());
		

		treeLength = new int[numOfTrees];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<numOfTrees; i++){
			treeLength[i] = Integer.parseInt(st.nextToken());
		}
		br.close();

		System.out.println( getMaxCuttingHeight(requiredLength) );


	}
}