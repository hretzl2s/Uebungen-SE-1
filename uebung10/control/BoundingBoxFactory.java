package org.hbrs.se1.ws22.uebung10.control;

public class BoundingBoxFactory {
    public static MyPrettyRectangle createBoundingBox(MyPrettyRectangle[] rectangles) {
        double kleinstesX1 = 10000000.0;
        double kleinstesY1 = 10000000.0;

        double groesstesX2 = -10000000.0;
        double groesstesY2 = -10000000.0;

        if (rectangles.length == 0) {
            return new MyPrettyRectangle(0.0, 0.0, 0.0, 0.0);
        }

        for (MyPrettyRectangle rectangle : rectangles){
            if (rectangle.getX1() < kleinstesX1) {kleinstesX1 = rectangle.getX1();}
            if (rectangle.getY1() < kleinstesY1) {kleinstesY1 = rectangle.getY1();}

            if (rectangle.getX2() > groesstesX2) {groesstesX2 = rectangle.getX2();}
            if (rectangle.getY2() > groesstesY2) {groesstesY2 = rectangle.getY2();}
        }
        return new MyPrettyRectangle(kleinstesX1, kleinstesY1, groesstesX2, groesstesY2);
    }
}
