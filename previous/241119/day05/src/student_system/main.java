package student_system;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Login();
    }
    public static void ForgetPassword(ArrayList<User> list){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入用户名");
        String userName = sc.next();
        int sub=UserintId(list,userName);
        if (sub==-1){
            System.out.println("用户名不存在，请注册");
        }else{
            System.out.println("请输入你的电话号码");
            String Number = sc.next();
            System.out.println("请输入你的身份证号码");
            String sfzNumber = sc.next();
            String NumberTrue=list.get(sub).getNumber();
            String sfzNumberTrue=list.get(sub).getSfzNumber();
            if(Number.equals(NumberTrue)&&sfzNumber.equals(sfzNumberTrue)){
                while (true){
                    System.out.println("请输入新密码:");
                    String password =sc.next();
                    System.out.println("请再次输入新密码:");
                    String password2 =sc.next();
                    if(password.equals(password2)){
                        User s =list.get(sub);
                        s.setPassword(password);
                        System.out.println("密码重置成功");
                        break;
                    }else{
                        System.out.println("两次密码不一致，请重新输入");
                    }
                }
            }else{
                System.out.println("身份证号码或电话号码不正确，密码重置失败");
            }
        }
    }
    public static void UserLogin(ArrayList<User> list){
        Scanner sc=new Scanner(System.in);
        int i=3;
        while(i>0) {
            System.out.println("请输入用户名");
            String userName = sc.next();
            System.out.println("请输入密码");
            String password = sc.next();
            String string = getCode(5);
            System.out.println("请输入验证码:"+string);
            String code =sc.next();
            if(code.equals(string)) {
                System.out.println("验证码正确");
                i--;
                int sub = UserintId(list, userName);
                if (sub == -1) {
                    System.out.println("用户名不存在，请注册");
                    break;
                } else {
                    String passwordTrue = list.get(sub).getPassword();
                    if (password.equals(passwordTrue)) {
                        System.out.println("登录成功");
                        choice();
                        break;
                    } else {
                        System.out.println("密码错误，请重新输入，您还有" + i + "次机会");
                    }
                }
            }else{
                System.out.println("验证码错误，请重新输入");
            }
        }
    }
    public static void Register(ArrayList<User> list){
        Scanner sc =new Scanner(System.in);
        User u =new User();
        while (true){
            System.out.println("请输入用户名:");
            String userName=sc.next();
            if(Userid(list,userName)){
                if(userName.length()>=3&&userName.length()<=15){
                    if(CheckUserName(userName)){
                        u.setUserName(userName);
                        break;
                    }else{
                        System.out.println("用户名必须是数字和英文构成，请重新输入");
                    }
                }else{
                    System.out.println("用户名长度不符合要求，请重新输入");
                }
            }else{
                System.out.println("用户名已存在，请重新输入");
            }
        }
        while (true){
            System.out.println("请输入密码:");
            String password =sc.next();
            System.out.println("请再次输入密码:");
            String password2 =sc.next();
            if(password.equals(password2)){
                u.setPassword(password);
                break;
            }else{
                System.out.println("两次密码不一致，请重新输入");
            }
        }
        while (true){
            System.out.println("请输入身份证号码：");
            String sfzNumber = sc.next();
            if (CheckFirstZero(sfzNumber)) {
                if(sfzNumber.length()==18){
                    if(CheckNumber(sfzNumber.substring(0,16))){
                        if(CheckFinalNumber(sfzNumber)){
                            u.setSfzNumber(sfzNumber);
                            break;
                        }else{
                            System.out.println("身份证号码最后一位必须是x或X或0-9的数字，请重新输入");
                        }
                    }else{
                        System.out.println("身份证号码前17位必须为数字，请重新输入");
                    }
                }else{
                    System.out.println("身份证号码长度不符合要求，请重新输入");
                }
            } else {
                System.out.println("身份证号码首位不能为零，请重新输入");
            }
        }
        while (true){
            System.out.println("请输入手机号码：");
            String Number = sc.next();
            if(Number.length()==11){
                if(CheckNumber(Number)){
                    if(CheckFirstZero(Number)){
                        u.setNumber(Number);
                        break;
                    }else{
                        System.out.println("手机号码首位不能为零，请重新输入");
                    }
                }else{
                    System.out.println("手机号码必须为数字，请重新输入");
                }
            }else{
                System.out.println("手机号码长度不符合要求，请重新输入");
            }
        }
        System.out.println("注册成功");
        list.add(u);
    }

    public static void Login(){
        ArrayList<User> list=new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        loop:while(true){
            LoginMenu();
            int choice=sc.nextInt();
            switch(choice){
                case 1:
                    System.out.println("登录");
                    UserLogin(list);
                    break;
                case 2:
                    System.out.println("注册");
                    Register(list);
                    break;
                case 3:
                    System.out.println("忘记密码");
                    ForgetPassword(list);
                    break;
                case 4:
                    System.out.println("退出");
                    break loop;
                default:
                    System.out.println("输入有误，请重新输入");
            }
        }
    }
    public static void choice(){
        ArrayList<Student> list =new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        loop:while(true){
            menu();
            int choice=sc.nextInt();
            switch(choice){
                case 1:
                    System.out.println("添加学生");
                    add(list);
                    System.out.println("添加成功");
                    break;
                case 2:
                    System.out.println("删除学生");
                    del(list);
                    break;
                case 3:
                    System.out.println("修改学生");
                    update(list);
                    break;
                case 4:
                    find(list);
                    break;
                case 5:
                    System.out.println("退出");
                    break loop;
                default:
                    System.out.println("输入有误，请重新输入");
            }
        }
    }
    public static void find(ArrayList<Student> list){
        if(list.size()==0){
            System.out.println("学生列表为空");
            return;
        }
        for(int i=0;i<list.size();i++){
            System.out.println((i+1)+":学生学号："+list.get(i).getId()+" 学生姓名："+list.get(i).getName()+" 学生年龄："+list.get(i).getAge()+" 学生住址："+list.get(i).getAddress());
        }
    }
    public static void update(ArrayList<Student> list){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入你想修改的学生的学号:");
        String id = sc.next();
        int count=intId(list, id);
        if (count == -1) {
            System.out.println("学号不存在，将返回主菜单");
        }else{
            list.remove(count);
            add(list);
            System.out.println("修改成功");
        }
    }
    public static void del(ArrayList<Student> list){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入你想删除的学生的学号:");
        String id = sc.next();
        int count=intId(list, id);
        if (count == -1) {
            System.out.println("学号不存在，将返回主菜单");
        }else{
            list.remove(count);
            System.out.println("删除成功");
        }
    }
    public static void add(ArrayList<Student> list){
        Scanner sc=new Scanner(System.in);
        Student s =new Student();
        System.out.println("请输入学生学号:");
        String id = sc.next();
        while (true) {
            if (id(list, id) == false) {
                System.out.println("学号重复，请重新输入:");
                id = sc.next();
            }else{
                break;
            }
        }
        System.out.println("请输入学生姓名:");
        String name=sc.next();
        System.out.println("请输入学生年龄:");
        int age=sc.nextInt();
        System.out.println("请输入学生住址:");
        String address =sc.next();
        s.setName(name);
        s.setAge(age);
        s.setId(id);
        s.setAddress(address);
        list.add(s);
    }
    public static Boolean id(ArrayList<Student> list,String id){
        if(intId(list,id)==-1){
            return true;
        }else{
            return false;
        }
    }
    public static int intId(ArrayList<Student> list,String id){
        if (list.size()==0){
            return -1;
        }
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId().equals(id)){
                return i;
            }
        }
        return -1;
    }
    public static Boolean Userid(ArrayList<User> list,String id){
        if(UserintId(list,id)==-1){
            return true;
        }else{
            return false;
        }
    }
    public static int UserintId(ArrayList<User> list,String id){
        if (list.size()==0){
            return -1;
        }
        for(int i=0;i<list.size();i++){
            if(list.get(i).getUserName().equals(id)){
                return i;
            }
        }
        return -1;
    }
    public static boolean CheckUserName(String name) {
        int NumberCount=0;
        int LetterCount=0;
        int OtherCount=0;
        for(int i=0;i<name.length();i++){
            if(name.charAt(i)>='0'&&name.charAt(i)<='9'){
                NumberCount++;
            } else if (name.charAt(i)>='a'&&name.charAt(i)<='z'||name.charAt(i)>='A'&&name.charAt(i)<='Z') {
                LetterCount++;
            }else{
                OtherCount++;
            }
        }
        if(NumberCount>0&&LetterCount>0&&OtherCount==0){
            return true;
        }
        return false;
    }
    public static Boolean CheckFirstZero(String number){
        if(number.charAt(0)=='0'){
            return false;
        }
        return true;
    }
    public static Boolean CheckFinalNumber(String number){
        if(number.charAt(17)=='X'||number.charAt(17)=='x'||number.charAt(17)>='0'&&number.charAt(17)<='9'){
            return true;
        }
        return false;
    }
    public static Boolean CheckNumber(String number){
        for(int i=0;i<number.length();i++){
            if(number.charAt(i)<'0'||number.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }
    public static void menu(){
        System.out.println("-------------欢迎来到学生管理系统----------------");
        System.out.println("1：添加学生");
        System.out.println("2：删除学生");
        System.out.println("3：修改学生");
        System.out.println("4：查询学生");
        System.out.println("5：退出");
        System.out.println("请输入您的选择:");
    }
    public static void LoginMenu(){
        System.out.println("欢迎来到学生管理系统");
        System.out.println("请选择操作:1.登录 2.注册 3.忘记密码 4.退出系统");
    }
    public static String getCode(int num){
        String[] Number={"0","1","2","3","4","5","6","7","8","9"};
        Random r =new Random();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<num-1;i++){
            int choose = r.nextInt(2);
            if(choose==0){
                int result=r.nextInt(26)+97;
                char s=(char)result;
                sb.append(s);
            }else{
                int result=r.nextInt(26)+65;
                char s=(char)result;
                sb.append(s);
            }
        }
        sb.insert(r.nextInt(5),Number[r.nextInt(10)]);
        return sb.toString();
    }
}