package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Account;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");

		if (action == null) {
			request.getRequestDispatcher("ListController").forward(request, response);
		} else if (action.equals("login")) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

		HttpSession session = request.getSession();
		session.setAttribute("formsubmit", null);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String formsubmit = request.getParameter("formsubmit");

		HttpSession session = request.getSession();
		session.setAttribute("formsubmit", formsubmit);
		
		String action = request.getParameter("action");
		
		if (action == null) {
			request.getRequestDispatcher("ListController").forward(request, response);
		} else if (action.equals("dologin")) {

			// Colect data from a login form

			String user = request.getParameter("user");
			String password = request.getParameter("password");

			//Tao cookie
			Cookie cookie = new Cookie("user", user);
			response.addCookie(cookie);
			cookie.setMaxAge(300);
			
			Account acc = new Account();
			acc.setName(user);
			acc.setPassword(password);

			if (!acc.validate(user, password)) {
				response.sendRedirect("login.jsp");
				session.setAttribute("error", "Invalid syntax");
			} else {

				//Read information of acount in web.xml

				ServletContext context = getServletContext();
				String uid = context.getInitParameter("adminUsername");
				String pwd = context.getInitParameter("adminPassword");

				//Check acount - use validate code in assignment 1 to valid user

				if (user != "" && acc.getName().equalsIgnoreCase(uid) && acc.getPassword().equals(pwd)) {
					
					//set session
					
					session.setAttribute("user", user);
					session.setAttribute("error", "no error");

					//Login is valid, now redirect to index page of admin

					response.sendRedirect("/PRJ321x_A3ver1/admin/admin.jsp");
				} else {
					session.setAttribute("error", "Wrong username or password");
					response.sendRedirect("login.jsp");
				}
			}
			
		}
	}

}
