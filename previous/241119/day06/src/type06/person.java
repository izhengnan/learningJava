package type06;

public class person {
    private String name;
    private int age;
    public person(){}
    public person(String name,int age){
        this.name = name;
        this.age = age;
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
    public void keepPet(animal a,String something){
        String pet=a.getClass().getName()=="type06.dog"? "狗" : "猫";
        System.out.println("年龄为"+age+"的"+name+"养了一只"+a.getColor()+"颜色的"+a.getAge()+"岁的"+pet);
        a.eat(something);
        if(a instanceof dog d){
            d.lookHome();
        }else if(a instanceof cat c){
            c.catchMouse();
        }
    }
}
