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
public class TestScript {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Drawing test = new Drawing("Piet", 200, 200);
        
        test.addDrawingItem(new Oval(30,30,new Point(70,70), Color.CYAN));
        test.addDrawingItem(new Polygon(20, new Point[]{new Point(50,50), new Point(40,60), new Point(60,60)}, new Point(40,40), Color.ORANGE));
        test.addDrawingItem(new PaintedText("Hallo Iedereen", null, new Point(120, 100), Color.DARK_GRAY));
        test.addDrawingItem(new Spline(new Point[]{new Point(20,160), new Point(30,150), new Point(40,170)}, 30, 20, new Point(20,150), Color.YELLOW));
        test.addDrawingItem(new Image(null, 70.3, new Point(140,60), Color.GREEN));
       
        //test.getItems().stream().forEach(x -> System.out.println(x));
        for(DrawingItem x : test.getItems())
        {
            System.out.println(x);
        }
    }
}
