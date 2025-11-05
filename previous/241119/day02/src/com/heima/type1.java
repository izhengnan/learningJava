package com.heima;
import java.util.Scanner;

public class type1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您的姓名：");
        String name = sc.nextLine();
        System.out.println("请输入您的性别(1.男 2.女)：");
        int sex = sc.nextInt();
        System.out.println("请输入您的年龄：");
        int age = sc.nextInt();
        System.out.println("请输入您的体重(kg)：");
        int weight = sc.nextInt();
        System.out.println("请输入您的身高(m)：");
        double height = sc.nextDouble();
        double BMI=weight/(height*height);
        double BMR=sex==1?88.36 + (13.4 *weight) + (4.8 *height*100) - (5.7 *age):447.6 + (9.2 *weight) + (3.1 *height*100) - (4.3 *age);
        System.out.println("您的姓名是"+name+"，性别是"+(sex==1?"男":"女")+"，年龄是"+age+"，体重是"+weight+"kg，身高是"+height+"m，BMI是"+BMI+"，基础代谢率是"+BMR+"大卡");

    }

}
