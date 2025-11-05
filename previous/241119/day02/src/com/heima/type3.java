package com.heima;

import java.util.Scanner;

public class type3 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入第一个数");
        double num1 = sc.nextDouble();
        System.out.println("请输入第二个数");
        double num2 = sc.nextDouble();
        System.out.println("请输入运算符");
        String operator = sc.next();

        double result = result(num1, num2, operator);
        System.out.println("计算的结果是："+result);
    }
    public static double result(double num1, double num2, String operator){
        double result = 0;
        switch(operator){
            case "+":
                result = num1 + num2;
                break;

            case "-":
                result = num1 - num2;
                break;

            case "*":
                result = num1 * num2;
                break;

            case "/":
                result = num1 / num2;
                break;

            default:
                System.out.println("输入的运算符有误");
                return -1;
        }
        return result;
    }
}
