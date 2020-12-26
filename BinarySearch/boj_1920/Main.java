package boj_1920;

import java.io.*;
import java.util.Arrays;


public class Main {
    
    static int n;
    static int[] arr;
    
    static void changeInputToArrays(String[] input){
        arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(input[i]);
        }
    }
    
    static Boolean isInArr(int number){
        
        int startPos = 0, endPos = n-1;
        Boolean isNumInArr = false;
        
        while(startPos != endPos){
            int curPos = (startPos + endPos + 1)/2;
            if(arr[curPos] == number){
                isNumInArr = true;
                break;
            }
            else if(arr[curPos] > number){
                endPos = curPos-1;
            }
            else {
                startPos = curPos;
            }
        }
        
        if(arr[startPos] == number){
            isNumInArr = true;
        }
        
        return isNumInArr;
        
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        changeInputToArrays(br.readLine().split(" "));
        
        Arrays.sort(arr);
        
        int m = Integer.parseInt(br.readLine());
        String[] candidates = br.readLine().split(" ");
        for(int i=0; i<m; i++){
            if(isInArr(Integer.parseInt(candidates[i]))){
                bw.write("1\n");
            }
            else{
                bw.write("0\n");
            }
        }
        
        br.close();
        bw.flush();
    }
}