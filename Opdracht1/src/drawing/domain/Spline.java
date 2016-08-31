/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import java.awt.Point;

/**
 *
 * @author Oscar
 */
public class Spline extends DrawingItem {
    private Point[] points;
    private int weight;
    private int degree;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public Point[] getPoints() {
        return points;
    }
}
