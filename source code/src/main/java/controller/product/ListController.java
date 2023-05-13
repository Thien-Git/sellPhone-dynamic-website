package controller.product;
//controller của chức năng hiển thị thông tin sản phẩm trong data source
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import context.DBContext;
import model.Product;

@WebServlet("/ListController")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBContext dbContext;
       
    public ListController() {
        super();
    }
    
    public void init()  {
		ServletContext context = getServletContext(); //Application
		
		String jdbcURL = context.getInitParameter("jdbcURL");
		String jdbcUsername = context.getInitParameter("jdbcUsername");
		String jdbcPassword = context.getInitParameter("jdbcPassword");
		
		dbContext = new DBContext(jdbcURL, jdbcUsername, jdbcPassword);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();//Trả về phần sau của URL(đã trừ phần url chính) của request này.
		
		try {
			switch (action) {
			default:
				listProduct(request, response);
				break;
			}
		} catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}

	private void listProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List<Product>listProduct = dbContext.listAllProducts();
		request.setAttribute("listProduct", listProduct);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}


}















