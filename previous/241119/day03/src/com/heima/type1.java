package com.heima;

public class type1 {
    public static void main(String[] args) {
//        student s1= new student();
//        s1.name="张三";
//        s1.age=18;
//        s1.Chinese_score=90;
//        s1.Math_score=80;
        student s1 =new student("张三",18,90,80);
        studentoperator op = new studentoperator(s1);
        op.print();
    }
}
