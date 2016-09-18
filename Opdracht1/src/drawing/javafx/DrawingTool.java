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
import drawing.persistency.DatabaseMediator;
import drawing.persistency.PersistencyMediator;
import drawing.persistency.SerializationMediator;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
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
    private PersistencyMediator mediator;
    
    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(1000,800);
        drawing = new Drawing("Aardappel", 1000, 800);
        mediator = new DatabaseMediator();
        mediator.init(readProperties("config.properties"));   
        
        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        
        Scene scene = new Scene(root, 1000, 800);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        paintable = new JavaFXPaintable(gc);
        addDrawings();
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        mediator.save(drawing);
        
        //drawing = mediator.load("Aardappel");
        draw();     
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public Properties readProperties(String filePath)
    {
        Properties props = new Properties();
        File file = new File(filePath);

        try (InputStream is = new FileInputStream(file)) {          
            props.load(is);
        } catch (Exception e) {
            System.out.println("Something went from with reading the file");
            System.out.println(e.getMessage());
        }
        
        return props;
    }
    
    public void addDrawings()
    {
        File file = new File("Kip.png");
        
        drawing.addDrawingItem(new Oval(30,60,new Point(560,70), Color.YELLOW));
        drawing.addDrawingItem(new Polygon(1, new Point[]{new Point(150,150), new Point(40,60), new Point(60, 300), new Point(130,400), new Point(260,260) }, new Point(40,60), Color.ORANGE));
        drawing.addDrawingItem(new PaintedText("Hallo", new Font("Helvetica", 20, 20), new Point(120, 100), Color.BLUE));
        drawing.addDrawingItem(new Spline(new Point[]{new Point(100,460), new Point(200,360), new Point(356, 550)}, 1, 20, new Point(100,360), Color.GREEN));
        drawing.addDrawingItem(new Image(file, 70.3, new Point(380,160), Color.GREEN));
    }
    
    public void draw()
    {
        drawing.paint(paintable);
    }
    
}
