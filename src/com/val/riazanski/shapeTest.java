package com.val.riazanski;

import java.util.ArrayList;

public class shapeTest {
    public static void main(String[] args) {
        FieldShape randField = new FieldShape();
        int sizeField = 20;
        for (int i = 0; i < sizeField; i++) {
            double base = 3 * Math.random() + 1;
            double height = 2 * Math.random() + 2;
            double typeShape = Math.random() * 8 + 1;
            if (typeShape < 4) {
                randField.adShape(new Prism(base, height));
            } else if ((4 < typeShape) && (typeShape < 7)) {
                randField.adShape(new Pyramid(base, height));
            }
            randField.adShape(new Sphere(height));
        }
        ArrayList<Shape> randFieldShapeList = randField.getShapeList();
        Shape anotherShape = randFieldShapeList.get(1);
        for (Shape shape : randFieldShapeList) {
            System.out.print(shape.getName() + "'s " + "volume=" + Math.round(shape.getVolume()) + " "
                    + anotherShape.getName() + "'s " + "volume=" + Math.round(anotherShape.getVolume()) + " ");
            if (shape.comparableTo(anotherShape) < 0) {
                System.out.print(shape.getName() + " volume > " + anotherShape.getName() + " volume" + "\n");
            } else if (shape.comparableTo(anotherShape) > 0 ) {
                System.out.print(shape.getName() + " volume < " + anotherShape.getName() + " volume" + "\n" );
            }
            else {
                System.out.print(shape.getName() + " volume = " + anotherShape.getName() + " volume" + "\n" );
            }
            anotherShape = shape;
        }

    }
}

abstract class Shape implements Comparable, Volume{
    //fields
    static final double EPSILON = 0.1E-04;
    //constructor
    public Shape() {
    }
    //methods
    public int comparableTo (Shape shape) {
        if ( shape.getVolume() < (this.getVolume() - EPSILON) ) {
            return -1;
        }
        else if (Math.abs(shape.getVolume() - this.getVolume()) < EPSILON) {
            return 0;
        }
        return 1;
    }
    public abstract String getName();

}
class FieldShape {
    //fields
    private final ArrayList<Shape> shapeList;
    //constructors
    public FieldShape() {
        this.shapeList = new ArrayList<Shape>();
    }
    //methods
    public void adShape(Shape shape) {
        this.shapeList.add(shape);
    }
    public ArrayList<Shape> getShapeList() {
        return this.shapeList;
    }
}
interface Volume {
    double getVolume();
}
interface Comparable {
      int comparableTo(Shape shape);
}

class Prism extends Shape implements Volume {
    //fields
    private final static String prism = "Prism";
    private final double base;
    private final double height;
    //constructors
    public Prism(double base, double height) {
        this.base = base;
        this.height = height;
    }
    //implements
    public double getVolume() {
        return this.base * this.height;
    }
    //methods
    @Override
    public String getName() {
        return prism;
    }
}
class Pyramid extends Shape implements Volume {
    //fields
    private final static String pyramid = "Pyramid";
    private final double base;
    private final double height;
    //constructors
    public Pyramid(double base, double height) {
        this.base = base;
        this.height = height;
    }
    //implements
    public double getVolume() {
        return this.base * this.height / 3.0;
    }
    //methods
    @Override
    public String getName() {

        return pyramid;
    }
}
class Sphere extends Shape implements Volume {
    //fields
    private final static String sphere = "Sphere";
    private final double radius;
    //constructors
    public Sphere(double radius) {
        this.radius = radius;
    }
    //implements
    public double getVolume() {
        return 4.0 * Math.PI * Math.pow(this.radius, 3) / 3.0;
    }
    //methods
    @Override
    public String getName() {
        return sphere;
    }
}