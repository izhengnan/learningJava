package zn.qkl3;


public class HongShaoRou extends Dish {
    public HongShaoRou() {
        this.name="红烧肉";
    }

    @Override
    public void prepare() {
        System.out.println("正在准备红烧肉的食材");
    }
}
