package zn.practice1128;

public class HongShaoRou implements Dish{
    public String name = " 红烧肉";
    @Override
    public void prepare() {
        System.out.println("开始准备"+name);
    }

    @Override
    public void cook() {
        System.out.println("开始烹饪"+name);
    }

    @Override
    public void plate() {
        System.out.println("开始装盘"+name);
    }

    @Override
    public void serve() {
        System.out.println("开始上菜"+name);
    }
}
