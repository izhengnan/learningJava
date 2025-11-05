package com.heima;

import java.util.Random;

public class type5 {
    public static void main(String[] args) {
        String code = getcode(4);
        System.out.println(code);
    }
    public static String getcode(int number) {
        String code = "";
        Random r = new Random();
        for(int i =0;i<number;i++){
            int random = r.nextInt(2);
            switch(random) {
                case 0:
                    code+=(int)(r.nextInt(10));
                    break;
                case 1:
//                    code+=(char)(r.nextInt(52)+65);
                    code+=(char)(r.nextInt(52)+'A');
                    break;
//                case 2:
//                    code+=(char)(r.nextInt(26)+'a');
//                    break;
            }
        }
        return code;
    }
}
