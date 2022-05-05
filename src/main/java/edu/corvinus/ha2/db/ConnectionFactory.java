package edu.corvinus.ha2.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static ConnectionFactory connectionFactory = null;

    //Singleton pattern
    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            //InputStream inStream = new FileInputStream("db.properties");
            InputStream inStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("db.properties");
            Properties p = new Properties();
            p.load(inStream);
            String dname = (String) p.get("Dname");
            String url = (String) p.get("URL");
            String username = (String) p.get("Uname");
            String password = (String) p.get("password");
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

}
