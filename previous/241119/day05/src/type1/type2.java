package type1;

import java.util.ArrayList;
import java.util.Scanner;

public class type2 {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        ArrayList<student> list =new ArrayList<>();
//        student s1=new student("张三",18);
//        student s2=new student("李四",19);
//        student s3=new student("王五",20);
//        list.add(s1);
//        list.add(s2);
//        list.add(s3);
        for (int i=0;i<3;i++){
            student s =new student();
            System.out.println("请输入学生的姓名");
            String name=sc.next();
            System.out.println("请输入学生的年龄");
            int age=sc.nextInt();
            s.setName(name);
            s.setAge(age);
            list.add(s);
        }
        System.out.println(list.size());
    }

}
