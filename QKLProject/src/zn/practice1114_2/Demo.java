package zn.practice1114_2;

import zn.practice1114_2.impl.Circle;
import zn.practice1114_2.impl.Rectangle;

public class Demo {
    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.setRadius(5);
        Rectangle rectangle = new Rectangle();
        rectangle.setLength(4);
        rectangle.setWidth(5);
        System.out.println("圆形面积："+circle.calculateArea()+
                "圆形周长："+circle.calculatePerimeter()+
                "矩形面积："+rectangle.calculateArea()+
                "矩形周长："+rectangle.calculatePerimeter());
    }

}
