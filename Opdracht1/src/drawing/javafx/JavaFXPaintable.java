/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.javafx;

import drawing.domain.Image;
import drawing.domain.Oval;
import drawing.domain.PaintedText;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.io.File;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light;

/**
 *
 * @author Oscar
 */
public class JavaFXPaintable implements Paintable {

    private final GraphicsContext gc;

    public JavaFXPaintable(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void setColor(Color color) {
        gc.setFill(convertToJavaFxColor(color));
    }

    @Override
    public void paintLine(Point from, Point to, int weight) {
        gc.setLineWidth(weight);
        gc.strokeLine(from.getX(), from.getY(), to.getX(), to.getY());
    }

    @Override
    public void paintOval(Oval oval) {
        gc.fillOval(oval.getAnchor().getX(), oval.getAnchor().getY(), oval.getWidth(), oval.getHeight());
    }

    @Override
    public void paintText(PaintedText text) {
        gc.setFont(convertToJavaFxFont(text.getFont()));
        gc.fillText(text.getContent(), text.getAnchor().getX(), text.getAnchor().getY());
    }
    
    @Override
    public void paintArc(Point a, Point b, int degree)
    {
        gc.beginPath();
        double distance = a.distance(b);
        double height = (Math.tan((double)degree)*distance)/2;
        
        double radius = height/2 + Math.pow(distance, 2)/(8*height);
        gc.arcTo(a.getX(), a.getY(), b.getX(), b.getY(), radius);
        gc.fill();
        gc.closePath();
    }

    @Override
    public void paintImage(Image image) {
        gc.drawImage(convertToJavaFxImage(image.getFile()), image.getAnchor().getX(), image.getAnchor().getY(), image.getSize(), image.getSize());
    }

    @Override
    public void clear() {
        Canvas canvas = gc.getCanvas();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    
    private static javafx.scene.text.Font convertToJavaFxFont(Font font)
    {
        return new javafx.scene.text.Font(font.getFontName(), font.getSize());
    }
    
    private static javafx.scene.image.Image convertToJavaFxImage(File file)
    {
       return new javafx.scene.image.Image(file.getAbsolutePath());
    }
    
    private static javafx.scene.paint.Color convertToJavaFxColor(Color color)
    {
        return new javafx.scene.paint.Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }    
}
