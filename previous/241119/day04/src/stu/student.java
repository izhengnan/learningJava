package stu;

public class student {
    private String stuid;
    private String name;
    private int age;

    public student(){}
    public student(String stuid,String name,int age)
    {
    	this.stuid=stuid;
    	this.name=name;
    	this.age=age;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
