package zn.qkl3;

import java.util.Scanner;

public class Demo01 {
    //设计模式
    //我开了家餐馆
    //红烧肉、清炒白菜
    //准备材料
    //烹调
    //装盘
    //上菜
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        Dish dish = restaurant.orderDish(s);
        dish.prepare();
        dish.cook();
        dish.plate();
        dish.serve();

    }
}
