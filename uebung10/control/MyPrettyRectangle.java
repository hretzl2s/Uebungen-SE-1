package org.hbrs.se1.ws22.uebung10.control;

public class MyPrettyRectangle {

    private double x1;
    private double y1;
    private double x2;
    private double y2;

    public MyPrettyRectangle(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public boolean contains(MyPrettyRectangle rectangle) {
        if (rectangle.x1 >= x1 && rectangle.x2 <= x2 && rectangle.y1 >= y1 && rectangle.y2 <= y2) {
            return true;
        }
        return false;
    }

    public MyPoint getCenter() {
        double x = (x1 + x2) / 2;
        double y = (y1 + y2) / 2;
        return new MyPoint(x,y);
    }

    public double getArea() {
        double seite1 = x2 - x1;
        double seite2 = y2 - y1;
        return seite1*seite2;
    }

    public double getPerimeter() {
        double xSeiten = (x2 - x1) * 2;
        double ySeiten = (y2 - y1) * 2;
        return xSeiten + ySeiten;
    }

    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!(object instanceof MyPrettyRectangle)) {
            return false;
        }

        MyPrettyRectangle rectangle = (MyPrettyRectangle) object;
        if (rectangle.x1 == x1 && rectangle.y1 == y1 && rectangle.x2 == x2 && rectangle.y2 == y2) {
            return true;
        }
        return false;
    }
}
