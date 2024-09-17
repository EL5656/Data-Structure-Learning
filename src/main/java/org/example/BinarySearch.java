package org.example;

import java.util.Scanner;

public class BinarySearch {

    public int BinarySearch(int a[], int target){
        int i=0; int j=a.length-1;

        while(j>=i){
            int m=(i+j)/2;
            if(target<a[m]){
                j = m - 1;
            }else if(target>a[m]){
                i = m +1;
            }else{
                return m;
            }
        }

        return -1;
    }

}
