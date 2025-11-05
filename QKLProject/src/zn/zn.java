package zn;

import java.util.Scanner;

public class zn {
    public static void main(String[] args) {
        String name;
        int age;
        Scanner sc =new Scanner(System.in);
        System.out.println("请输入年龄：");
        age=sc.nextInt();
        System.out.println("请输入姓名：");
        name = sc.next();
        System.out.println("我是"+name+"，我今年"+age+"岁");
    }
}
