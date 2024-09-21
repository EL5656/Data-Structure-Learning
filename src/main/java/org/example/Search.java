package org.example;

public class Search {
    public int binarySearch(int a[], int target){
        System.out.println("Binary search");
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

    public int linearSearch(int a[], int target){
        System.out.println("Linear search");
        for(int i=0; i<a.length;i++){
            if(a[i]==target){
                System.out.println(i);
                return i;
            }
        }
        return -1;
    }
}
