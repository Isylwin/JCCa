/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import java.awt.Color;
import java.awt.Point;
import java.io.File;

/**
 *
 * @author Oscar
 */
public class Image extends DrawingItem {
    private File file;
    private double size;

    public Image(File file, double size, Point anchor, Color color) {
        super(anchor, color);
        this.file = file;
        this.size = size;
    }
    
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
