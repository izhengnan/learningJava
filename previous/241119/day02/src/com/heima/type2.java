package com.heima;

public class type2 {
    public static void main(String[] args) {
        int zmlm = 8848860;
        double paper = 0.1;
        int count=0;
        while(paper<zmlm){
            paper*=2;
            count++;
        }
        System.out.println("折叠次数为" +count);
    }
}
