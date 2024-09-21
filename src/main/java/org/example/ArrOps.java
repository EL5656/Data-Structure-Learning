package org.example;

import java.util.Arrays;

public class ArrOps {

    public static int[] insertNewElement(int arr[], int position, int insertIndex){
        int newArr[] = new int[arr.length+1];
        for(int i=0;i<newArr.length;i++){
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

    public static int getIndex(int arr[], int num){//pass param
        for(int i=0;i<arr.length;i++){
            if(arr[i]==num){
                return i;
            }
        }
        return -1;
    }

    public static int[] removeElementAtIndex(int arr[], int position){
        int newArr[] = new int[arr.length-1];
        for(int i=0; i<newArr.length;i++){
            if(i>=position){
                newArr[i]= arr[i+1];
            }else if(i<position){
                newArr[i]= arr[i];
            }
        };
        return newArr;
    }

    public static void main(String[] args) {
        int num[] = {3,8,6,5,4};
        int index = 10;
        int pos = 2;
        num = insertNewElement(num,pos,index);
        System.out.println("New Array: "+Arrays.toString(num));

        int searchNum = 6;
        int searchIndex = getIndex(num,searchNum);
        System.out.println("Index of "+searchNum+" is "+searchIndex);

        int delIndex = 3;
        int newNum[] = removeElementAtIndex(num,delIndex);
        System.out.println("After remove element: "+Arrays.toString(newNum));
    }
}
/*
0 Element before target position: 3
1 Element before target position: 8
2 Insert index: 10
3 Element after target position: 6
4 Element after target position: 5
5 Element after target position: 4
New Array: [3, 8, 10, 6, 5, 4]
Index of 6 is 3
After remove element: [3, 8, 10, 5, 4]

Process finished with exit code 0
 */