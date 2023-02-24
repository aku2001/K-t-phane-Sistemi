
public class Admin {
	
	private String username;
	private String password;
	private int budget;
	
	
	public Admin(String username, String password, int budget) {
		super();
		this.username = username;
		this.password = password;
		this.budget = budget;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getBudget() {
		return budget;
	}


	public void setBudget(int budget) {
		this.budget = budget;
	}
	
	
}
