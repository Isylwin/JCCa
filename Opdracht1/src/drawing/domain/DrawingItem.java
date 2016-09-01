/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author Oscar
 */
public abstract class DrawingItem implements Comparable<DrawingItem> {
    private Point anchor;
    private Color color;
    private DrawingItem previousState;

    public DrawingItem(Point anchor, Color color) {
        this.anchor = anchor;
        this.color = color;
    }  

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point getAnchor() {
        return anchor;
    }

    public void setAnchor(Point anchor) {
        this.anchor = anchor;
    }

    public DrawingItem getPreviousState() {
        return previousState;
    }

    public void setPreviousState(DrawingItem previousState) {
        this.previousState = previousState;
    }   

    @Override
    public int compareTo(DrawingItem item) {
        Point origin = new Point(0,0);
        double dist1 = this.anchor.distance(origin);
        double dist2 = item.getAnchor().distance(origin);
        
        return Double.compare(dist1,dist2);       
    }

    @Override
    public String toString() {
        return "DrawingItem{" + "anchor=" + anchor + ", color=" + color + '}';
    }
    
    
}
