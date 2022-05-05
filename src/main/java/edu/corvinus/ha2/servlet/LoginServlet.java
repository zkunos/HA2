package edu.corvinus.ha2.servlet;

import edu.corvinus.ha2.bean.UserBean;
import edu.corvinus.ha2.bean.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

import static edu.corvinus.ha2.Helpers.getMD5;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // get request parameters for email and password
        String email = request.getParameter("email").trim();
        String password = request.getParameter("pwd").trim();

        UserDAO users = new UserDAO();
        UserBean u = users.findByEmail(email);

        if (u != null && u.getPasswordHash().equals(getMD5(password))) {
            //get the old session and invalidate
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            //generate a new session
            HttpSession newSession = request.getSession(true);

            //setting session to expiry in 12 mins
            newSession.setMaxInactiveInterval(12 * 60);

            response.addCookie(new Cookie("email", email));
            response.addCookie(new Cookie("name", u.getName()));

            response.sendRedirect("protected/loginSuccess.jsp");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either username or password is wrong.</font>");
            rd.include(request, response);
        }
    }
}