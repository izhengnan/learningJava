package zn;

import java.util.Scanner;

public class Demo5 {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        double result = 0;
        boolean flag =true;
        double num1=0,num2=0;
        String op=null;
        while (flag) {
            try {
                System.out.println("请输入第一个数：");
                num1 = sc.nextDouble();
                System.out.println("请输入运算符：");
                op = sc.next();
                System.out.println("请输入第二个数：");
                num2 = sc.nextDouble();
                switch (op) {
                    case "+":
                        result = num1 + num2;
                        flag = false;
                        break;
                    case "-":
                        result = num1 - num2;
                        flag = false;
                        break;
                    case "*":
                        result = num1 * num2;
                        flag = false;
                        break;
                    case "/":
                        if (num2 == 0) {
                            System.out.println("除数不能为零，请重新输入");
                        } else {
                            result = num1 / num2;
                            flag = false;
                        }
                        break;
                    default:
                        System.out.println("输入错误，请重新输入");
                }
            } catch (Exception e) {
                System.out.println("输入格式错误，请重新输入");
                sc.nextLine();
            }
        }
        System.out.printf("%.2f %s %.2f=%.2f",num1,op,num2,result);
    }
}