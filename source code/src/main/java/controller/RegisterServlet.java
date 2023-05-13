package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Account;
import context.DBContext;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBContext dbContext;

	public RegisterServlet() {
		super();
	}

	public void init() {
		ServletContext context = getServletContext(); // Application

		String jdbcURL = context.getInitParameter("jdbcURL");
		String jdbcUsername = context.getInitParameter("jdbcUsername");
		String jdbcPassword = context.getInitParameter("jdbcPassword");

		dbContext = new DBContext(jdbcURL, jdbcUsername, jdbcPassword);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else if (action.equals("createaccount")) {
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");

		if (action.equals("createaccount")) {

			String user_mail = request.getParameter("user_mail");
			String password = request.getParameter("password");
			String repeatPassword = request.getParameter("repeatpassword");
			String account_role = request.getParameter("account_role");
			String user_name = request.getParameter("user_name");
			String user_address = request.getParameter("user_address");
			String user_phone = request.getParameter("user_phone");

			request.setAttribute("user_mail", user_mail);
			request.setAttribute("password", "");
			request.setAttribute("repeatpassword", "");
			request.setAttribute("message", "");
			request.setAttribute("formsubmit", "formsubmited");

			// Check password = repeat password

			if (!password.equals(repeatPassword)) {
				request.setAttribute("message", "Password do not match");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			} else {
				Account account = new Account(user_mail, password);

				if (!account.validate(user_mail, password)) {
					request.setAttribute("message", "MK hoac email khong dung");
					request.getRequestDispatcher("/register.jsp").forward(request, response);
				} else {
					try {
						// check user_mail exist
						if (dbContext.exist(user_mail)) {
							request.setAttribute("message", "user_mail da dk");
							request.getRequestDispatcher("/register.jsp").forward(request, response);
						} else {
							// create user
							dbContext.createAccount(user_mail, password, account_role, user_name, user_address, user_phone);
							request.getRequestDispatcher("/registered.jsp").forward(request, response);
						}
					} catch (SQLException e) {
						request.getRequestDispatcher("/error.jsp").forward(request, response);
						e.printStackTrace();
					}
				}
			}
		} else {
			out.print("unrecognised action");
			return;
		}
		
	}

}
