package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BusinessLogic.BusinessProccess;
import BusinessLogic.Data;
import BusinessLogic.ExceptionsHandlers;
import BusinessLogic.Person;
import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class AirlineLogin extends Application {
	//Take it out when strart from Splash
	public static String currentUser = "";
  
  public static void main(String[] args) { 
    launch(args);
  }
  
@Override
  public void start(Stage primaryStage) throws Exception{
	
	
	primaryStage.setTitle("7Clouds Online Reservation");
	
	//* Create top part of the login widow with text
	HBox topMenu = new HBox();
	LinearGradient gradient = new LinearGradient(0,0,0,1,true,CycleMethod.NO_CYCLE,
			new Stop(0.2, Color.AQUAMARINE), new Stop(0.8, Color.BLACK));
	Text text = new Text("Log In to 7Clouds");
	text.setFont(new Font("Times New Roman", 30));
	text.setStroke(Color.DARKSLATEBLUE);
	text.setStrokeWidth(2);
	text.setFill(gradient);
	topMenu.setAlignment(Pos.BASELINE_CENTER);
	topMenu.getChildren().add(text);
	
	
	
	
	//* Create center part of the login window with buttons and labels
	VBox centerMenu = new VBox();
	Label userName = new Label();
	userName.setMaxWidth(150);
	TextField txtName = new TextField();
	txtName.setPromptText("Enter username");
	txtName.setMaxWidth(150);
	Label password = new Label();
	password.setMaxWidth(150);
	TextField txtPassword = new TextField();
	txtPassword.setPromptText("Enter password");
	txtPassword.setMaxWidth(150);
	Button log_in = new Button("Log In");
	
	Button register = new Button("Register");
	Button recoverPass = new Button("Forgot Password");
	centerMenu.getChildren().addAll(userName, txtName, password, txtPassword,log_in,register,recoverPass);
	centerMenu.setMargin(log_in, new Insets(20));
	centerMenu.setMargin(recoverPass, new Insets(10));
	centerMenu.setMargin(userName, new Insets(10));
	
	// Set listener for register button
	register.setOnAction(e->{
		AirlineRegistration window = new AirlineRegistration();
		try {
			window.start(primaryStage);
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
	});
	
	// Set listener for log in button
	
	log_in.setOnAction(e -> {
		
		Person p = new Person(txtName.getText(), txtPassword.getText());
		currentUser = txtName.getText();
		Data d = new Data();
		d.setPerson(p);
		ExceptionsHandlers.catchExceptions(d, "log in"); 
		try {
			if(BusinessProccess.logIn(d) == 1) {
				CustomerChoice window = new CustomerChoice();
				try {
					window.start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			else if (BusinessProccess.logIn(d) == 2) {
				AdminChoice window = new AdminChoice();
				try {
					window.start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			else {
				AlertBox.display("WHOOPS", "Wrong username/password please try again");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	});

	//* Set one size for all nodes and adjust layout alignment
	log_in.setMaxWidth(150);
	register.setMaxWidth(150);
	recoverPass.setMaxWidth(150);
	
	centerMenu.setPadding(new Insets(20, 40, 0, 40));
	centerMenu.setAlignment(Pos.CENTER);
	
	//* Create bottom part of the login window
	StackPane bottomMenu = new StackPane();
    String str = "file:///C:/Users/Max/git/Repo1/TestRepo/src/gui/privateJet.jpg";
    Image image1 = new Image(str);
    ImageView iview1 = new ImageView();
	iview1.setImage(image1);
	iview1.setFitWidth(450);
	iview1.setFitHeight(390);
    
	bottomMenu.getChildren().add(iview1);
	
	
	//* Create layout pane to add all created panes into one
	BorderPane borderPane = new BorderPane();
	borderPane.setTop(topMenu);
	borderPane.setCenter(centerMenu);
	borderPane.setBottom(bottomMenu);
	
    

	Scene scene = new Scene(borderPane,450,550);
	scene.getStylesheets().add("background.css");
	primaryStage.setScene(scene);
	primaryStage.show();

	
  }



	   
   

  
}
