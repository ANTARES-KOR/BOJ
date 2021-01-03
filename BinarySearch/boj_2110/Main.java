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
		int left = 1;
		int right = (maxPos - minPos) / (numOfWifi-1) + 1;
		int mid;
		int ret = 1000000001;

		while(left <= right){
			mid = (left + right + 1) / 2; // 점프해볼 거리

			int closestDist = jump(mid);

			// 공유기가 부족한 경우 점프거리를 줄여야함
			if(closestDist == -1){
				right = mid - 1;
			}
			// 공유기가 남는 경우 점프거리를 늘려야함. 
			else if(closestDist == -2){
				left = right + 1;
			}
			// 공유기 개수가 딱 맞는 경우 현재의 최인접 최대거리를 저장하고 점프거리를 늘려봄.
			else {
				ret = Math.min(ret, closestDist);
				left = right + 1;
			}
		}

		return ret;

	}

	static int jump(int dist){
		int pos = housePos[0];
		int lastPos = housePos[0];
		int closestDist = 1000000001;
		int wifiCnt = 0;
		for(int i=0; i<numOfHouse; i++){
			if(housePos[i] >= pos){
				pos = housePos[i] + dist;
				wifiCnt++;
				if(i != 0){
					closestDist = Math.min(closestDist, housePos[i] - lastPos);
					lastPos = housePos[i];
				}
			}
		}

		if(wifiCnt > numOfWifi){
			closestDist = -1;
		}
		else if(wifiCnt < numOfWifi){
			closestDist = -2;
		}
		return closestDist;
	}

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		numOfHouse = Integer.parseInt(st.nextToken());
		numOfWifi = Integer.parseInt(st.nextToken());

		housePos = new int[numOfHouse];
		for(int i=0; i<numOfHouse; i++){
			housePos[i] = Integer.parseInt(br.readLine());
		}

		br.close();
		System.out.println( getMaxDistBetweenClosestWifi() );
	}
}