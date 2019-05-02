package gui;
import java.util.ArrayList;

import BusinessLogic.Data;
import BusinessLogic.ExceptionsHandlers;
import BusinessLogic.Person;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AirlineRegistration extends Application{
	 public static void main(String[] args) { 
		    launch(args);
 }
		  
@Override
	 public void start(Stage primaryStage) throws Exception {
	primaryStage.setTitle("7Clouds Registration");
	
	//* Create Grid layout
	GridPane registerWindow = new GridPane();
	registerWindow.setPadding(new Insets(10,10,10,5));
	registerWindow.setVgap(10);
	registerWindow.setHgap(8);
	
	// Set top text info
	LinearGradient gradient1 = new LinearGradient(0,0,0,1,true,CycleMethod.NO_CYCLE,
			new Stop(0.2, Color.AQUAMARINE), new Stop(0.8, Color.BLACK));
	Text text = new Text("Register to 7Clouds");
	text.setFont(new Font("Times New Roman", 30));
	text.setStroke(Color.DARKSLATEBLUE);
	text.setStrokeWidth(2);
	text.setFill(gradient1);
	GridPane.setConstraints(text, 1, 0);
	
	
	
	//* Create and set information nodes
	
	Label firstName = new Label("First Name *");	
	GridPane.setConstraints(firstName, 0, 1);
	Label lastName = new Label("Last Name *");
	GridPane.setConstraints(lastName, 0,2);
	Label address = new Label("Address *");
	GridPane.setConstraints(address,0,3);
	Label state = new Label("State *");
	GridPane.setConstraints(state,0,4);
	Label zip = new Label("Zip *");
	GridPane.setConstraints(zip,0,5);
	Label userName = new Label("Username *");
	GridPane.setConstraints(userName,0,6);
	Label password = new Label("Password *");
	GridPane.setConstraints(password,0,7);
	Label email = new Label("Email *");
	GridPane.setConstraints(email,0,8);
	Label SSN = new Label("SSN *");
	GridPane.setConstraints(SSN,0,9);
	Label secQuestion = new Label("Question *");
	GridPane.setConstraints(secQuestion, 0, 10);
	Label secAnswer = new Label("Answer *");
	GridPane.setConstraints(secAnswer, 0, 11);
	TextField txtFirstName = new TextField();
	GridPane.setConstraints(txtFirstName, 1, 1);
	TextField txtLastName = new TextField();
	GridPane.setConstraints(txtLastName, 1, 2);
	TextField txtAddress = new TextField();
	GridPane.setConstraints(txtAddress, 1, 3);
	TextField txtState = new TextField();
	GridPane.setConstraints(txtState, 1, 4);
	TextField txtZip = new TextField();
	GridPane.setConstraints(txtZip, 1, 5);
	TextField txtUserName = new TextField();
	GridPane.setConstraints(txtUserName, 1, 6);
	TextField txtPassword = new TextField();
	GridPane.setConstraints(txtPassword, 1, 7);
	TextField txtEmail = new TextField();
	GridPane.setConstraints(txtEmail, 1, 8);
	TextField txtSSN = new TextField();
	GridPane.setConstraints(txtSSN, 1, 9);
	
	// Create options for security questions
	
	ComboBox<String> question = new ComboBox<>();
	question.getItems().addAll("What is your name?", "What is your favorite color?");
	question.setPromptText("Choose your security question");
	question.setId("Security-question");
	GridPane.setConstraints(question, 1, 10);
	TextField txtAnswer = new TextField();
	GridPane.setConstraints(txtAnswer, 1, 11);
	Button completeButton = new Button("Complete");
	GridPane.setConstraints(completeButton, 1, 12);
	Button backButton = new Button("Back");
	
	GridPane.setConstraints(backButton, 1, 13);
	
	//* Set size for all nodes
	firstName.setMaxWidth(250);
	lastName.setMaxWidth(250);
	address.setMaxWidth(250);
	state.setMaxWidth(250);
	zip.setMaxWidth(250);
	userName.setMaxWidth(250);
	password.setMaxWidth(250);
	email.setMaxWidth(250);
	SSN.setMaxWidth(250);
	question.setMaxWidth(250);
	secAnswer.setMaxWidth(250);
	completeButton.setMaxWidth(250);
	backButton.setMaxWidth(250);
	txtFirstName.setMaxWidth(250);
	txtLastName.setMaxWidth(250);
	txtAddress.setMaxWidth(250);
	txtState.setMaxWidth(250); 
	txtZip.setMaxWidth(250); 
	txtUserName.setMaxWidth(250); 
	txtPassword.setMaxWidth(250); 
	txtEmail.setMaxWidth(250);
	txtSSN.setMaxWidth(250); 
	
	//Set text on the bottom explain what * means
	Text bottomText = new Text(" *REQUIRED. English characters only");
	bottomText.setFont(new Font("Itallic", 12));
	GridPane.setConstraints(bottomText, 1, 20);
	
	// Activate back button
	backButton.setOnAction(event -> {
		AirlineLogin window = new AirlineLogin();
		try {
			window.start(primaryStage);
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
	});
	
	completeButton.setOnAction( event -> {
		
		Person p = new Person( txtFirstName.getText(),txtLastName.getText(), txtAddress.getText(),txtZip.getText(),txtState.getText(),
				txtEmail.getText(),	txtSSN.getText(), question.getValue(), txtAnswer.getText(), txtUserName.getText(), txtPassword.getText());
		Data d = new Data();
		d.setPerson(p);
		ExceptionsHandlers.catchExceptions(d, "sign up"); 
			//register user into the database
			//notify the user of succesful account create with alertbox
			AlertBox.display("YOHOOO", "Registration is completed go back to main menu");
		
		
		
	});
	
	registerWindow.setAlignment(Pos.TOP_CENTER);
	registerWindow.getChildren().addAll(text, firstName,lastName,address, state, zip,
			userName, password,SSN, txtFirstName, txtLastName,txtState,txtZip,
			txtUserName, txtPassword, txtSSN, txtAddress, question,secQuestion,secAnswer, txtAnswer,
			completeButton, backButton,email, txtEmail, bottomText);
	
	Scene newScene = new Scene(registerWindow, 600,600);
	newScene.getStylesheets().add("background.css");
	primaryStage.setScene(newScene);
	primaryStage.show();
}

}
