package BusinessLogic;

public class Bookings {
	private int ticketNumber;
	private int flightId;
	private String username;
	
	
	public Bookings() {
		
	}
	public Bookings(int ticketNumber, int flightId, String username ) {
				
		this.ticketNumber = ticketNumber;
		this.flightId = flightId;
		this.username = username;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

}
