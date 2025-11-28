package zn.practice1128;

public class DishFactory {
    public Dish factoryDish(String name){
        switch (name){
            case "红烧肉":
                return new HongShaoRou();
            case "清炒白菜":
                return new QingChaoBaiCai();
            default:
                throw new DishException("没有此菜");
        }
    }
}
