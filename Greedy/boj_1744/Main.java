package boj_1744;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    
    static int lengthOfArr;
    static int[] arr;
    static int[] numberOf = {0,0,0,0};
    static final int negativeNum = 0, zero = 1, one = 2, biggerThanOne = 3;


    static void countNum(int number){
        if(number < 0){
            numberOf[negativeNum]++;
        }
        else if(number == 0){
            numberOf[zero]++;
        }
        else if(number == 1){
            numberOf[one]++;
        }
        else {
            numberOf[biggerThanOne]++;
        }
    }
    static int getMaxSumFromArr(){
        long sum = 0;
        int index = 0;
        int addingValue = 0;  
        
        if(numberOf[biggerThanOne] > 0){
            index = lengthOfArr - 1;
            while(index >= 0 && arr[index]>1){
                if(addingValue == 0){
                    addingValue = arr[index--];
                }
                else {
                    addingValue *= arr[index--];
                    sum += addingValue;
                    addingValue = 0;
                }
            }
            sum += addingValue;
        }

        if(numberOf[negativeNum] > 0){
            index = 0;
            addingValue = 0;
            while(index < lengthOfArr && arr[index] <= 0){
                if(addingValue == 0){
                    addingValue = arr[index++];
                }
                else {
                    addingValue *= arr[index++];
                    sum += addingValue;
                    addingValue = 0;
                }
            }
            sum += addingValue;
        }

        sum += numberOf[one];

        return (int)sum;
    }
    public static void main(String[] args) throws IOException{

        Scanner scan = new Scanner(System.in);
        lengthOfArr = scan.nextInt();
        arr = new int[lengthOfArr];

        for(int i=0; i<lengthOfArr; i++){
            arr[i] = scan.nextInt();
            countNum(arr[i]);
        }

        Arrays.sort(arr);
        System.out.println( getMaxSumFromArr() );
        scan.close();
    }
}
