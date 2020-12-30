package boj_1654;

import java.io.*;
import java.util.StringTokenizer;

public class Main{
    
    static int[] cableLength;
    static int numOfCable;
    static int numOfNeededCable;
    
    static long getMaxLengthOfCable(long maxLength){
        
        long currentSegmentLength = (maxLength + 1)/2;
        
        long left = 1, right = maxLength;
        long ret = 0;
        while(left <= right){ ////////////이부분의 로직!!!!!!!!!!!!!!!!여기가 진짜 거짓말안치고 왜틀리고 왜맞는건지 1도모르겠음
            currentSegmentLength = (left + right) / 2;
            long currentNumOfCables = 0;
            for(int i=0; i<numOfCable; i++){
                currentNumOfCables += cableLength[i] / currentSegmentLength;
                if(currentNumOfCables > numOfNeededCable){
                    break;
                }
            }
            
            if(currentNumOfCables >= numOfNeededCable){
                left = currentSegmentLength+1;
                ret = Math.max(ret, currentSegmentLength);
            }
            else{
                right = currentSegmentLength-1; 
            }
        }    
        return right;
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        numOfCable = Integer.parseInt(st.nextToken());
        numOfNeededCable = Integer.parseInt(st.nextToken());
        
        long maxCableLength=0;
        cableLength = new int[numOfCable];
        for(int i=0; i<numOfCable; i++){
            cableLength[i] = Integer.parseInt(br.readLine()); 
            maxCableLength = Math.max(maxCableLength, cableLength[i]);
        }
        
        
        System.out.println( getMaxLengthOfCable(maxCableLength) );
        br.close();
        
    }
}