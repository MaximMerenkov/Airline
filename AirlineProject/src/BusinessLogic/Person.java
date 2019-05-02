package BusinessLogic;
public class Person {
	private String firstName;
	private String lastName;
	private String address;
	private String zipCode;
	private String state;
	private String email;
	private String ssn;
	private String securityQuestion;
	private String answer;
	private String password;
	private String userName;
	private boolean isAdmin;

	public Person() {
		
	}
	
	public Person(String username) {
		
	}
	
	public Person(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	public Person(String firstName, String lastName, String address, String zipCode,
			String state, String email, String ssn, String securityQuestion, String answer, String userName, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.zipCode = zipCode;
		this.state = state;
		this.email = email;
		this.ssn = ssn;
		this.securityQuestion = securityQuestion;
		this.answer = answer;
		this.userName = userName;
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}