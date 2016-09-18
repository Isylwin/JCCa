/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.persistency;

import drawing.domain.Drawing;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Oscar
 */
public class DatabaseMediator implements PersistencyMediator {

    private Properties properties;
    private Connection connection;

    @Override
    public Drawing load(String nameDrawing) {
        Drawing drawing = null;
        System.out.println("Loading drawing: " + nameDrawing + " from the database");

        try {
            initConnection();

            String sql = "select Drawing from drawing where Name=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nameDrawing);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                byte[] st = (byte[]) rs.getObject(1);
                ByteArrayInputStream baip = new ByteArrayInputStream(st);
                ObjectInputStream ois = new ObjectInputStream(baip);
                drawing = (Drawing) ois.readObject();
            }
            System.out.println("Loading succesfull.");
            statement.close();

            closeConnection();
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace(System.err);
        }

        return drawing;
    }

    @Override
    public boolean save(Drawing drawing) {
        boolean greatSuccess = false;
        System.out.println("Saving Drawing: " + drawing.getName() + " to database.");

        try {
            initConnection();

            deleteOld(drawing.getName());

            String sql = "insert into drawing(Drawing, Name) values (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(drawing);
            byte[] employeeAsBytes = baos.toByteArray();

            ByteArrayInputStream bais = new ByteArrayInputStream(employeeAsBytes);

            statement.setBinaryStream(1, bais);
            statement.setString(2, drawing.getName());

            statement.executeUpdate();

            System.out.println("Saved the drawing.");
            closeConnection();

            greatSuccess = true;
        } catch (SQLException | IOException ex) {
            ex.printStackTrace(System.err);
        }
        return greatSuccess;
    }

    @Override
    public boolean init(Properties props) {
        properties = props;
        return true;
    }

    private void deleteOld(String name) {
        try {
            String sql = "delete from drawing where Name=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }

    private void closeConnection() {
        try {
            System.out.println("Closing database connection...");
            connection.close();
            System.out.println("Database connection closed.");
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }

    private void initConnection() {
        try {
            System.out.println("Establishing database connection...");

            String dbUrl = properties.getProperty("DBUrl");
            String user = properties.getProperty("Username");
            String password = properties.getProperty("Password");

            connection = DriverManager.getConnection(dbUrl, user, password);

            System.out.println("Database connection successful!");
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }

}
