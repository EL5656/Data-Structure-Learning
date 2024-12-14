package org.example.factorial;

public class Main {
    public static void main(String[] args) {
        int num = 5;
        Factorial f = new Factorial();
        int recursive = f.factorial(5);
        System.out.println(recursive);
        int iterative = f.findFactorial(5);
        System.out.println(iterative);
    }
}
