package zn;

import java.util.Scanner;

public class Demo4 {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        Boolean flag = true;
        while(flag){
            System.out.println("##################温度转换系统####################");
            System.out.println("1.华氏度转换摄氏度 2.摄氏度转换华氏度 3.退出");
            System.out.println("请选择：");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.println("请输入华氏度：");
                    double fahrenheit = sc.nextDouble();
                    double celsius = (fahrenheit-32)*5/9;
                    System.out.println("转换后的摄氏度是："+celsius);
                    break;
                case 2:
                    System.out.println("请输入摄氏度：");
                    double celsius1 = sc.nextDouble();
                    double fahrenheit1 = (celsius1*9/5)+32;
                    System.out.println("转换后的华氏度是："+fahrenheit1);
                    break;
                case 3:
                    flag = false;
                    System.out.println("退出");
                    break;
                default:
                    System.out.println("输入错误，请重新输入");
            }
        }
    }
}
