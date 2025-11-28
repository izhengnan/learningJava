package zn.qkl3;

public class DishFactory {
    public Dish produceDish(String name){
        if (name.equals("红烧肉")){
            return new HongShaoRou();
        }else if (name.equals("清炒白菜")){
            return new QingChaoBaiCai();
        }else if (name.equals("佛跳墙")){
            return new FoTiaoQiang();
        }else {
            throw new DishException("对不起，工厂没有这道菜");
        }
    }
}
