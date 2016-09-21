/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import drawing.javafx.Paintable;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

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

        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        int textwidth = (int)(font.getStringBounds(content, frc).getWidth());
        int textheight = (int)(font.getStringBounds(content, frc).getHeight()) - 7;

        Point anchor2 = new Point((int)anchor.getX(), (int)anchor.getY() - textheight);

        this.boundingBox = new Rectangle(anchor2, new Dimension(textwidth, textheight));
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

        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        int textwidth = (int)(font.getStringBounds(content, frc).getWidth());
        int textheight = (int)(font.getStringBounds(content, frc).getHeight());

        boundingBox.setSize( new Dimension(textwidth, textheight));
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
