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
public class Polygon extends DrawingItem {
    private int weight;
    private Point[] vertices;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Point[] getVertices() {
        return vertices;
    }
}
