package denlu;

import java.util.Scanner;

public class denlu {
    public static void main(String[] args) {
        account Account[] =  new account[999];
        Account[0]=new account("ABC123","123456");
        int count =1;
//        denglu(Account,count);


        System.out.println("请输入一个字符串");
        Scanner sc =new Scanner(System.in);
        String string=sc.next();
        String str=getString(string);
        System.out.println(str);
    }
    public static void denglu(account Account[],int count){
        int n=0;
        Scanner sc =new Scanner(System.in);
        while(true){
            if(n==3){
                System.out.println("失败次数过多，请稍后再试");
                return;
            }
            System.out.println("请输入用户名");
            String username = sc.next();
            System.out.println("请输入密码");
            String password = sc.next();
            for(int i=0;i<count;i++){
                if(username.equals(Account[i].getAccount())&&password.equals(Account[i].getPassword())){
                    System.out.println("登录成功");
                    return;
                }
            }
            n+=1;
            System.out.println("用户名或密码错误，您还有"+(3-n)+"次机会");
        }
    }

    public static String getString(String string) {
//        char arr[]=new char[string.length()];
//        for(int i=0;i<string.length();i++){
//            arr[i]=string.charAt(i);
//        }
//        String str ="";
//        for(int i=string.length()-1;i>=0;i--){
//            str+=arr[i];
//        }
//        return str;
//    }
        String str = "";
        for (int i = string.length() - 1; i >= 0; i--) {
            str += string.charAt(i);
        }
        return str;
    }
}
