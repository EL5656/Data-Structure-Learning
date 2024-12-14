package org.example.factorial;

public class Factorial {
    public int factorial(int num) {
        if (num == 0 || num == 1) {
            return 1;
        } else {
            return (num * factorial(num - 1));
        }
    }

    public int findFactorial(int num){
        int factorial = 1;
        for(int i=1; i<=num;i++){
            factorial *= i;
        }
        return factorial;
    }
}
