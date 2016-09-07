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
import java.awt.Point;



/**
 *
 * @author Oscar
 */
public interface Paintable {
    void setColor(Color color);
    void paintLine(Point from, Point to, int weight);
    void paintArc(Point a, Point b, int degree, int weight);
    void paintOval(Oval oval);
    void paintText(PaintedText text);
    void paintImage(Image image);
    void clear();
    
}
