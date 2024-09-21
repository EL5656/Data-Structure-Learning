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
/*
Initial: [3, 8, 6, 5, 4]
0 Element before target position: 3
1 Element before target position: 8
2 Insert index: 10
3 Element after target position: 6
4 Element after target position: 5
5 Element after target position: 4
Output: [3, 8, 10, 6, 5, 4]

Process finished with exit code 0
 */