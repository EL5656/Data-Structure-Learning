package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] a = { 5, 14, 22, 30, 31, 38, 41, 44 };
        BinarySearch bs = new BinarySearch();

        Scanner sc= new Scanner(System.in);
        System.out.print("Enter a number: ");
        int x = sc.nextInt();

        int res = bs.BinarySearch(a, x);
        if (res == -1)
            System.out.println(
                    "Element is not present in array");
        else
            System.out.println("Element is present at "
                    + "index " + res);
    }
}