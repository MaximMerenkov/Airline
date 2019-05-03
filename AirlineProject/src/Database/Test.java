package Database;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import BusinessLogic.Data;
import BusinessLogic.Person;
import javafx.geometry.Pos;
import Database.DatabaseConnector;

public class Test {
	public static void main(String[] args) {
		
		String currentUser = "";
		
		Person p = new Person("sim1");
		currentUser = "sim1";
		Data d = new Data();
		d.setPerson(p);
		System.out.println(d.getPerson().getUserName());
		DatabaseConnector obj = new DatabaseConnector();
		try {
		String str = obj.getSecurityQuestion(currentUser);
		System.out.println(str);
		System.out.println("yo");
		}
		catch (Exception ex) {
			System.out.println("Something went wrong");
		}
	}
}
