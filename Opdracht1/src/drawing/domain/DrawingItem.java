/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import drawing.javafx.Paintable;

import java.awt.*;
import java.io.Serializable;

/**
 *
 * @author Oscar
 */
public abstract class DrawingItem implements Comparable<DrawingItem>, Serializable {
    private Point anchor;
    private Color color;
    private DrawingItem previousState;
    protected Rectangle boundingBox;

    public DrawingItem(Point anchor, Color color) {
        this.anchor = anchor;
        this.color = color;
        this.boundingBox = new Rectangle();
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
        this.boundingBox.setLocation(anchor);
    }

    public DrawingItem getPreviousState() {
        return previousState;
    }

    public void setPreviousState(DrawingItem previousState) {
        this.previousState = previousState;
    }

    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }
    
    public void paint(Paintable paintable)
    {
        paintable.setColor(Color.BLACK);
        paintable.paintRectangle(this.boundingBox);
        paintable.setColor(this.color);
    }

    public boolean overlaps(DrawingItem item) {
        return this.boundingBox.intersects(item.getBoundingBox());
    }

    public boolean insideBoundingBox(Point point) {
        return this.boundingBox.contains(point);
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
        return   "X=" + anchor.getX() + " Y=" + anchor.getY() + " color=" + color + ' ';
    }
    
    
}
