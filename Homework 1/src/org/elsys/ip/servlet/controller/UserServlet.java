package org.elsys.ip.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elsys.ip.servlet.model.User;
import org.elsys.ip.servlet.service.UserService;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String name;
	
	private UserService userService = new UserService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		User user = userService.getByName(request.getParameter("name"));
		name = user.getName();
		if (user != null) {
			out.print("Welcome, " + user.getName()+ ". Your email is: " + user.getEmail());
		} else {
			out.println("Welcome, anonymous.");
		}

		System.out.println("H");
		response.setContentType("text/html");
		request.setAttribute("user", user);
		getServletContext().getRequestDispatcher("/WEB-INF/lib/user.jsp")
				.forward(request, response);

		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doPut(request, response);
        doGet(request, response);


	}

	protected void doPut (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		System.out.println("Here");
		User user = userService.getByName(name);



		String id = request.getParameter("id");
		String name = request.getParameter("n");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		System.out.println(name);

        int id_num = Integer.valueOf(id);
        if(id != null && !(id_num == user.getId())){
            user.setId(id_num);
        }

        if(email != null && !(email.equals(user.getEmail()))){
			user.setEmail(email);
		}

		if(password != null && !(password.equals(user.getEmail()))){
			user.setPassword(password);
		}

        response.setContentType("text/html");
        request.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/admin")
                .forward(request, response);
	}

}
