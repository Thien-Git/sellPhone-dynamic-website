package beans;

public class Account {
	private String user, password;
	private int role;
	private String name, address, phone;
	private int check;
	
	public Account() {
		super();
	}

	public Account(String user, String password, int role, String name, String address, String phone, int check) {
		this.user = user;
		this.password = password;
		this.role = role;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.check = check;
	}

	public Account(String user, String password) {
		this.user = user;
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

	public boolean validate(String user_mail, String password) {

		if (user_mail == "") {
			return false;
		}

		if (password == "") {
			return false;
		}

		if (!user_mail.matches("\\w+@\\w+\\.\\w+\\.\\w+")) {
			return false;
		}

		if (password.length() < 1) {
			return false;
		} else if (password.matches("\\w*\\s+\\w*")) {
			return false;
		}
		
		return true;
	}
}
