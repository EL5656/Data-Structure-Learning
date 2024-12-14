package org.example.recursive;

public class Main {
    public static void main(String[] args) {
        int num = 5;
        Factorial f = new Factorial();
        int result = f.findFactorial(5);
        System.out.println(result);
    }
}
