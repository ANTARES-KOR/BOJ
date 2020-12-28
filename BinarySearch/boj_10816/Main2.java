

import java.io.*;
import java.util.Arrays;


public class Main2 {
    
    static int n;
    static int[] arr;
    
    static void changeInputToArrays(String[] input){
        arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(input[i]);
            
        }
    }
    
    static int howManyInArr(int number){
        
        int leftBound = 0, rightBound = 0;
        int startPos=0, endPos = n-1, curPos = 0;
        
        
        // leftBound 찾기
        while(startPos != endPos){
            curPos = (startPos + endPos + 1)/2;
            if(arr[curPos] == number){
                if(curPos == 0 || arr[curPos-1] != number){
                    leftBound = curPos;
                    break;
                }
                endPos = curPos-1;
            }
            else if(arr[curPos] > number){
                endPos = curPos-1;
            }
            else{
                startPos = curPos;
            }
        }
        
    
        // rightBound 찾기
        startPos = 0;
        endPos = n-1;
        while(startPos != endPos){
            curPos = (startPos + endPos + 1)/2;
            if(arr[curPos] == number){
                if(curPos == n-1 || arr[curPos+1] != number){
                    rightBound = curPos;
                    break;
                }
                startPos = curPos;
            }
            else if(arr[curPos] > number){
                endPos = curPos-1;
            }
            else{
                startPos = curPos;
            }
        }
        
        if(startPos == endPos){
            if(arr[startPos] == number){
                return 1;
            }
            return 0;
        }
        
        if(rightBound == 0 && leftBound == 0){
            return 0;
        }
        return rightBound - leftBound + 1;
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
            bw.write( howManyInArr(Integer.parseInt(candidates[i])) + " " );
        }
        
        br.close();
        bw.flush();
    }
}