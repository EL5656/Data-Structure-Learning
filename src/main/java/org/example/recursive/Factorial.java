package org.example.recursive;

public class Factorial {
    public int findFactorial(int num) {
        if (num == 0 || num == 1) {
            return 1;
        } else {
            return (num * findFactorial(num - 1));
        }
    }
}
