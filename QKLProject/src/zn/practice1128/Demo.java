package zn.practice1128;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        System.out.println("请输入你想点的菜：");
        Scanner sc =new Scanner(System.in);
        String name = sc.next();
        Dish dish = restaurant.orderDish(name);
        dish.prepare();
        dish.cook();
        dish.plate();
        dish.serve();

    }

}
