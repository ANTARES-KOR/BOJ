package boj_2146;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    
    static int sizeOfMap;
    static int[][] map;
    static Boolean[][] isVisited;
    
    static Queue<Integer> x_queue = new LinkedList<>();
    static Queue<Integer> y_queue = new LinkedList<>();
    static Queue<Integer> depth_queue = new LinkedList<>();
    static Queue<Integer> color_queue = new LinkedList<>();
    
    static void printMap(){
        System.out.println("-------------------------");
        for(int i=0; i<sizeOfMap; i++){
            for(int j=0; j<sizeOfMap; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    static void getMapInfoAndInitialize() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        sizeOfMap = Integer.parseInt(br.readLine());
        String[] input;
        map = new int[sizeOfMap][sizeOfMap];
        isVisited = new Boolean[sizeOfMap][sizeOfMap];
        
        
        for(int i=0; i<sizeOfMap; i++){
            input = br.readLine().split(" ");
            for(int j=0; j<sizeOfMap; j++){
                map[i][j] = Integer.parseInt(input[j]);
            }
            
            Arrays.fill(isVisited[i], false);
        }
        
        br.close();
    }
    
    static void indicateDifferentContinents(){
        
        int color = 1;
        
        for(int i=0; i<sizeOfMap; i++){
            for(int j=0; j<sizeOfMap; j++){
                if(!isVisited[i][j] && map[i][j]>0){
                    dfs(i,j,color++);
                }
            }
        }
        
    }
    
    static void dfs(int x, int y, int color){
        isVisited[x][y] = true;
        map[x][y] = color;
        x_queue.add(x);
        y_queue.add(y);
        depth_queue.add(0);
        color_queue.add(color);
        
    
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
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
        if(map[x][y] == 0){
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
        // depth 저장용 맵을 하나 만들고( 시작 : -1 로 초기화 ) // isVisited 역할도 얘가 할수있을듯
        // 시작위치 저장용 맵을 하나 만들어야할듯?( 시작 : -1로 초기화)
        
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        
        int[][] depth_map = new int[sizeOfMap][sizeOfMap];
        int[][] color_map = new int[sizeOfMap][sizeOfMap];
        for(int i=0; i<sizeOfMap; i++){
            Arrays.fill(depth_map[i], -1);
            Arrays.fill(color_map[i], -1);
        }
        
        // queue에서 pull 할때마다 현재 위치의 depth = 현재 depth, 시작한 대륙 이름(1,2..) 기록해주기.
        int curX, curY, nextX, nextY, depth, color;
        int bridgeLength = 0;
        while(!x_queue.isEmpty()){
            curX = x_queue.poll();
            curY = y_queue.poll();
            depth = depth_queue.poll();
            color = color_queue.poll();
            
            // 만약에 현재 위치기 아미 방문한 위치라면(다리가 만났다면)
            if(depth_map[curX][curY] != -1 && color != color_map[curX][curY]){
                bridgeLength = depth + depth_map[curX][curY] - 1;
            }
            
            // 안만났다면
            depth_map[curX][curY] = depth;
            color_map[curX][curY] = color;
            
            for(int i=0; i<4; i++){
                nextX = curX + dx[i];
                nextY = curY + dy[i];
                if(isMovableToNextSea(nextX, nextY) && color_map[nextX][nextY] != color){
                    x_queue.add(nextX);
                    y_queue.add(nextY);
                    depth_queue.add(depth + 1);
                    color_queue.add(color);
                }
            }
            
        }
        
        return bridgeLength;
    }
    
    static Boolean isMovableToNextSea(int x, int y) {
        if(isOutOfRange(x, y)){
            return false;
        }
        if(map[x][y] != 0){
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) throws IOException {
        
        getMapInfoAndInitialize();
        indicateDifferentContinents();
        //printMap();
        
        System.out.println(getShortestBridgeLength());
    }
}




