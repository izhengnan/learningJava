package type07;

public class rabbit extends animal{
    public rabbit() {
    }

    public rabbit(int age, String name) {
        super(age, name);
    }
    public void eat() {
        System.out.println("兔子吃胡萝卜");
    }
}
