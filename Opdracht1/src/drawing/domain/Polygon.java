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
public class Polygon extends DrawingItem {
    private int weight;
    private final Point[] vertices;

    public Polygon(int weight, Point[] vertices, Point anchor, Color color) {
        super(anchor, color);
        this.weight = weight;
        this.vertices = Arrays.copyOf(vertices, vertices.length);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Point[] getVertices() {
        return Arrays.copyOf(vertices, vertices.length);
    }

    @Override
    public void paint(Paintable paintable) {
        super.paint(paintable);
        for(int i = 0; i < vertices.length; i++)
        {
            Point current = vertices[i];
            Point next = i+1 == vertices.length ? vertices[0] : vertices[i+1];
            paintable.paintLine(current, next, weight);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Polygon{" + "weight=" + weight + '}';
    }
    
    
}
