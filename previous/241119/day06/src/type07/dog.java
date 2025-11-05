package type07;

public class dog extends animal implements swim{
    public dog() {
    }

    public dog(int age, String name) {
        super(age, name);
    }
    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }
    public void swim(){
        System.out.println("狗会游泳");
    }
}
