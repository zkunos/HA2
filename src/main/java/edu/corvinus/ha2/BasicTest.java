package edu.corvinus.ha2;

import edu.corvinus.ha2.bean.UserBean;
import edu.corvinus.ha2.bean.UserDAO;

import static edu.corvinus.ha2.Helpers.getMD5;

public class BasicTest {
    public static void main(String[] args) {

        UserDAO users = new UserDAO();
        users.createTableUsers();

        UserBean u1 = new UserBean(
                "z.kunos@gmail.com",
                "Zoltan",
                "Kunos",
                getMD5("NT2022-1")
        );

        UserBean u2 = new UserBean(
                "daniell@gmail.com",
                "Daniel",
                "Lozano",
                getMD5("NT2022-2")
        );

        UserBean u3 = new UserBean(
                "mlajtai@gmail.com",
                "Monika",
                "Lajtai",
                getMD5("NT2022-3")
        );

        UserBean u4 = new UserBean(
                "liz.t@gmail.com",
                "Liz",
                "Taylor",
                getMD5("NT2022-4")
        );

        UserBean u5 = new UserBean(
                "thomas.leg@gmail.com",
                "Thomas",
                "Leg",
                getMD5("NT2022-5")
        );

        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);

        /*for (UserBean u0 : users.findAll())  {
            System.out.println(u0.toString());
        }*/
    }
}
