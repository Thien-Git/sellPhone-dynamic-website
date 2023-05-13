package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;

//lớp thiết lập kết nối với data source
public class DBContext {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	// Use for init method in controller
	public DBContext(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	//--------------------------------Home---------------------------------
	
	public List<Product> listAllProducts() throws SQLException {

		List<Product> listProduct = new ArrayList<>();

		String sql = "select * from products";
		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("product_id");
			String name = resultSet.getString("product_name");
			String des = resultSet.getString("product_des");
			Double price = resultSet.getDouble("product_price");
			String imgSource = resultSet.getString("product_img_source");
			String type = resultSet.getString("product_type");
			String brand = resultSet.getString("product_brand");

			Product product = new Product(id, name, des, price, imgSource, type, brand);
			listProduct.add(product);
		}

		resultSet.close();
		statement.close();
		disconnect();

		return listProduct;
	}

	//--------------------------------Infomation Product---------------------------------
	
	public Product getProductById(int id) throws SQLException {
		Product product = null;
		String sql = "SELECT * FROM products WHERE product_id = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String name = resultSet.getString("product_name");
			String des = resultSet.getString("product_des");
			Double price = resultSet.getDouble("product_price");
			String imgSource = resultSet.getString("product_img_source");
			String type = resultSet.getString("product_type");
			String brand = resultSet.getString("product_brand");
			int number = 1;
			product = new Product(id, name, des, price, imgSource, type, brand, number);
		}
		resultSet.close();
		statement.close();
		return product;
	}
	
	//--------------------------------Search---------------------------------

	public List<Product> getListProductByName(String name) throws SQLException {
		List<Product> listProduct = new ArrayList<>();
		String sql = "SELECT * FROM products where product_name = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, name);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			int id = resultSet.getInt("product_id");
			String des = resultSet.getString("product_des");
			Double price = resultSet.getDouble("product_price");
			String imgSource = resultSet.getString("product_img_source");
			String type = resultSet.getString("product_type");
			String brand = resultSet.getString("product_brand");

			Product product = new Product(id, name, des, price, imgSource, type, brand);
			listProduct.add(product);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return listProduct;
	}
	
	//--------------------------------Register_Create Account---------------------------------
	
	public boolean exist(String user_mail) throws SQLException {
		String sql = "select count(*) as count from account where user_mail=?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, user_mail);

		ResultSet rs = statement.executeQuery();

		int count = 0;

		if (rs.next()) {
			count = rs.getInt("count");
		}
		rs.close();
		disconnect();

		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean existProduct_name(String product_name) throws SQLException {
		String sql = "select count(*) as count from products where product_name=?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		
		statement.setString(1, product_name);
		
		ResultSet rs = statement.executeQuery();
		
		int count = 0;
		
		if (rs.next()) {
			count = rs.getInt("count");
		}
		rs.close();
		disconnect();
		
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public void createAccount(String user_mail, String password, String account_role, String user_name, String user_address, String user_phone) throws SQLException {
		String sql = "insert into account (user_mail, password, account_role, user_name, user_address, user_phone) "
				+ "values (?, ?, ?, ?, ?, ?)";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);

		statement.setString(1, user_mail);
		statement.setString(2, password);
		statement.setString(3, account_role);
		statement.setString(4, user_name);
		statement.setString(5, user_address);
		statement.setString(6, user_phone);

		statement.executeUpdate();

		statement.close();
		disconnect();
	}
	
	//--------------------------------Them du lieu vao bang oders_detai---------------------------------
	
	public void insert(int product_id, int amount_product, int price_product) throws SQLException {
		String sql = "insert into orders_detail (product_id, amount_product, price_product) values (?, ?, ?)";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		statement.setInt(1, product_id);
		statement.setDouble(2, amount_product);
		statement.setDouble(3, price_product);
        
		statement.executeUpdate();
		statement.close();
		disconnect();
	}
	
	//--------------------------------PayController---------------------------------
	
	public void createOrder(String user_mail, int id, int order_status, String order_discount_code, String user_address) throws SQLException {
		String sql = "insert into orders (user_mail, order_status, order_discount_code, order_address) "
				+ "values (?, ?, ?, ?)";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		statement.setString(1, user_mail);
		statement.setInt(2, order_status);
		statement.setString(3, order_discount_code);
		statement.setString(4, user_address);

		statement.executeUpdate();
		statement.close();
		disconnect();
	}

	
	
	
}





















