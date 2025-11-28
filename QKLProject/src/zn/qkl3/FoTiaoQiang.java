package zn.qkl3;

public class FoTiaoQiang extends Dish{
    public FoTiaoQiang() {
        this.name=" 佛跳墙 ";
    }

    @Override
    public void prepare() {
        System.out.println("正在准备佛跳墙的材料");
    }
}
