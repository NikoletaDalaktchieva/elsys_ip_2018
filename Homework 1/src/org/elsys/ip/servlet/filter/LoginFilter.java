package org.elsys.ip.servlet.filter;

import org.elsys.ip.servlet.controller.UserServlet;
import org.elsys.ip.servlet.model.User;
import org.elsys.ip.servlet.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		boolean authorized = true;

		UserService userService = new UserService();

		String username = request.getParameter("name");
		String password = request.getParameter("password");

		List<User> users =  userService.getUsers();

		if(UserService.getLoggedUser() == null) {
			if (username != null && password != null) {
				for (User user : users) {
					if (username.equals(user.getName()) && password.equals(user.getPassword())) {
						authorized = true;
						break;
					} else authorized = false;
				}
			}
		}

		// check username and password (can be hardcoded, can use the userService)
		// add if the person is logged in to a cookie (Google it), so that we do not check at every page
		if (authorized) {
			chain.doFilter(request, response);
		} else {
			request.setAttribute("error", "Wrong username or password!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		UserService.add(new User(1, "admin", "admin@admin.bg", "123"));
		UserService.add(new User(2, "user", "user@user.bg", "123"));
		UserService.add(new  User(3, "Name", "name@name.bg", "123"));
	}

}
