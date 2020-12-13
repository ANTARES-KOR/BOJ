package boj_2331;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;



public class Main{
    

    static ArrayList<Integer> nodes = new ArrayList<Integer>();
    
    public static void main(String[] args){
        
        Scanner scan = new Scanner(System.in);
        
        int a = scan.nextInt();
        int p = scan.nextInt();
        int numberCnt = 0;
        int d = a;
        
        nodes.add(d);
        
        do{
            int newNum = 0;
            String prevNum = Integer.toString(d);
            for(int i=0; i<prevNum.length(); i++){
                newNum += Math.pow((int)(prevNum.charAt(i) - '0'), p);
            }
            
            if(nodes.contains(newNum)){
                numberCnt = nodes.indexOf(newNum);
                break;
            }
            else {
                nodes.add(newNum);
            }
            
            d = newNum;
            
        }while(true); 
        
        System.out.println(numberCnt);
        scan.close();
        
    }
}