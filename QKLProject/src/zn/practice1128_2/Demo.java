package zn.practice1128_2;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.println("请输入第一个数字：");
        int a = sc.nextInt();
        System.out.println("请输入第二个数字：");
        int b = sc.nextInt();
        System.out.println("请输入运算符：");
        String operate = sc.next();
        OperationImpl operationImpl = new OperationImpl();
        int operation = operationImpl.calculate(operate,a,b);
        System.out.println("结果是："+operation);
    }
}
