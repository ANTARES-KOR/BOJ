import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int numOfHouse, numOfWifi;
	static int[] housePos;

	static int getMaxDistBetweenClosestWifi(){
		Arrays.sort(housePos);
		int minPos = housePos[0];
		int maxPos = housePos[numOfHouse-1];
		int properDist = (maxPos - minPos) / numOfWifi;

		// dist 이상인 처음의 위치를 선택한다.

	}

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		numOfHouse = Integer.parseInt(st.nextToken());
		numOfWifi = Integer.parseInt(st.nextToken);

		housePos = new int[numOfHouse];
		for(int i=0; i<numOfHouse; i++){
			housePos[i] = Integer.parseInt(br.readLine());
		}

		br.close();
		System.out.println( getMaxDistBetweenClosestWifi() );
	}
}