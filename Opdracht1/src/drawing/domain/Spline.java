/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import drawing.javafx.Paintable;
import java.awt.Color;
import java.awt.Point;
import java.util.Arrays;

/**
 *
 * @author Oscar
 */
public class Spline extends DrawingItem {
    private final Point[] points;
    private int weight;
    private int degree;

    public Spline(Point[] points, int weight, int degree, Point anchor, Color color) {
        super(anchor, color);
        this.points = Arrays.copyOf(points, points.length);
        this.weight = weight;
        this.degree = degree;
    }

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
        return Arrays.copyOf(points, points.length);
    }

    @Override
    public void paint(Paintable paintable) {
        super.paint(paintable);
        for(int i = 0; i < points.length - 1; i++)
        {
            paintable.paintArc(points[i], points[i+1], degree);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Spline{" + "weight=" + weight + ", degree=" + degree + '}';
    }
    
    
}
