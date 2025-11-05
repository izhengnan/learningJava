
import java.lang.*;
import java.util.*;

public class test {
        public static void main(String [] args){
            System.out.print("������n: ");
            //**********Found**********
            Scanner scan = new Scanner (System.in);
            String nstr = scan.next();
            //**********Found**********
            int n = Integer.parseInt(nstr);
            int b = 6;
            long sum=0,item=b;
            for(int i=1;i<=n;i++){
                sum += item;
                //**********Found**********
                item = item * 10 + b;
            }
            System.out.print(n + "��ĺ�Ϊ " + sum);
        }
    }