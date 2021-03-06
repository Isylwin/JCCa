/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import drawing.javafx.Paintable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Oscar
 */
public class Drawing implements Comparator<DrawingItem>, Serializable {
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

    public List<DrawingItem> getItems() {
        return Collections.unmodifiableList(items);
    }
    
    public void addDrawingItem(DrawingItem item) {
        if(!items.stream().anyMatch(i -> i.overlaps(item))) {
            items.add(item);
            items.sort(this);
        }
        else {
            System.out.println("Found overlapping item: " + item.toString());
        }
    }
    
    public void changeDrawingItem(int index, DrawingItem newItem)
    {
        if(index >= 0 && index < items.size())
        {
            DrawingItem oldItem = items.get(index);
            newItem.setPreviousState(oldItem);
            items.remove(oldItem);
            this.addDrawingItem(newItem);
        }      
    }
    
    public void revertDrawingItem(int index)
    {
        if(index >= 0 && index < items.size())
        {
            DrawingItem item = items.get(index);
          
            if(item.getPreviousState() != null)
            {
                items.remove(item);
                this.addDrawingItem(item.getPreviousState());           
            }
        }
    }
    
    public void paint(Paintable paintable)
    {
        items.stream().forEach((item) -> {
            item.paint(paintable);
        });
    }

    @Override
    public int compare(DrawingItem o1, DrawingItem o2) {
        return o1.compareTo(o2);
    }
}
