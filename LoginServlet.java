package com.example.demo;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<String,String> users = new HashMap<>();
        users.put("student1","pass1");
        users.put("student2","pass2");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(users.containsKey(username) && users.get(username).equals(password)){

            HttpSession session = request.getSession();
            session.setAttribute("username",username);

            Cookie userCookie = new Cookie("username",username);
            userCookie.setMaxAge(60*60);
            response.addCookie(userCookie);

            response.sendRedirect("DashboardServlet");
        }else {
            response.getWriter().println("<h3>Invalid Username or Password</h3>");
        }
    }
}
