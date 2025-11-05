package com.heima;

public class student {
    private String name;
    private int age;
    private int Chinese_score;
    private int Math_score;

    public student(String name, int age, int Chinese_score, int Math_score){
        this.name=name;
        if(age>0&&age<200){
            this.age=age;
        }else{
            System.out.println("名字不合法");
        }
        this.Chinese_score=Chinese_score;
        this.Math_score=Math_score;
    }
    public student(){}

    public void setAge(int age) {
        if(age>0&&age<200){
            this.age=age;
        }else{
            System.out.println("名字不合法");
        }
    }
    public int getAge(){
        return age;
    }
    public void setName(String name) {
        this.name=name;
    }
    public String getName(){
        return name;
    }

    public int getChinese_score() {
        return Chinese_score;
    }

    public void setChinese_score(int chinese_score) {
        Chinese_score = chinese_score;
    }

    public int getMath_score() {
        return Math_score;
    }

    public void setMath_score(int math_score) {
        Math_score = math_score;
    }


}
