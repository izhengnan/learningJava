package com.heima;

import java.util.Random;

public class type7 {
    public static void main(String[] args) {
        Random r = new Random();
        int arr[]=new int[12];
        int max = arr[0];
        for(int i=0;i<12;i++){
            arr[i]=r.nextInt(50);
            System.out.println(arr[i]);
            if (arr[i]>max){
                max=arr[i];
            }
        }
        System.out.println("最大值是："+max);
    }
}
