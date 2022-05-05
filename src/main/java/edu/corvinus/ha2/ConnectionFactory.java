package edu.corvinus.ha2;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static ConnectionFactory connectionFactory = null;

    public Connection getConnection() {
        Connection conn = null;
        try {
            FileInputStream fis = new FileInputStream("./db.prop");
            Properties p = new Properties ();
            p.load (fis);
            String dname = (String) p.get ("Dname");
            String url = (String) p.get ("URL");
            String username = (String) p.get ("Uname");
            String password = (String) p.get ("password");
            System.out.println(url);
            Class.forName(dname);
            conn = DriverManager.getConnection(url, username, password);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    //Singleton pattern
    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }

}
