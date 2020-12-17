package boj_4963;

import java.io.*;
import java.util.Arrays;

public class Main {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int mapLength, mapHeight;
    static Boolean[][] map;
    static Boolean[][] isVisited;
    
    static void getMapAndInitialize() throws IOException{
        String[] mapInput;
        
        map = new Boolean[mapHeight][mapLength];
        isVisited = new Boolean[mapHeight][mapLength];
        
        for(int i = 0; i < mapHeight; i++){
            mapInput = br.readLine().split(" ");
            for(int j = 0; j < mapLength; j++){
                if(mapInput[j].equals("0")){
                    map[i][j] = false;
                }
                else {
                    map[i][j] = true;
                }
            }
            
            Arrays.fill(isVisited[i], false);
        }
    }
    
    static int getNumberOfIslands(){
        
        int islandCount = 0;
        
        for(int i=0; i<mapHeight; i++){
            for(int j=0; j<mapLength; j++){
                if(map[i][j] && !isVisited[i][j]){
                    islandCount++;
                    dfs(i,j);
                    //System.out.println(islandCount);
                }
            }
        }
        
        return islandCount;
    }
    
    static void dfs(int x, int y){
        isVisited[x][y] = true;
    
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        int nextX, nextY;
        
        for(int i=0; i<8; i++){
            nextX = x + dx[i];
            nextY = y + dy[i];
            
            if(isMoveable(nextX, nextY)){
                dfs(nextX, nextY);
            }
        }
        
    }
    
    static Boolean isMoveable(int x, int y){
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
        if(x < 0 || x >= mapHeight){
            return true;
        }
        if(y < 0 || y >= mapLength){
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) throws IOException{
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] mapInfo;
        
        do{
            mapInfo = br.readLine().split(" ");
            mapLength = Integer.parseInt(mapInfo[0]);
            mapHeight = Integer.parseInt(mapInfo[1]);
            if(mapHeight == 0 && mapLength == 0){
                break;
            }
            getMapAndInitialize();
            bw.write(getNumberOfIslands() + "\n");
            
        }while(true);
        
        bw.flush();
        br.close();
    }
}