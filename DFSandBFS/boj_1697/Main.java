package boj_1697;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
    
    static int subinPosition;
    static int sisterPosition;
    static Boolean[] isVisited = new Boolean[100001];
    
    static int getMinimumSecondFindingSister(){
        
        Arrays.fill(isVisited, false);
        Queue<Integer> queue_pos = new LinkedList<>();
        Queue<Integer> queue_sec = new LinkedList<>();
        
        queue_pos.add(subinPosition);
        queue_sec.add(0);
        isVisited[subinPosition] = true;
        
        int currentPosition;
        int nextPosition;
        int secondPassed = 0;
        
        while(!queue_pos.isEmpty()){
            currentPosition = queue_pos.poll();
            secondPassed = queue_sec.poll();
            
            if(currentPosition == sisterPosition){
                break;
            }
            
            nextPosition = currentPosition - 1;
            if(isMoveablePos(nextPosition)){
                isVisited[nextPosition] = true;
                queue_pos.add(nextPosition);
                queue_sec.add(secondPassed + 1);
            }
            
            nextPosition = currentPosition + 1;
            if(isMoveablePos(nextPosition)){
                isVisited[nextPosition] = true;
                queue_pos.add(nextPosition);
                queue_sec.add(secondPassed + 1);
            }
            
            nextPosition = currentPosition * 2;
            if(isMoveablePos(nextPosition)){
                isVisited[nextPosition] = true;
                queue_pos.add(nextPosition);
                queue_sec.add(secondPassed + 1);
            }
        }
        return secondPassed;
    }
    
    static Boolean isMoveablePos(int pos){
        if(pos < 0 || pos > 100000){
            return false;
        }
        else if(isVisited[pos]){
            return false;
        }
        return true;
    }
    
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        subinPosition = scan.nextInt();
        sisterPosition = scan.nextInt();
        
        scan.close();
        
        // 가장 빠른~ 이란 말이 나오면 BFS로 찾으면 될듯.
        int answer = getMinimumSecondFindingSister();
        
        System.out.println(answer);
    }
}