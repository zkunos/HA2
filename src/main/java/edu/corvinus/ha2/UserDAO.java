package edu.corvinus.ha2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


    public boolean add(UserBean u) {
        try {
            String queryString = "INSERT INTO users(email, password, name, surname) VALUES(?,?,?,?)";
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
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
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
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            }

            catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
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
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return false;

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
                users.add(new UserBean(
                        resultSet.getString("EMAIL"),
                        resultSet.getString("NAME"),
                        resultSet.getString("SURNAME"),
                        resultSet.getString("PASSWORD")
                ));
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return users;
    }
}
