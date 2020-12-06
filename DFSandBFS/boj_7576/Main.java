
import java.io.*;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Tomato {
    private int x;
    private int y;
    private int daysPassed;
    
    public Tomato(int x, int y, int days){
        this.x = x;
        this.y = y;
        this.daysPassed = days;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getDays(){
        return daysPassed;
    }
}

public class Main {
    
    static int houseLength;
    static int houseHeight;
    static Boolean[][] isVisited;
    static int[][] tomatoHouse;
    static int ripeTomatoNumber = 0;
    static int emptySpaceNumber = 0;
    static Queue<Tomato> queue = new LinkedList<>();
    
    static void getTomatoHouseInfoandMap() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        
        houseLength = Integer.parseInt(input[0]);
        houseHeight = Integer.parseInt(input[1]);
        isVisited = new Boolean[houseHeight][houseLength];
        tomatoHouse = new int[houseHeight][houseLength];
        
        // 처음부터 익은 토마토가 있는 부분은 큐에다가 add.
        Tomato ripenedTomato;
        
        for(int i=0; i<houseHeight; i++){
            Arrays.fill(isVisited[i], false);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; st.hasMoreTokens(); j++){
                tomatoHouse[i][j] = Integer.parseInt(st.nextToken());
                if(tomatoHouse[i][j] == 1){
                    ripenedTomato = new Tomato(i,j,0);
                    ripeTomatoNumber++;
                    queue.add(ripenedTomato);
                    isVisited[i][j] = true;
                }
                else if(tomatoHouse[i][j] == -1){
                    emptySpaceNumber++;
                }
            }
        }
        
        br.close();
    }
    
    static int getLeastDaysToRipenTomatoes(){
        int totalTomatoNumber = houseHeight * houseLength - emptySpaceNumber;
        if(totalTomatoNumber == ripeTomatoNumber){
            return 0;
        }
        
        Tomato tomato;
        Tomato nextTomato;
        int x, y;
        int nextX, nextY;
        int currentDaysPassed = 0;
        int[][] addNumberArr = { {0,1}, {0,-1}, {1,0}, {-1,0} };
        
        while(!queue.isEmpty()){
            tomato = queue.poll();
            x = tomato.getX();
            y = tomato.getY();
            currentDaysPassed = tomato.getDays();
            
            for(int i=0; i<4; i++){
                nextX = addNumberArr[i][0] + x;
                nextY = addNumberArr[i][1] + y;
                if(isRipenAbleTomato(nextX, nextY)){
                    nextTomato = new Tomato(nextX, nextY, currentDaysPassed + 1);
                    queue.add(nextTomato);
                    isVisited[nextX][nextY] = true;
                    ripeTomatoNumber++;
                }
            }
        }
        
        if(totalTomatoNumber > ripeTomatoNumber ){
            return -1;
        }
        return currentDaysPassed;
    }
    
    static Boolean isRipenAbleTomato(int x, int y){
        if(isXOutofRange(x)){
            return false;
        }
        else if(isYOutofRange(y)){
            return false;
        }
        else if(isVisited[x][y] || tomatoHouse[x][y] == -1){
            return false;
        }
        return true;
    }
    
    static Boolean isXOutofRange(int x){
        if( x >= houseHeight || x < 0 ){
            return true;
        }
        return false;
    }
    
    static Boolean isYOutofRange(int y){
        if( y >= houseLength || y < 0 ){
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) throws IOException{
        
        // 익은 토마토들에서 시작해서 BFS해나가면 될문제!
        getTomatoHouseInfoandMap();
        
        int daysToRipenTomatoes = getLeastDaysToRipenTomatoes();
        
        System.out.println(daysToRipenTomatoes);
    }
}