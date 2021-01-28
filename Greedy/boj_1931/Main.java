package boj_1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.Arrays;

class Meeting {
	public int start, end;
	
	public Meeting(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

public class Main {

	static int numOfMeeting;
	static Meeting[] schedule;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		numOfMeeting = Integer.parseInt(br.readLine());
		schedule = new Meeting[numOfMeeting];
		for(int i=0; i<numOfMeeting; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			schedule[i] = new Meeting(start, end);
		}
		
		Arrays.sort(schedule, new Comparator<Meeting>() {
			public int compare(Meeting m1, Meeting m2) {
				if(m1.end != m2.end) {
					return Integer.compare(m1.end, m2.end);
				}
				else {
					return Integer.compare(m1.start, m2.start);
				}
			}
		});
		
		int meetingCnt=0;
		int previousMeetingEnd = -1;
		for(Meeting i : schedule) {
			
			if(i.start >= previousMeetingEnd) {
				meetingCnt++;
				previousMeetingEnd = i.end;
			}
			
		}
		
		System.out.println(meetingCnt);
	}
}
