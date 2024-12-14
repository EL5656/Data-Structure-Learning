package org.example.factorial;

public class Main {
    public static void main(String[] args) {
        int num = 5;
        Factorial f = new Factorial();
        int recursive = f.factorial(num);
        System.out.println(recursive);
        int iterative = f.findFactorial(num);
        System.out.println(iterative);
    }
}
