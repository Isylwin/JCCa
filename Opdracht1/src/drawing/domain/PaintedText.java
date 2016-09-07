/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import drawing.javafx.Paintable;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

/**
 *
 * @author Oscar
 */
public class PaintedText extends DrawingItem {
    private String content;
    private Font font;

    public PaintedText(String content, Font font, Point anchor, Color color) {
        super(anchor, color);
        this.content = content;
        this.font = font;
    }
   
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    @Override
    public void paint(Paintable paintable) {
        super.paint(paintable);
        paintable.paintText(this);
    }

    @Override
    public String toString() {
        return super.toString() + "PaintedText{" + "content='" + content + "', font=" + font + '}';
    }
    
    
}
