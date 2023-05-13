package controller;

//controller của chức năng lưu thông tin chi tiết đơn hàng và khách hàng vào datasource
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import context.DBContext;

@WebServlet("/PayController")
public class PayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBContext dbContext;

	public PayController() {
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
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String customer_name = request.getParameter("customer_name");
		String customer_address = request.getParameter("customer_address");
		String discount_code = request.getParameter("discount_code");
		int order_status = 1;
		try {
			dbContext.createOrder(customer_name, 0, order_status, discount_code, customer_address);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("orderSuccess.jsp").forward(request, response);
	}

}
