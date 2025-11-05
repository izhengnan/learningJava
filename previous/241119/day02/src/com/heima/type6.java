package com.heima;

import java.util.Scanner;

public class type6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入想检索的素数的最大值：");
        int num = sc.nextInt();
        for (int i = 0;i<=num;i++){
            for(int j = 2; j <=i;j++){
                if(i==j){
                    System.out.println(i);
                }else if(i%j==0){
                    break;
                }
            }
        }
    }
}
