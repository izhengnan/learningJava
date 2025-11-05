package type06;

public class cat extends animal{
    public cat() {}
    public cat(int age,String color){
        super(age,color);
    }
    public void eat(String something){
        System.out.println(getAge()+"岁的"+getColor()+"颜色的猫眯着眼睛侧着头吃"+something);
    }
    public void catchMouse(){
        System.out.println("猫抓老鼠");
    }
}
