/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.javafx;

import drawing.domain.Drawing;
import drawing.domain.Image;
import drawing.domain.Oval;
import drawing.domain.PaintedText;
import drawing.domain.Polygon;
import drawing.domain.Spline;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Oscar
 */
public class DrawingTool extends Application {
    
    private JavaFXPaintable paintable;
    private Drawing drawing;
    
    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(1000,800);
        drawing = new Drawing("Aardappel", 1000, 800);
        
        /*Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
        
        @Override
        public void handle(ActionEvent event) {
        System.out.println("Hello World!");
        }
        });*/
        
        StackPane root = new StackPane();
        //root.getChildren().add(btn);
        root.getChildren().add(canvas);
        
        Scene scene = new Scene(root, 1000, 800);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        paintable = new JavaFXPaintable(gc);
        addDrawings();
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        draw();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void addDrawings()
    {
        File file = new File("Kip.png");
        
        //drawing.addDrawingItem(new Oval(30,70,new Point(560,70), Color.CYAN));
        //drawing.addDrawingItem(new Polygon(1, new Point[]{new Point(150,150), new Point(40,60), new Point(60, 300), new Point(130,400), new Point(260,260) }, new Point(40,60), Color.ORANGE));
        //drawing.addDrawingItem(new PaintedText("Hallo", new Font("Helvetica", 20, 20), new Point(120, 100), Color.DARK_GRAY));
        drawing.addDrawingItem(new Spline(new Point[]{new Point(100,460), new Point(200,460)}, 1, 20, new Point(100,450), Color.YELLOW));
        //drawing.addDrawingItem(new Image(file, 70.3, new Point(180,160), Color.GREEN));
    }
    
    public void draw()
    {
        drawing.paint(paintable);
    }
    
}
