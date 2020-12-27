package boj_10816;

import java.io.*;
import java.util.Arrays;


public class Main {
    
    static int n;
    static int[] arr;
    static int[] arrNumberCount = new int[20000010];
    
    static void changeInputToArrays(String[] input){
        arr = new int[n];
        Arrays.fill(arrNumberCount, 0);
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(input[i]);
            arrNumberCount[arr[i] + 10000000]++;
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
                bw.write(arrNumberCount[Integer.parseInt(candidates[i]) + 10000000] + " ");
            }
            else{
                bw.write("0 ");
            }
        }
        
        br.close();
        bw.flush();
    }
}