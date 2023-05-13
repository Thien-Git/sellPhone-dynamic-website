package controller;

//controller của chức năng xem thông tin chi tiết của một sản phẩm
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import context.DBContext;
import model.Product;

@WebServlet("/InformationProductController")
public class InformationProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBContext dbContext;

	public InformationProductController() {
		super();
	}

	public void init() {
		ServletContext context = getServletContext(); // Application

		String jdbcURL = context.getInitParameter("jdbcURL");
		String jdbcUsername = context.getInitParameter("jdbcUsername");
		String jdbcPassword = context.getInitParameter("jdbcPassword");

		dbContext = new DBContext(jdbcURL, jdbcUsername, jdbcPassword);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		String action = request.getServletPath();// Trả về phần sau của URL(đã trừ phần url chính) của request này.

		try {
			switch (action) {
			default:
				infoProduct(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void infoProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("product_id"));
		Product product = dbContext.getProductById(id);
		request.setAttribute("product", product);
		request.getRequestDispatcher("/infoProduct.jsp").forward(request, response);
	}
}
