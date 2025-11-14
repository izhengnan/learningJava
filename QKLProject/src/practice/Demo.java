package practice;

import practice.impl.Circle;
import practice.impl.Rectangle;

public class Demo {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        System.out.println("圆形面积："+circle.calculateArea()+"圆形周长："+circle.calculatePerimeter());
        Rectangle rectangle = new Rectangle(4,5);
        System.out.println("矩形面积："+rectangle.calculateArea()+"矩形周长："+rectangle.calculatePerimeter());
    }

}
