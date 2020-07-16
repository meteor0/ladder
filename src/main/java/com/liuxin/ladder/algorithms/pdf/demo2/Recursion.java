package com.liuxin.ladder.algorithms.pdf.demo2;

/**
 * @description:
 * @author: liuxin79
 * @date: 2020-05-27 11:22
 */
public class Recursion {

    public static void main(String[] args) {

        System.out.println(factorial1(4));
        System.out.println(factorial2(5));
        writeBinary(25);

    }

    /**
     * 乘阶!
     * 递归方法1
     * @param n
     * @return
     */
    private static long factorial1(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("");
        } else if (n == 0) {
            return 1;
        } else {
            return n * factorial1(n - 1);
        }
    }

    /**
     * 递归方法2
     * @param n
     * @return
     */
    private static long factorial2(int n) {
        int sum  = n;
        if (n < 0) {
            throw new IllegalArgumentException("");
        } else if (n <= 1) {
            return 1;
        } else {
          for(int i= n-1 ;i>1;i--){
              sum = sum * i;
          }
        }
        return sum;
    }

    /**
     * 十进制转二进制
     */
    private static void  writeBinary(int num){
        if (num < 0) {
            throw new IllegalArgumentException("");
        } else if (num <= 1) {
            System.out.print(num);
        } else {
            writeBinary(num /2);
            System.out.print(num%2);
        }
    }
}
