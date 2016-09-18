/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.persistency;

import drawing.domain.Drawing;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oscar
 */
public class SerializationMediator implements PersistencyMediator {

    private Properties props;

    @Override
    public Drawing load(String nameDrawing) {
        System.out.println("Loading: " + nameDrawing + "...");
        Drawing drawing = null;
        String path = props.getProperty("Path") + nameDrawing + props.getProperty("FileType");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            drawing = (Drawing) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        if (drawing != null) {
            System.out.println("Loaded: " + nameDrawing);
        }
        return drawing;
    }

    @Override
    public boolean save(Drawing drawing) {
        System.out.println("Saving " + drawing.getName() + "...");
        String path = props.getProperty("Path") + drawing.getName() + props.getProperty("FileType");
        File file = new File(path);

        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.out.println("Couldn't find file: " + file.getAbsolutePath());
        }

        boolean greatSuccess = false;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(drawing);
            greatSuccess = true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.err);
        }

        System.out.println("Saving succesful");
        return greatSuccess;
    }

    @Override
    public boolean init(Properties props) {
        this.props = props;

        return true;
    }

}
