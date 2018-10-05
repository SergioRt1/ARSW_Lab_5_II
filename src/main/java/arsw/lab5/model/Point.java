/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arsw.lab5.model;

/**
 *
 * @author 2135494
 */
public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Point point) {
        return this.getX() == point.getX() && this.getY() == point.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
