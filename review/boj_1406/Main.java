package boj_1406;

import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st; 

	static LinkedList<String> word = new LinkedList<>();
	
	static void textEdit()throws IOException {
		
		ListIterator<String> itr = word.listIterator();
		
		while(itr.hasNext()) {
			itr.next();
		}
		
		int numOfRequests = Integer.parseInt(br.readLine());
		String command, inputChar;
		
		for(int i=0; i<numOfRequests; i++) {
			st = new StringTokenizer(br.readLine());
			command = st.nextToken();
			
			if(command.equals("L")) {
				if(itr.hasPrevious()) {
					itr.previous();
				}
			}
			else if(command.equals("B")) {
				if(itr.hasPrevious()) {
					itr.previous();
					itr.remove();
				}
			}
			else if(command.equals("D")) {
				if(itr.hasNext()) {
					itr.next();
				}
			}
			else {
				inputChar = st.nextToken();
				itr.add(inputChar);
			}
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		
		String[] input = br.readLine().split("");
		
		for(String i : input) {
			word.add(i);
		}
		
		textEdit();
		
		for(String i : word) {
			bw.write(i);
		}
		
		bw.flush();
		bw.close();
		br.close();
		
	}
}
