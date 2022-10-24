package com.example.kotlin1.helpers;

public class MathHelper {

    public static int nthFibonacciTerm(int n) {
        if (n == 1 || n == 0) {
            return n;
        }
        return nthFibonacciTerm(n-1) + nthFibonacciTerm(n-2);
    }

    public static int getCollatzNumber(int num){
        if (num % 2 == 0) {
            num /= 2;
        } else {
            num = 3 * num + 1;
        }
        return num;
    }
}
