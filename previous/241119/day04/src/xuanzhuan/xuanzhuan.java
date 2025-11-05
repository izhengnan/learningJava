package xuanzhuan;
import java.util.Scanner;
public class xuanzhuan {
    public static void main(String[] args) {
        String string=input("请输入原始字符串");
        String c=input("请输入要比较的字符串");
        Boolean b=start(string,c);
        if(b==true){
            System.out.println("是回文");
        }else{
            System.out.println("不是回文");
        }
    }
    public static Boolean start(String string,String c) {
        for (int i = 0; i < c.length(); i++) {
            c=change(c);
            if(c.equals(string)){
                return true;
            }
        }
        return false;
    }
    public static String change(String string) {
        StringBuilder sb = new StringBuilder();
        String s0=string.substring(0,1);
        String s1=string.substring(1);
        sb.append(s1);
        sb.append(s0);
        return sb.toString();
    }
    public static String input(String s) {
        Scanner sc=new Scanner(System.in);
        System.out.println(s);
        String string=sc.next();
        return string;
    }
}