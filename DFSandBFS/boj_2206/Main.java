package boj_2206;

import java.io.*;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

class Point{
    public int x;
    public int y;
    public int moveCount;
    public Boolean hasWallBreak;
    
    public Point(int x, int y, int moveCount, Boolean hasWallBreak){
        this.x = x;
        this.y = y;
        this.moveCount = moveCount;
        this.hasWallBreak = hasWallBreak;
    }
}

public class Main{
    
    static Boolean[][][] isVisited;
    static Boolean[][] map; // true : 벽, false : 통로.
    static int mapHeight, mapLength;
    
    static void getMapInfoAndInitialize() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        
        mapHeight = Integer.parseInt(input[0]);
        mapLength = Integer.parseInt(input[1]);
        
        isVisited = new Boolean[2][mapHeight][mapLength];
        map = new Boolean[mapHeight][mapLength];
        
        for(int i=0; i<mapHeight; i++){
            Arrays.fill(isVisited[0][i], false);
            Arrays.fill(isVisited[1][i], false);
            String[] mapInput = br.readLine().split("");
            for(int j=0; j<mapLength; j++){
                if(mapInput[j].equals("0")){
                    map[i][j] = false; 
                }
                else{
                    map[i][j] = true;
                }
            }
        }  
        br.close();
    }
    
    static int getMinimumPath(){
        // BFS로 탐색해나가면서 찾기.
        Queue<Point> queue = new LinkedList<>();
        Point currentPoint = new Point(0,0,1,true);
        queue.add(currentPoint);
        
        int nextX, nextY;
        Point nextPoint;
        Boolean isEscapable = false;
        int[][] addDirection = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        int currentMoves = 1;
        
        while(!queue.isEmpty()){
            currentPoint = queue.poll();
            currentMoves = currentPoint.moveCount;
            
            if(isEndOfMap(currentPoint.x, currentPoint.y)){
                isEscapable = true;
                break;
            }
            
            for(int i=0; i<4; i++){
                nextX = currentPoint.x + addDirection[i][0];
                nextY = currentPoint.y + addDirection[i][1];
                if(isMoveable(nextX, nextY, currentPoint.hasWallBreak)){ //outofRange 랑 isVisited 검사
                    if(map[nextX][nextY] && currentPoint.hasWallBreak){
                        // 벽 깨고 다음거로
                        nextPoint = new Point(nextX, nextY, currentMoves+1, false);
                        queue.add(nextPoint);
                        isVisited[0][nextX][nextY] = true;
                    }
                    else if( !map[nextX][nextY] ){
                        // 지금상태 보존하고 다음거로 넘어가기
                        nextPoint = new Point(nextX, nextY, currentMoves+1, currentPoint.hasWallBreak);
                        queue.add(nextPoint);
                        if(currentPoint.hasWallBreak){
                            isVisited[1][nextX][nextY] = true;
                        }
                        else {
                            isVisited[0][nextX][nextY] = true;
                        }
                        
                    }
                }
            }
        }
        
        if(isEscapable){
            return currentMoves;
        }
        return -1;
    }
    
    static Boolean isEndOfMap(int x, int y){
        if(x == mapHeight-1 && y == mapLength-1){
            return true;
        }
        return false;
    }
    
    static Boolean isMoveable(int x, int y, Boolean hasWallBreak){
        if(isOutOfRange(x,y)){
            return false;
        }
        
        if(hasWallBreak){
            if(isVisited[1][x][y]){
                return false;
            }
        }
        else {
            if(isVisited[0][x][y]){
                return false;
            }
        }
        return true;
    }
    
    static Boolean isOutOfRange(int x, int y){
        if( x < 0 || x >= mapHeight){
            return true;
        }
        if( y < 0 || y >= mapLength){
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) throws IOException{
        
        getMapInfoAndInitialize();
        
        int answer = getMinimumPath();
        
        System.out.println(answer);
        
    }
}