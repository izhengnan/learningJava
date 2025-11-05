package type06;

public class dog extends animal{
    public dog() {}
    public dog(int age,String color){
        super(age,color);
    }
    public void eat(String something){
        System.out.println(getAge()+"岁的"+getColor()+"颜色的狗两只前腿死死的抱住"+something+"猛吃");
    }
    public void lookHome(){
        System.out.println("汪汪汪");
    }
}
