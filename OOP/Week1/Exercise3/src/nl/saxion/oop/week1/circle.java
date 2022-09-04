package nl.saxion.oop.week1;

import nl.saxion.app.SaxionApp;

import static java.lang.Math.PI;
import static nl.saxion.app.SaxionApp.drawCircle;

public class circle {
    private int x = 250;
    private int y = 250;
    private int radius = 100;

    public int getxCoordinate() {
        return x;
    }

    public void setX(int x) {
        if (x > 0) {
            this.x = x;
        } else {
            SaxionApp.printLine("X must be positive");
        }
    }

    public int getyCoordinate() {
        return y;
    }

    public void setY(int y) {
        if (y > 0) {
            this.y = y;
        } else {
            SaxionApp.printLine("Y must be positive");
        }
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public float area(){
        return (float) (getRadius()*getRadius()*PI);
    }

    public float perimeter(){
        return (float) (2*getRadius()*PI);
    }

    public void draw(){
        drawCircle(getxCoordinate(), getyCoordinate(), getRadius());
    }
}
