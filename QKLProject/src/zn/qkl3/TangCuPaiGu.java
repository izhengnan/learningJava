package zn.qkl3;

public class TangCuPaiGu extends Dish{
    public TangCuPaiGu(){
        this.name = "糖醋排骨";
    }
    @Override
    public void prepare() {
        System.out.println("正在准备糖醋排骨的材料");
    }
}
