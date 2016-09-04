/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

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
    public String toString() {
        return super.toString() + "Polygon{" + "weight=" + weight + '}';
    }
    
    
}
