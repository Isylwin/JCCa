/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import drawing.javafx.Paintable;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


/**
 *
 * @author Oscar
 */
public class Polygon extends DrawingItem {
    private int weight;
    private final Point[] vertices;

    public Polygon(int weight, Point[] vertices, Color color) {
        super(new Point(0,0), color);
        this.weight = weight;
        this.vertices = Arrays.copyOf(vertices, vertices.length);

        Collection<Integer> xCoordinates = new ArrayList<>();
        Collection<Integer> yCoordinates = new ArrayList<>();
        Arrays.stream(vertices).forEach(x -> xCoordinates.add((int)x.getX()));
        Arrays.stream(vertices).forEach(x -> yCoordinates.add((int)x.getY()));

        int width = Collections.max(xCoordinates) - Collections.min(xCoordinates);
        int height = Collections.max(yCoordinates) - Collections.min(yCoordinates);

        Point anchor = new Point(Collections.min(xCoordinates), Collections.min(yCoordinates));
        this.setAnchor(anchor);

        this.boundingBox = new Rectangle(anchor, new Dimension(width, height));
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
