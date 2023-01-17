package org.hbrs.se1.ws22.uebung10.Aufgabe1.control;

public class MyPoint {
    private double x;
    private double y;
    public MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!(object instanceof MyPoint)) {
            return false;
        }

        MyPoint punkt = (MyPoint) object;
        if (punkt.x == x && punkt.y == y) {
            return true;
        }
        return false;
    }
}
