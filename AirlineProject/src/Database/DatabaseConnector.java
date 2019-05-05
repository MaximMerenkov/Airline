package Database;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;

import BusinessLogic.Bookings;
import BusinessLogic.Customer;
import BusinessLogic.Data;
import BusinessLogic.Flight;
import gui.AdminChoice;
import gui.AirlineLogin;
import gui.AlertBox;
import gui.CustomerChoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DatabaseConnector {

	public static void main(String[] args) {
		
		
		String  databaseURL = "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
		String databaseUser = "root";
		String databasePass = "Westsmyrna-2020";
		
		try {
			
			//loads the driver to connect to the database
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection connection = DriverManager.getConnection(databaseURL, databaseUser, databasePass);
			System.out.print("Database connected");
					
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// Unknown method written by Simeon because he wants to get a job very soon. I hope he gets it
		public static void dbPushBooking(int ticketId, String flightId, String custUserName) {
			String databaseURL = "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
			String databaseUser = "root";
			String databasePass = "Westsmyrna-2020";
			
			try {
				
				//loads the driver to connect to the database
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection connection = DriverManager.getConnection(databaseURL, databaseUser, databasePass);
				System.out.print("Database connected");
				String sqlQuery = 
						"insert into confirmedbookings values(" + ticketId + ", " + flightId + ", " + custUserName + ")";
				PreparedStatement statement = connection.prepareStatement(sqlQuery);
				statement.executeUpdate();
				
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
		}
		
		// Method that checks who logs in the system: admin returns 2, customer returns 1
		public static int getLogin(Data data)  {
			
			String databaseURL = "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
			String databaseUser = "root";
			String databasePass = "Westsmyrna-2020";
			String search = data.getPerson().getUserName();
			String searchP = data.getPerson().getPassword();
			int count = 0;
				//loads the driver to connect to the database
				try {
					if (!search.equals("")||!searchP.equals("")) {
				Connection connection = DriverManager.getConnection(databaseURL, databaseUser, databasePass);
				String sqlQuery = "SELECT * FROM users WHERE username = '" + search+"'";
				
				PreparedStatement pStatement = connection.prepareStatement(sqlQuery);
				ResultSet rs = pStatement.executeQuery();
				
				
				while(rs.next()) {
					searchP=rs.getString(8);
					if( searchP.equalsIgnoreCase(data.getPerson().getPassword()) & search.equalsIgnoreCase("admin1") || search.equalsIgnoreCase("admin2") ){
				    count = count + 2;
					}
					else if(searchP.equalsIgnoreCase(data.getPerson().getPassword())) {
						count = count + 1;
					}
					else {
						count= 0;
					}
				}
				
				rs.close();
				pStatement.close();
				connection.close();
					}
				return count;
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
				return count;
		}

		
		
		// Call this method to display all available flights in the table view
		public ObservableList<Flight> getFlights() throws SQLException{
			String databaseURL = "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
			String databaseUser = "root";
			String databasePass = "Westsmyrna-2020";
			
			ObservableList<Flight> oblist = FXCollections.observableArrayList();
							
				Connection con = DriverManager.getConnection(databaseURL, databaseUser, databasePass);
				
				String querty = "SELECT flightId, departureCity, departureDate, departureTime, arrivalCity, basicPrice, availableSeats FROM flight";
				PreparedStatement pStatement = con.prepareStatement(querty);
			    ResultSet rs = pStatement.executeQuery();
			    
			    
			    
			    while(rs.next()) {
			    	oblist.add(new Flight(rs.getInt("flightId"), 
			    						  rs.getString("departureCity"),
			    						  rs.getString("departureDate"),
			    						  rs.getString("departureTime"), 
			    						  rs.getString("arrivalCity"),
			    						  rs.getString("basicPrice"), 
			    						  rs.getInt("availableSeats")));
			    	
			    
			    	
			    	System.out.println("reached get flights");
			    }
			    rs.close();
			    pStatement.close();
			    con.close();
			    
			    return oblist;
		}
		
		// adds resultset into ObservableList which later displayed in the table for booked flights
		public ObservableList<Bookings> getBookings() throws SQLException{
			String databaseURL = "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
			String databaseUser = "root";
			String databasePass = "Westsmyrna-2020";
			
			ObservableList<Bookings> oblist = FXCollections.observableArrayList();
							
				Connection con = DriverManager.getConnection(databaseURL, databaseUser, databasePass);
				
				String querty = "SELECT ticketNumber, flightId, username FROM confirmedbookings";
				PreparedStatement pStatement = con.prepareStatement(querty);
			    ResultSet rs = pStatement.executeQuery();
			    
			    
			    
			    while(rs.next()) {
			    	oblist.add(new Bookings (rs.getInt("ticketNumber"), 
			    						     rs.getInt("flightId"),
			    						    rs.getString("username")));
			    		
			    	
			    
			    	
			    	System.out.println("booked it");
			    }
			    
			    rs.close();
			    pStatement.close();
			    con.close();
			    
			    
			    return oblist;
		}
		
		// Returns searched flight after search button clicked, based on date, departure city and arrival city
		public static ObservableList<Flight> getSearch(Data data) throws SQLException{
			String databaseURL = "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
			String databaseUser = "root";
			String databasePass = "Westsmyrna-2020";
			String str = data.getFlight().getDepartureCity();
			String str1 = data.getFlight().getDepartureDate();
			String str2 = data.getFlight().getArrivalCity();
			ObservableList<Flight> oblist = FXCollections.observableArrayList();
							
				Connection con = DriverManager.getConnection(databaseURL, databaseUser, databasePass);
				
				String query = "SELECT * FROM flight WHERE departurecity=" + "'" + str + "'" + 
						 " AND departureDate= " + "'" + str1 + "'" +  " AND arrivalCity=" + "'" + str2 + "'" ;
				PreparedStatement pStatement = con.prepareStatement(query);
				
			    ResultSet rs = pStatement.executeQuery();
			    
			    
			    
			    while(rs.next()) {
			    	System.out.println("here");
			    	oblist.add(new Flight(rs.getInt("flightId"), 
			    						  rs.getString("departureCity"),
			    						  rs.getString("departureDate"),
			    						  rs.getString("departureTime"), 
			    						  rs.getString("arrivalCity"),
			    						  rs.getString("basicPrice"), 
			    						  rs.getInt("availableSeats")));
			    	
			    
			    	
			    	System.out.println("got search");
			    }
		    

			    rs.close();
			    pStatement.close();
			    con.close();
			    
			    return oblist;
		}
		
		// Adds user to database
           public static void addUser(Data data) throws SQLException {
			
			String databaseURL = "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
			String databaseUser = "root";
			String databasePass = "Westsmyrna-2020";
			
			
				//loads the driver to connect to the database
				
				Connection connection = DriverManager.getConnection(databaseURL, databaseUser, databasePass);
				String sqlQuery = "INSERT INTO users (username, firstname, lastname, address,zip, state, ssn, password, email, question, answer) VALUES(?, ?, ?, ?,?,?,?,?,?,?,?)";
				PreparedStatement statement = connection.prepareStatement(sqlQuery);
				statement.setString(1, data.getPerson().getUserName()); 
				statement.setString(2, data.getPerson().getFirstName()); 
				statement.setString(3, data.getPerson().getLastName());
				statement.setString(4, data.getPerson().getAddress());
				statement.setString(5, data.getPerson().getZipCode());
				statement.setString(6, data.getPerson().getState());
				statement.setString(7, data.getPerson().getSsn());
				statement.setString(8, data.getPerson().getPassword());
				statement.setString(9, data.getPerson().getEmail());
				statement.setString(10, data.getPerson().getSecurityQuestion());
				statement.setString(11, data.getPerson().getAnswer());
				
				statement.executeUpdate();
				connection.close();
				
		}

		

	
    public static void addBooking(Data data) throws Exception {
	
	String databaseURL = "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
	String databaseUser = "root";
	String databasePass = "Westsmyrna-2020";
	
	
		//loads the driver to connect to the database and set 
		
		Connection connection = DriverManager.getConnection(databaseURL, databaseUser, databasePass);
		String query = "SELECT username FROM confirmedbookings WHERE flightId = ?";
		PreparedStatement firstStatement = connection.prepareStatement(query);
		firstStatement.setInt(1, data.getFlight().getFlightId());
		ResultSet rs = firstStatement.executeQuery();
		String customerName = "";
		while(rs.next()) {
			customerName = rs.getString("username");
		}
		if(!customerName.equalsIgnoreCase(AirlineLogin.currentUser)) {
		
		String sqlQuery = "INSERT INTO confirmedbookings ( flightId, username) VALUES(?, ?)";
		String seatUpdate = "UPDATE flight SET availableSeats = ? WHERE flightId = ?";
		PreparedStatement statement = connection.prepareStatement(sqlQuery);
		
		statement.setInt(1, data.getFlight().getFlightId()); 
		statement.setString(2, AirlineLogin.currentUser);
		System.out.println("booking works");
		
		statement.executeUpdate();
		
		PreparedStatement pStatement = connection.prepareStatement(seatUpdate);
		pStatement.setInt(1, data.getFlight().getAvailableSeats() - 1);
		pStatement.setInt(2, data.getFlight().getFlightId());
		pStatement.executeUpdate();
		
		connection.close();
		}
		else {
			AlertBox.display("SORRY", "You can't book the same flight twice");
		}
    }	
	
    //Admin choice update flight info
		public static void updateFlight(Data data)  throws Exception {
			
			String databaseURL = "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
			String databaseUser = "root";
			String databasePass = "Westsmyrna-2020";
			
			Connection connection = DriverManager.getConnection(databaseURL, databaseUser, databasePass);
			String sqlQuery = "UPDATE flight SET departureCity = ? , departureDate = ?, departureTime = ?, "
					                     + " arrivalCity = ? , basicPrice = ? , availableSeats = ?  WHERE flightId = ?";
			
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, data.getFlight().getDepartureCity());
			statement.setString(2, data.getFlight().getDepartureDate());
			statement.setString(3, data.getFlight().getDepartureTime());
			statement.setString(4, data.getFlight().getArrivalCity());
			statement.setString(5, data.getFlight().getBasicPrice());
			statement.setInt(6, data.getFlight().getAvailableSeats());
			statement.setInt(7, data.getFlight().getFlightId());
			statement.executeUpdate();
			connection.close();
		}
		

    public static void deleteBooking(Data data) throws Exception {
	String databaseURL = "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
	String databaseUser = "root";
	String databasePass = "Westsmyrna-2020";
	
	
		//loads the driver to connect to the database
		
		Connection connection = DriverManager.getConnection(databaseURL, databaseUser, databasePass);
		String sqlQuery = " DELETE  FROM confirmedbookings WHERE ticketNumber = ? AND flightId = ? AND username = ?";
		String querty = "SELECT availableSeats FROM flight WHERE flightId = ?";
		String seatUpdate = "UPDATE flight SET availableSeats = ? WHERE flightId = ?";
		PreparedStatement statement = connection.prepareStatement(sqlQuery);
		statement.setInt(1, data.getBookings().getTicketNumber());
		statement.setInt(2, data.getBookings().getFlightId()); 
		
		statement.setString(3, AirlineLogin.currentUser);
		
		statement.executeUpdate();
		PreparedStatement st = connection.prepareStatement(querty);
		st.setInt(1, data.getBookings().getFlightId());
		ResultSet rs = st.executeQuery();
		int seats = 0;
		while(rs.next()) {
	    seats = rs.getInt("availableSeats");
		
		}
		PreparedStatement pStatement = connection.prepareStatement(seatUpdate);
		pStatement.setInt(1, seats + 1);
		pStatement.setInt(2, data.getBookings().getFlightId());
		pStatement.executeUpdate();
		
		
		connection.close();
}

    public static void deleteFlight(Data data) throws Exception {
    String databaseURL = "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
    String databaseUser = "root";
    String databasePass = "Westsmyrna-2020";
   
   
    //loads the driver to connect to the database
   
    Connection connection = DriverManager.getConnection(databaseURL, databaseUser, databasePass);
    String sqlQuery = " DELETE  FROM flight WHERE flightId = ? AND departureCity = ? AND departureDate = ? AND departureTime = ? AND "
                                           + "arrivalCity = ? AND basicPrice = ? AND availableSeats = ? ";
    PreparedStatement statement = connection.prepareStatement(sqlQuery);
    statement.setInt(1, data.getFlight().getFlightId());
    statement.setString(2, data.getFlight().getDepartureCity());
    statement.setString(3, data.getFlight().getDepartureDate());
    statement.setString(4, data.getFlight().getDepartureTime());
    statement.setString(5, data.getFlight().getArrivalCity());
    statement.setString(6, data.getFlight().getBasicPrice());
    statement.setInt(7, data.getFlight().getAvailableSeats());
    statement.executeUpdate();

    connection.close();
    }
    
    
    public static void addFlight(Data data) throws Exception {
    	String databaseURL = "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
    	String databaseUser = "root";
    	String databasePass = "Westsmyrna-2020";
    	//loads the driver to connect to the database
    	Connection connection = DriverManager.getConnection(databaseURL, databaseUser, databasePass);
    	String sqlQuery = "INSERT INTO flight (flightId, departureCity, departureDate, departureTime, arrivalCity, basicPrice, availableSeats) VALUES(?,?,?,?,?,?,?)";
    	PreparedStatement statement = connection.prepareStatement(sqlQuery);
    	statement.setInt(1, data.getFlight().getFlightId()); 
    	statement.setString(2, data.getFlight().getDepartureCity()); 
    	statement.setString(3, data.getFlight().getDepartureDate());
    	statement.setString(4, data.getFlight().getDepartureTime());
    	statement.setString(5, data.getFlight().getArrivalCity());
    	statement.setString(6, data.getFlight().getBasicPrice());
    	statement.setInt(7, data.getFlight().getAvailableSeats());
    	statement.executeUpdate();
    	connection.close();
}

    public static  String getSecurityQuestion(Data data) throws Exception {
	String databaseURL = "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
	String databaseUser = "root";
	String databasePass = "Westsmyrna-2020";
	
	Connection connection = DriverManager.getConnection(databaseURL, databaseUser, databasePass);
	
	String secQuest = "" ;
	
	String sqlQuery = "SELECT question FROM users WHERE username = ?";
	PreparedStatement pStatement = connection.prepareStatement(sqlQuery);
	pStatement.setString(1, data.getPerson().getUserName());
	ResultSet rs = pStatement.executeQuery();
	while (rs.next()) {
		secQuest = rs.getString("question");
		System.out.println(secQuest);
	}
	
	
	
	return secQuest;
}

    public static String getPassword(Data data) throws Exception {
	String databaseURL = "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
	String databaseUser = "root";
	String databasePass = "Westsmyrna-2020";
	
	Connection connection = DriverManager.getConnection(databaseURL, databaseUser, databasePass);
	
	String userPass = "";
	
	String sqlQuery = "SELECT password FROM users WHERE username = ?";
	PreparedStatement pStatement = connection.prepareStatement(sqlQuery);
	pStatement.setString(1, data.getPerson().getUserName());
	ResultSet rs = pStatement.executeQuery();
	while (rs.next()) {
		userPass = rs.getString("password");
		System.out.println(userPass);
	}
	
	
	
	return userPass;
}

}