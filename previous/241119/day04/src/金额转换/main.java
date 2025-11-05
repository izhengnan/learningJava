package 金额转换;
import java.util.Scanner;
public class main {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        String S[]= {"元","拾","佰","仟","万","拾","佰"};
        String C[]={"壹","贰","叁","肆","伍","陆","柒","捌","玖"};
        System.out.println("请输入你想转换的金额");
        int num = sc.nextInt();
        String s="";
        String arr[]=new String[7];
        for(int i=0;i<7;i++){
            if(num!=0) {
                arr[i] = C[num % 10 - 1];
                num /= 10;
            }else{
                arr[i] = C[0];
            }
        }
        for(int i=6;i>=0;i--){
            s+=(arr[i]+S[i]);
        }
        System.out.println(s);
    }
}
