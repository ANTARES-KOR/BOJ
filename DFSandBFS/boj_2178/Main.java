
import java.io.*;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

class Point{
    private int x;
    private int y;
    private int moves;
    
    public Point(int x, int y, int moves){
        this.x = x;
        this.y = y;
        this.moves = moves;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getMoves(){
        return this.moves;
    }
}

public class Main {
    
    static int mazeHeight;
    static int mazeLength;
    static Boolean[][] isVisited;
    static String[][] maze;
    static Queue<Point> queue = new LinkedList<>();
    
    static void getMazeInfoandMap() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");    
        mazeHeight = Integer.parseInt(input[0]);
        mazeLength = Integer.parseInt(input[1]);
        
        maze = new String[mazeHeight][mazeLength];
        isVisited = new Boolean[mazeHeight][mazeLength];
        
        for(int i = 0; i < mazeHeight; i++){
            Arrays.fill(isVisited[i],false);
            maze[i] = br.readLine().split("");
        }
        
        br.close();
    }
    
    static int getMinimumMovesOutOfMaze(){
        // BFS로 0,0에서부터 탐색, 끝에 닿으면 해당깊이를 저장하고 break.
        
        Point currentPoint = new Point(0,0,1);
        queue.add(currentPoint);
        
        int x, y;
        int nextX, nextY;
        int currentMoves = 1;
        int[][] addNumberArr = { {0,1}, {0,-1}, {1,0}, {-1,0} };
        Point nextPoint;
        
        while( !queue.isEmpty() ){
            currentPoint = queue.poll();
            x = currentPoint.getX();
            y = currentPoint.getY();
            currentMoves = currentPoint.getMoves();
            
            if(isEndOfMaze(x, y)){
                break;
            }
            
            for(int i=0; i<4; i++){
                nextX = addNumberArr[i][0] + x;
                nextY = addNumberArr[i][1] + y;
                if(isMoveablePosition(nextX, nextY)){
                    nextPoint = new Point(nextX, nextY, currentMoves + 1); 
                    queue.add(nextPoint); 
                    isVisited[nextX][nextY] = true;
                }
            }
        }
        
        return currentMoves;
    }
    
    static Boolean isEndOfMaze(int x, int y){
        if( x == mazeHeight-1 && y == mazeLength-1 ){
            return true;
        }
        return false;
    }
    
    static Boolean isMoveablePosition(int x, int y){
        if(isXOutofRange(x)){
            return false;
        }
        else if(isYOutofRange(y)){
            return false;
        }
        else if(isVisited[x][y] || maze[x][y].equals("0")){
            return false;
        }
        return true;
    }
    
    static Boolean isXOutofRange(int x){
        if( x >= mazeHeight || x < 0 ){
            return true;
        }
        return false;
    }
    
    static Boolean isYOutofRange(int y){
        if( y >= mazeLength || y < 0 ){
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) throws IOException{
        
        getMazeInfoandMap();
        
        int minimumMoves = getMinimumMovesOutOfMaze();
        
        System.out.println(minimumMoves);
    }
}