/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Oscar
 */
public class Drawing {
    private String name;
    private int width;
    private int height;
    private final ArrayList<DrawingItem> items;

    public Drawing(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.items = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ArrayList<DrawingItem> getItems() {
        return (ArrayList<DrawingItem>) Collections.unmodifiableList(items);
    }
}
