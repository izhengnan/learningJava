package roma;

import java.util.Scanner;

public class romaMain {
    public static void main(String[] args) {
        String string=get();
        String result=change(string);
        System.out.println(result);
    }

    public static String change(String string){
        String[] x={" ","I","II","III","IV","V","VI","VII","VIII","IX"};
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<string.length();i++){
            int j=string.charAt(i)-'0';
            sb.append(x[j]);
            sb.append(" ");
        }
        return sb.toString();
    }
    public static String get(){
        String string="";
        System.out.println("请输入一个数字字符串(长度小于9)");
        while (true) {
            Scanner sc = new Scanner(System.in);
            string = sc.next();
            int length=string.length();
            if(length<=9){
                if(isnum(string)){
                    break;
                }
            }
            System.out.println("过长或包含其他字符，请重新输入");
        }
        return string;
    }

    public static Boolean isnum(String s){
        for(int i=0;i<s.length();i++){
            s.charAt(i);
            if(s.charAt(i)<'0'||s.charAt(i)>'9'){
                return false;
            }
        }
        return true;

    }
}
