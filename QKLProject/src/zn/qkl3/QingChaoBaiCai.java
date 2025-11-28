package zn.qkl3;

public class QingChaoBaiCai extends Dish{
    public QingChaoBaiCai() {
        this.name="清炒白菜";
    }

    @Override
    public void prepare() {
        System.out.println("正在准备清炒白菜的材料");
    }
}
