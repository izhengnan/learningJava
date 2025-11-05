package com.heima;

public class studentoperator {
    private student s;
    public studentoperator(student s){
        this.s =s;
    }
    public void print() {
        System.out.print(s.getName()+" "+s.getAge());
    }
}
