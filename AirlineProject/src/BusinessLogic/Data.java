package BusinessLogic;

public class Data {
	private Person person;
	private Bookings bookings;
	private Flight flight;
	
	public Bookings getBookings() {
		return bookings;
	}
	public void setBookings(Bookings bookings) {
		this.bookings = bookings;
	}
	
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	

}
