package com.heima;

import java.util.Random;

public class type9 {
    public static void main(String[] args) {
        String card1[] =new String[54];
        card(card1);
        Random random = new Random();
        for(int i =0;i<card1.length;i++){
            int index= random.nextInt(54);//运行结果是0-53之间的随机数
            exchange(card1,i,index);
        }
        for(int i=0;i<card1.length;i++){
            System.out.print(card1[i]+"\t");
        }
    }
    public static void card(String card1[]){
        int count = 0;
        String color[]={"♠","♣","♥","♦"};
        for(int i=0;i<13;i++){
            String k;
            if(i==0){k="A";}
            else if(i==10){k="J";}
            else if(i==11){k="Q";}
            else{k="K";}
            for(int j=0;j<4;j++){
                if(i==0||i==11||i==12||i==10){
                    card1[count++]=color[j]+k;
                }else{
                    card1[count++]=color[j]+i;
                }
            }
        }
        card1[count++]="小王";
        card1[count]="🃏";
    }
    public static void exchange(String arr[],int y,int x){
        String temp = arr[x];
        arr[x]=arr[y];
        arr[y] = temp;
    }
}
