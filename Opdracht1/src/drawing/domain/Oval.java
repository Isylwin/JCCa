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
public class Oval extends DrawingItem {

    private double width;
    private double height;

    public Oval(double width, double height, Point anchor, Color color) {
        super(anchor, color);
        this.width = width;
        this.height = height;
    }
    
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return super.toString() + "Oval{" + "width=" + width + ", height=" + height + '}';
    }
    
    
}
