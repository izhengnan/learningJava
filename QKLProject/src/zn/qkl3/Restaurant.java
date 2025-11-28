package zn.qkl3;

public class Restaurant {//高耦合

    //    public Dish orderDish(String name) {
//        if (name.equals("红烧肉")){
//            return new HongShaoRou();
//        }else if (name.equals("清炒白菜")){
//            return new QingChaoBaiCai();
//        }else if (name.equals("佛跳墙")){
//            return new FoTiaoQiang();
//        }else {
//            throw new DishException("对不起，本店没有这道菜");
//        }
//    }

    public Dish orderDish(String name) {
        return new DishFactory().produceDish(name);
    }
}
