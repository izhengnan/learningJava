package type4;

import java.util.Scanner;

public class digui {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个整数：");
        int num = sc.nextInt();
        System.out.println("结果是："+sum(num));
    }
    public static int sum(int n){
        if(n==1){
            return 1;
        }else{
            return n+sum(n-1);
        }
    }
}
