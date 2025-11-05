package com.itheima;
import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i =sc.nextInt();
        int j =sc.nextInt();
        System.out.println(sum(i,j));
    }
    //写一个1-100的输出
    public static int sum(int a, int b){
        return a+b;
    }

}
