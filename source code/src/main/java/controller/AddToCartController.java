package controller;

//Controller của chức năng thêm một sản phẩm vào giỏ hàng
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import context.DBContext;
import model.Cart;
import model.Product;

@WebServlet("/AddToCartController")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBContext dbContext;
	Cart c = new Cart();

	public AddToCartController() {
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
		response.setContentType("text/html; charset=UTF-8");
		response.sendRedirect("cart.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else if (action.equals("addtocart")) {
			try {
				addToCardDB(request, response);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
		} else if (action.equals("remove")) {
			try {
				removeFromCart(request, response);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void addToCardDB(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		int id = Integer.parseInt(request.getParameter("product_id"));
		Product product = dbContext.getProductById(id);

		c.add(product);
		int size = c.getItems().size();

		Double total = c.getAmount();
		List<Product> items = c.getItems();

		HttpSession session = request.getSession();// session thi chuyen trang van con luu lai (trong phien lam viec)
		session.setAttribute("size", size);
		session.setAttribute("total", total);
		session.setAttribute("items", items);
		
		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}

	private void removeFromCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		c.remove(id);
		int size = c.getItems().size();
		Double total = c.getAmount();
		List<Product> items = c.getItems();
		
		HttpSession session = request.getSession();
		session.setAttribute("total", total);// session thi chuyen trang van con luu lai
		session.setAttribute("items", items);
		session.setAttribute("size", size);
		
		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}

}
