package com.heima;

public class type8
{
    public static void main(String[] args) {
        create();
    }
    public static void create(){
        String arr[]=new String[54];
        int i=0;
            for(int j=2;j<=10;j++){
                for(int n =0;n<4;n++){
                    switch(n) {
                        case 0:
                            arr[i]="黑桃"+j;
                            i++;
                            break;
                        case 1:
                            arr[i]="红桃"+j;
                            i++;
                            break;
                        case 2:
                            arr[i]="梅花"+j;
                            i++;
                            break;
                        case 3:
                            arr[i]="方块"+j;
                            i++;
                            break;
                        default:
                            break;
                    }
                }
            }
            for(int k = 0;k<4;k++){
                String s;
                switch(k){
                    case 0:
                        s="J";
                        break;
                    case 1:
                        s="Q";
                        break;
                    case 2:
                        s="K";
                        break;
                    case 3:
                        s="A";
                        break;
                    default:
                        s = "";
                        break;
                }
                for (int j = 0; j < 4; j++){
                    switch(j) {
                        case 0:
                            arr[i]="黑桃"+s;
                            i++;
                            break;
                        case 1:
                            arr[i]="红桃"+s;
                            i++;
                            break;
                        case 2:
                            arr[i]="梅花"+s;
                            i++;
                            break;
                        case 3:
                            arr[i]="方块"+s;
                            i++;
                            break;
                        default:
                            break;
                    }
                }
            }
            arr[i]="小王";
            i++;
            arr[i]="大王";
            for (int o = 0; o < arr.length; o++){
                System.out.println(arr[o]);
            }
}
}
