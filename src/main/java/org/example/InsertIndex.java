package org.example;

import java.util.Arrays;

public class InsertIndex {

    public static int[] printNewArr(int arr[], int position, int insertIndex){
        int newArr[] = new int[arr.length+1];
        int i;
        for(i=0;i<newArr.length;i++){
            System.out.print(i +" ");
            if(i==position){
                newArr[i]=insertIndex;
                System.out.print("Insert index: "+newArr[i]);
            }else if(i<position){
                newArr[i]=arr[i];
                System.out.print("Element before target position: "+newArr[i]+ "");
            }else if(i>position){//i==3
                newArr[i]=arr[i-1];//arr[3-1], arr[4-1], arr[5-1]
                System.out.print("Element after target position: "+newArr[i]+ "");
            }
            System.out.println();
        };
        return newArr;
    }

    public static void main(String[] args) {
        int num[] = {3,8,6,5,4};
        int index = 10;
        int pos = 2;
        num = printNewArr(num,pos,index);
        System.out.println("New Array: "+Arrays.toString(num));

    }
}
