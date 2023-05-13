package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Product;
import model.ProductOrders;

//OrdersDAO: các phương thức làm việc với bảng Orders (chắc là thêm, duyệt, xóa, sửa)
public class OrdersDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	// Use for init method in controller
	public OrdersDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null || !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	//them san pham Order vao CSDL MySQL
	
	public void insertToOrderDetailDB(int product_id, int amount_product, int price_product) throws SQLException {
		String sql = "INSERT INTO orders_detail (product_id, amount_product, price_product) VALUES (?, ?, ?)";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);

		statement.setInt(1, product_id);
		statement.setDouble(2, amount_product);
		statement.setDouble(3, price_product);
        
		statement.executeUpdate();
		statement.close();
		disconnect();
	}
	
	
	

}
