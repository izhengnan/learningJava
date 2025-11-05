package type07;

public class qingwa extends animal implements swim{
    public qingwa() {}

    public qingwa(int age, String name) {
        super(age, name);
    }
    @Override
    public void eat(){
        System.out.println("青蛙吃虫子");
    }
    public void swim(){
        System.out.println("青蛙游泳");
    }
}
