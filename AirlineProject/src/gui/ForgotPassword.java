package gui;

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

public class ForgotPassword {
	//public static String currentUser = "";
	static String str = "";
	public static void display(String title, String message) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setHeight(500);
		window.setMinWidth(300);
		
		Label label = new Label();
		label.setText(message);
		
		TextField txtName = new TextField();
		txtName.setMaxWidth(150);
		
		Button getSecQuest = new Button("Get Security Question");
		getSecQuest.setOnAction(e -> {
			Person p =  new Person(txtName.getText());
			Data data = new Data();
			data.setPerson(p);
			DatabaseConnector obj = new DatabaseConnector();
			try {
				str = obj.getSecurityQuestion(data);
				AlertBox.display("Your security question is:", str);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			
			
		});
		getSecQuest.setMaxWidth(150);
		
		Label submitSecAnswer = new Label();
		submitSecAnswer.setText("Enter answer for security question");
		TextField txtSecAnswer = new TextField();
		txtSecAnswer.setMaxWidth(150);
		
		
		Button submitButton = new Button("Submit");
		submitButton.setOnAction(e -> {
				Person c =  new Person(txtName.getText());
				DatabaseConnector obj = new DatabaseConnector();
				Data data = new Data();
				data.setPerson(c);
				try {
				AlertBox.display("Your password is:", 
						obj.getPassword(data));
				}
				catch (Exception ex) {
					System.out.println("Something went wrong");
					
				}
				});
		submitButton.setMaxWidth(150);
		
		Button closeButton = new Button("Close the Window");
		closeButton.setOnAction(e -> window.close());
		closeButton.setMaxWidth(150);
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, txtName, getSecQuest,submitSecAnswer, 
				txtSecAnswer, submitButton, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		scene.getStylesheets().add("background.css");
		window.setScene(scene);
		window.showAndWait();
		
	}
	
}