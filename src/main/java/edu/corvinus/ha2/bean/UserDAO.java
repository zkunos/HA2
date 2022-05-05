package edu.corvinus.ha2.bean;

import edu.corvinus.ha2.db.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    private Connection getConnection() {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public boolean createTableUsers() {
        try {
            String queryString = "CREATE TABLE USERS" + "(" + "EMAIL    varchar(255) NOT NULL, " + "PASSWORD varchar(255) NOT NULL, " + "NAME     varchar(255) NOT NULL, " + "SURNAME  varchar(255) NOT NULL, " + "PRIMARY KEY (EMAIL)" + ")";

            connection = getConnection();

            DatabaseMetaData dbmd = connection.getMetaData();
            ResultSet rs = dbmd.getTables(null, "USER", "USERS", null);

            if (!rs.next()) {
                ptmt = connection.prepareStatement(queryString);
                ptmt.executeUpdate();
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null) ptmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean add(UserBean u) {
        try {
            String queryString = "INSERT INTO users(email, name, surname, password) VALUES(?,?,?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, u.getEmail());
            ptmt.setString(2, u.getName());
            ptmt.setString(3, u.getSurname());
            ptmt.setString(4, u.getPasswordHash());
            ptmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null) ptmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean update(UserBean u) {
        try {
            String queryString = "UPDATE users SET password=?, name=?, surname=? WHERE email=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, u.getPasswordHash());
            ptmt.setString(2, u.getName());
            ptmt.setString(3, u.getSurname());
            ptmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null) ptmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public boolean delete(UserBean u) {
        try {
            String queryString = "DELETE FROM users WHERE email=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, u.getEmail());
            ptmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null) ptmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public UserBean findByEmail(String email) {
        UserBean u = null;
        try {
            String queryString = "SELECT * FROM users WHERE email=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, email);
            resultSet = ptmt.executeQuery();

            if (resultSet.next()) {
                u = new UserBean(resultSet.getString("EMAIL"), resultSet.getString("NAME"), resultSet.getString("SURNAME"), resultSet.getString("PASSWORD"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (ptmt != null) ptmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return u;
    }

    public ArrayList<UserBean> findAll() {
        ArrayList<UserBean> users = null;
        try {
            String queryString = "SELECT * FROM users";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();

            users = new ArrayList<UserBean>();

            while (resultSet.next()) {
                users.add(new UserBean(resultSet.getString("EMAIL"), resultSet.getString("NAME"), resultSet.getString("SURNAME"), resultSet.getString("PASSWORD")));
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (ptmt != null) ptmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
}