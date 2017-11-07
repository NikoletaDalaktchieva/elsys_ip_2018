package org.elsys.ip.servlet.controller;

import org.elsys.ip.servlet.model.User;
import org.elsys.ip.servlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUser extends HttpServlet {


    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub


        String id = request.getParameter("id");
        String name = request.getParameter("n");
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        if((id != null && id != "")&&
                (name != null && name  != "") &&
                (email != null && email  != "") &&
                (password != null && password  != "")){
            UserService.add(new User(Integer.valueOf(id), name, email, password));
            response.sendRedirect("/admin");
            return;
        }


        response.setContentType("text/html");
        getServletContext().getRequestDispatcher("/WEB-INF/lib/addUser.jsp")
                .forward(request, response);


    }
}
