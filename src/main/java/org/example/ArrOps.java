package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class ArrOps {
    public static void main(String[] args) {
        int num[] = {3,8,6,5,4};
        int insertIndex = 10;
        int position = 2;
        int newArr[] = new int[num.length+1];

        System.out.println("Initial: "+Arrays.toString(num));

        for(int i=0;i<newArr.length;i++){
            System.out.print(i +" ");
            if(i==position){
                newArr[i]=insertIndex;
                System.out.print("Insert index: "+newArr[i]);
            }else if(i<position){
                newArr[i]=num[i];
                System.out.print("Element before target position: "+newArr[i]+ "");
            }else if(i>position){//i==3
                newArr[i]=num[i-1];//num[3-1], num[4-1], num[5-1]
                System.out.print("Element after target position: "+newArr[i]+ "");
            }
            System.out.println();
        }
        System.out.println("Output: "+Arrays.toString(newArr));

    }
}
