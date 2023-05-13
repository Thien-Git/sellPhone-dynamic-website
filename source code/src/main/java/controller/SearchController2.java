package controller;
//controller của chức năng tìm kiếm dành cho người dùng
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import context.DBContext;
import model.Product;

@WebServlet("/SearchController2")
public class SearchController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBContext dbContext;

	public SearchController2() {
		super();
	}
	
	public void init() {
		ServletContext context = getServletContext();
		String jdbcURL = context.getInitParameter("jdbcURL");
		String jdbcUsername = context.getInitParameter("jdbcUsername");
		String jdbcPassword = context.getInitParameter("jdbcPassword");
		
		dbContext = new DBContext(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
//		out.print(action);
		
		if (action == null) {
			request.getRequestDispatcher("ListController").forward(request, response);
		}else if(action.equals("search")) {
			try {
				searchProduct(request, response);
			} catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
//		PrintWriter out = response.getWriter();
		List<Product> listProduct = new ArrayList<>();
		String product_name = request.getParameter("product_name");
		listProduct = dbContext.getListProductByName(product_name);
		if (listProduct.size() >0) {
			request.setAttribute("listProduct", listProduct);
			request.getRequestDispatcher("search.jsp").forward(request, response);
		}else {
//			out.print("loi r");
			request.setAttribute("error_search", "Not found");
			request.getRequestDispatcher("ListController").forward(request, response);
		}
	}
	



}



















