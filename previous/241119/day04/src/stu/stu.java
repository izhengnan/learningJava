package stu;

import java.util.Scanner;

public class stu {
    public static void main(String[] args) {
        student s[]=new student[999];
        s[0]=new student("001","张三",18);
        int num=1;
        start(s,num);
    }

    public static int start(student stu[],int num){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1.添加学生");
            System.out.println("2.删除学生");
            System.out.println("3.查询列表");
            System.out.println("0.退出");
            int input=sc.nextInt();
            switch (input){
                case 1:
                    stu[num]=new student();
                    System.out.println("请输入学号：");
                    out:
                    while(true){
                        String id =sc.next();
                        for(int i=0;i<num;i++){
                            if(id.equals(stu[i].getStuid())){
                                System.out.println("学号重复，请重新输入：");
                            }
                            else{
                                stu[num].setStuid(id);
                                break out;
                            }
                        }
                    }
                    System.out.println("请输入姓名：");
                    stu[num].setName(sc.next());
                    System.out.println("请输入年龄：");
                    stu[num].setAge(sc.nextInt());
                    System.out.println("添加成功，学号是"+stu[num].getStuid()+"，姓名是"+stu[num].getName()+"，年龄是"+stu[num].getAge());
                    num++;
                    break;
                case 2:
                    System.out.println("请输入要删除的学生的学号：");
                    String DeleteID=sc.next();
                    for(int i=0;i<num;i++){
                        if(DeleteID.equals(stu[i].getStuid())){
                            System.out.println("删除成功.");
                            num--;
                        }
                    }
                    break;
                case 3:
                    if(num==0){
                        System.out.println("没有学生信息");
                    }else{
                        for(int i=0;i<num;i++){
                            System.out.println("学号:"+stu[i].getStuid()+" 姓名:"+stu[i].getName()+" 年龄:"+stu[i].getAge());
                        }
                    }
                    break;
                case 0:
                    return 0;
            }
        }
    }
}
