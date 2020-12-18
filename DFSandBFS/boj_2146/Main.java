package boj_2146;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    
    static int sizeOfMap;
    static Boolean[][] map;
    static Boolean[][] isVisited;
    static int[][] depth_map;
    static int[][] color_map;
    
    static Queue<Integer> x_queue = new LinkedList<>();
    static Queue<Integer> y_queue = new LinkedList<>();
    static Queue<Integer> depth_queue = new LinkedList<>();
    static Queue<Integer> color_queue = new LinkedList<>();
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    static void getMapInfoAndInitialize() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        sizeOfMap = Integer.parseInt(br.readLine());
        String[] input;
        map = new Boolean[sizeOfMap][sizeOfMap];
        isVisited = new Boolean[sizeOfMap][sizeOfMap];
        depth_map = new int[sizeOfMap][sizeOfMap];
        color_map = new int[sizeOfMap][sizeOfMap];
        
        for(int i=0; i<sizeOfMap; i++){
            input = br.readLine().split(" ");
            for(int j=0; j<sizeOfMap; j++){
                if(input[j].equals("0")){
                    map[i][j] = false;
                }
                else {
                    map[i][j] = true;
                }
            }
            
            Arrays.fill(isVisited[i], false);
            Arrays.fill(depth_map[i], -1);
            Arrays.fill(color_map[i], -1);
        }
        
        br.close();
    }
    
    static void indicateDifferentContinents(){
        
        int color = 1;
        
        for(int i=0; i<sizeOfMap; i++){
            for(int j=0; j<sizeOfMap; j++){
                if(!isVisited[i][j] && map[i][j]){
                    dfs(i,j,color++);
                }
            }
        }
        
    }
    
    static void dfs(int x, int y, int color){
        isVisited[x][y] = true;
        x_queue.add(x);
        y_queue.add(y);
        depth_queue.add(0);
        color_queue.add(color);
        depth_map[x][y] = 0;
        color_map[x][y] = color;
    
        
        int nextX, nextY;
        
        for(int i=0; i<4; i++){
            nextX = x + dx[i];
            nextY = y + dy[i];
            
            if(isMoveableToNextContinent(nextX, nextY)){
                dfs(nextX, nextY, color);
            }
        }
        
    }
    
    static Boolean isMoveableToNextContinent(int x, int y){
        if(isOutOfRange(x, y)){
            return false;
        }
        if(isVisited[x][y]){
            return false;
        }
        if(!map[x][y]){
            return false;
        }
        return true;
    }
    
    static Boolean isOutOfRange(int x, int y){
        if(x < 0 || x >= sizeOfMap){
            return true;
        }
        if(y < 0 || y >= sizeOfMap){
            return true;
        }
        return false;
    }
    
    static int getShortestBridgeLength(){
   
        // queue에서 pull 할때마다 현재 위치의 depth = 현재 depth, 시작한 대륙 이름(1,2..) 기록해주기.
        int curX, curY, nextX, nextY, depth, color;
        int bridgeLength = 200;
        
        while(!x_queue.isEmpty()){
            curX = x_queue.poll();
            curY = y_queue.poll();
            depth = depth_queue.poll();
            color = color_queue.poll();
            
            
            // 안만났다면
            //depth_map[curX][curY] = depth;
            //color_map[curX][curY] = color;
            
            for(int i=0; i<4; i++){
                nextX = curX + dx[i];
                nextY = curY + dy[i];
                if(isMovableToNextSea(nextX, nextY) && color_map[nextX][nextY] != color){
                    
                    if(color_map[nextX][nextY] != -1){
                        bridgeLength = Math.min(depth + depth_map[nextX][nextY], bridgeLength);
                    }
                    
                    if(bridgeLength == 200) {
                        
                        x_queue.add(nextX);
                        y_queue.add(nextY);
                        depth_queue.add(depth + 1);
                        color_queue.add(color);
                    
                        depth_map[nextX][nextY] = depth + 1;
                        color_map[nextX][nextY] = color;
                        
                    }
                }
            }

        }
        
        return bridgeLength;
    }
    
    static Boolean isMovableToNextSea(int x, int y) {
        if(isOutOfRange(x, y)){
            return false;
        }
        if(map[x][y]){
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) throws IOException {
        
        getMapInfoAndInitialize();
        indicateDifferentContinents();
        
        System.out.println(getShortestBridgeLength());
    }
}




