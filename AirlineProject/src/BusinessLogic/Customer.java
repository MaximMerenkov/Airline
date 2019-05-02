package BusinessLogic;

public class Customer extends Person {
	protected String custUserName;
	protected String custPassword;
	
	public Customer() {
		
	}
	
	public Customer(String firstName, String lastName, String address, String zipCode,
			String state, String email, String ssn, String securityQuestion,
			String custUserName, String custPassword) {
		super();
		this.custUserName = custUserName;
		this.custPassword = custPassword;
	}
	
	public String getCustUserName() {
		return custUserName;
	}

	public void setCustUserName(String custUserName) {
		this.custUserName = custUserName;
	}

	public String getCustPassword() {
		return custPassword;
	}

	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}
	
	/*public String bookAFlight() {
		Flight test = new Flight();
		Flight.ConfirmedBookings CB = test.new ConfirmedBookings();
		CB.confirmBooking(this.custUserName);
		
		return "Flight confirmed";
	}
	// requestFlightCancel() {}
	*/
}