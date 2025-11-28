package zn.qkl3;

public class Restaurant6 {//解耦
    public Dish orderDish(String name) {
        return new DishFactory().produceDish(name);
    }
}
