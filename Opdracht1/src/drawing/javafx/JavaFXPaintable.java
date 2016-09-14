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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.lang.NullPointerException;

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
        javafx.scene.paint.Color temp = convertToJavaFxColor(color);
        gc.setFill(temp);
        gc.setStroke(temp);
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
    public void paintArc(Point p0, Point p2, int degree, int weight) {
        gc.setLineWidth(weight);
        gc.beginPath();
        gc.moveTo(p0.getX(), p2.getY());

        double radians = degree * Math.PI / 180;
        final double ARCCONSTANT = 45 * Math.PI / 180;

        double distance = p0.distance(p2);
        double height = (Math.tan(radians) * distance) / 2;

        //Needs complex numbers to find p1 which is at a 45 degree angle
        //from p0 and p2 and on the median of the line between p0 and p2.
        double radius = height / 2 + Math.pow(distance, 2) / (8 * height);

        gc.arcTo(p0.getX(), p0.getY(), p2.getX(), p2.getY(), radius);
        gc.lineTo(p2.getX(), p2.getY());
        gc.stroke();
    }

    @Override
    public void paintImage(Image image) {
        try {
            gc.drawImage(convertToJavaFxImage(image.getFile()), image.getAnchor().getX(), image.getAnchor().getY(), image.getSize(), image.getSize());
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void clear() {
        Canvas canvas = gc.getCanvas();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private static javafx.scene.text.Font convertToJavaFxFont(Font font) {
        return new javafx.scene.text.Font(font.getFontName(), font.getSize());
    }

    private static javafx.scene.image.Image convertToJavaFxImage(File file) {
        //Image cannot be read from an url thus an inputstream is required.

        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException | NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
        return new javafx.scene.image.Image(is);
    }

    private static javafx.scene.paint.Color convertToJavaFxColor(Color color) {
        double red = ((double) color.getRed()) / 255;
        double green = ((double) color.getGreen()) / 255;
        double blue = ((double) color.getBlue()) / 255;
        double alpha = ((double) color.getAlpha()) / 255;

        return new javafx.scene.paint.Color(red, green, blue, alpha);
    }
}
