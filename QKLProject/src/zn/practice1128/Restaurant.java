package zn.practice1128;

public class Restaurant {
    public Dish orderDish(String name){
        return new DishFactory().factoryDish(name);
    }
}
