package BusinessLogic;
import java.util.Date;

import Database.DatabaseConnector;

import java.math.BigDecimal;

public class Flight {
	private int flightId;
	private String departureCity;
	private String departureDate;
	private String departureTime;
	private String arrivalCity;
	private String basicPrice;
	private final int seatCapacity = 200;
	private int availableSeats;
	
	public Flight() {
		
	}
	
	public Flight(String departureCity, String departureDate, String arrivalCity) {
		this.departureCity = departureCity;
		this.departureDate = departureDate;
		this.arrivalCity = arrivalCity;
	}
	
	 public Flight(int flightId, String departureCity, String departureDate,
			String departureTime, String arrivalCity, String basicPrice,
			int availableSeats) {
		this.flightId = flightId;
		this.departureCity = departureCity;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalCity = arrivalCity;
		this.basicPrice = basicPrice;
		this.availableSeats = availableSeats;
		
	}
	 
	
		
		/*public void confirmBooking(String custUsername) {
			DatabaseConnector.dbPushBooking(ticketNumber, flightId, custUsername);
		}
        */
		public int getFlightId() {
			return flightId;
		}

		public void setFlightId(int flightId) {
			this.flightId = flightId;
		}

		public String getDepartureCity() {
			return departureCity;
		}

		public void setDepartureCity(String departureCity) {
			this.departureCity = departureCity;
		}

		public String getDepartureDate() {
			return departureDate;
		}

		public void setDepartureDate(String departureDate) {
			this.departureDate = departureDate;
		}

		public String getDepartureTime() {
			return departureTime;
		}

		public void setDepartureTime(String departureTime) {
			this.departureTime = departureTime;
		}

		public String getArrivalCity() {
			return arrivalCity;
		}

		public void setArrivalCity(String arrivalCity) {
			this.arrivalCity = arrivalCity;
		}

		public String getBasicPrice() {
			return basicPrice;
		}

		public void setBasicPrice(String basicPrice) {
			this.basicPrice = basicPrice;
		}

		public int getAvailableSeats() {
			return availableSeats;
		}

		public void setAvailableSeats(int availableSeats) {
			this.availableSeats = availableSeats;
		}

		public int getSeatCapacity() {
			return seatCapacity;
		}
	
}