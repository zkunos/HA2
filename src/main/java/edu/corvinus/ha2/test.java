package edu.corvinus.ha2;

import java.nio.file.Path;
import java.sql.Connection;

public class test {

    public static void main(String[] args) {
        //String cwd = Path.of("").toAbsolutePath().toString();

        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        UserDAO users = new UserDAO();

        for (UserBean u : users.findAll())  {
            System.out.println(u.toString());
        }


    }
}
