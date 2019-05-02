package BusinessLogic;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import Database.DatabaseConnector;
import gui.AlertBox;
import javafx.collections.ObservableList;

public class BusinessProccess {

	public static void signUp(Data data) throws SQLException {
		DatabaseConnector.addUser(data);		
   }
	
	public static void bookFlight(Data data) {
		
	}
	
	public static int logIn(Data data) throws SQLException {
		int result = DatabaseConnector.getLogin(data);
		return result;
	}
	
	public static ObservableList<Flight> searchFlight(Data data) throws SQLException {
		 return DatabaseConnector.getSearch(data);
	}
	
    public static void processObject(Data data, String msg) throws SQLException {
	
	if(msg.equals("sign up")) {
		signUp(data);
	}
	else if(msg.equals("book flight")){
		
	}
	else if (msg.equals("search flight")) {
		searchFlight(data);
	}
	else if(msg.equals("log in")) {
		
	}
		
	
	
	
   }

}
