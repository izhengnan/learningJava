package type01;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        ArrayList<Student> list=new ArrayList<>();
        Student s1=new Student("张三",18,"男");
        Student s2=new Student("李四",19,"男");
        Student s3=new Student("王五",20,"女");
        list.add(s1);
        list.add(s2);
        list.add(s3);


        int max=TestDemo.GetMaxAge(list);

        System.out.println("最大年龄为"+max);
    }



}
