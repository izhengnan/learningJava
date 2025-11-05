package 金额转换;

import java.util.Scanner;

public class type2 {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.println("请输入你的手机号");
        String number = sc.next();
        String number_2=number.substring(0,3);
        String number_3=number.substring(7);
        System.out.println((number_2)+"****"+(number_3));
    }


}
