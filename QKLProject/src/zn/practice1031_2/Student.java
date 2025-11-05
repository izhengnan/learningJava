package zn.practice1031_2;

public class Student {
    private String name;
    private String studentId;
    private MyDate birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }
    public void showInfo(){
        System.out.println("name："+name+"，studentId："+studentId+"，birthday："+birthday.getYear()+"年"+birthday.getMonth()+"月"+birthday.getDay()+"日");
    }
}
