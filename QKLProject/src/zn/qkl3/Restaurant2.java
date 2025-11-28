package zn.qkl3;

public class Restaurant2 {//解耦
    public Dish orderDish(String name) {
        return new DishFactory().produceDish(name);
    }
}
