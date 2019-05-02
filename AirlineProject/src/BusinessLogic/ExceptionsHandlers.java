package BusinessLogic;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import gui.AlertBox;

public class ExceptionsHandlers {
	
	
	
	public static void catchExceptions(Data data, String msg) {
		
		try {
			BusinessProccess.processObject(data, msg);
		} catch (SQLException e) {
			
			
			if (e.getMessage().startsWith("Duplicate")) {
			AlertBox.display("Chosen username already exsist", "Please choose another username");
				
			}
			else {
				AlertBox.display("Registration confirmation", "Please fill out all fields");
			}
			
		}
		catch (Exception e){
			
		}
	}

}
