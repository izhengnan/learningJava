package zn.practice1114_2.impl;

import zn.practice1114_2.Shape;

public class Circle implements Shape {
    private double radius;


    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if(radius>=0) {
            this.radius = radius;
        }
    }

    @Override
    public double calculateArea() {
        return radius*radius*3.14;
    }

    @Override
    public double calculatePerimeter() {
        return 2*radius*3.14;
    }
}
