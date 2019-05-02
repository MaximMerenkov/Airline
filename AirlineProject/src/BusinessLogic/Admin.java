package BusinessLogic;

public class Admin extends Person {
	private String adminUserName;

	private String adminPassword;
	
	
	Admin() {
		
	}
	
	Admin(String firstName, String lastName, String address, String zipCode,
			String state, String email, String ssn, String securityQuestion,
			String adminUserName, String adminPassword) {
		super();
		this.adminUserName = adminUserName;
		this.adminPassword = adminPassword;
	}
	
	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	// public String bookAFlight() {}
	// public String cancelAFlight() {}
}