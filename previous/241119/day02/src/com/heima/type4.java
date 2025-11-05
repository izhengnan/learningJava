package com.heima;

import java.util.Random;
import java.util.Scanner;

public class type4 {
    public static void main(String[] args) {
        guess();
    }
    public static void guess(){
        Random r = new Random();
        int num =r.nextInt(100)+1;
//        System.out.println(num);
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("请输入你猜的数字：");
            int guess =sc.nextInt();
            if(guess==num){
                System.out.println("恭喜你猜对了，游戏结束");
                break;
            }else if(guess<num){
                System.out.println("猜小了，再猜一次");
            }else if(guess>num){
                System.out.println("猜大了，再猜一次");
            }
        }
    }
}
