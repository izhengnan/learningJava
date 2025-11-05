package zn;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        String name;
        int age;
        Scanner sc =new Scanner(System.in);
        System.out.println("请输入姓名：");
        name = sc.next();
        System.out.println("请输入年龄：");
        age = sc.nextInt();
        System.out.println("姓名："+name+" 年龄："+age);
    }
}