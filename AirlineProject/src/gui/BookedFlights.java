package gui;

import java.sql.SQLException;

import BusinessLogic.Bookings;
import BusinessLogic.Data;
import BusinessLogic.Flight;
import Database.DatabaseConnector;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BookedFlights extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	static ObservableList<Flight> oblist = FXCollections.observableArrayList();
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		 primaryStage.setTitle("Booked flights table");
		 
		 TableView table2 = new TableView();
         
         // table2 = DynamicTable.getTable();
          table2.setMaxWidth(200);
	        table2.setMaxHeight(250);
	        
	        TableColumn<Bookings, Integer> ticketColumn = new TableColumn<>("ticketNumber");
	        ticketColumn.setMinWidth(50);
	        ticketColumn.setCellValueFactory(new PropertyValueFactory<>("ticketNumber"));

	        
	        TableColumn<Bookings, String> flightIdColumn = new TableColumn<>("Flight Id");
	        flightIdColumn.setMinWidth(50);
	        flightIdColumn.setCellValueFactory(new PropertyValueFactory<>("flightId"));

	        
	        TableColumn<Bookings, String> userColumn = new TableColumn<>("Username");
	        userColumn.setMinWidth(50);
	        userColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
	        
	        // Add columns to table2
	        table2.getColumns().add(ticketColumn);
	        table2.getColumns().add(flightIdColumn);
	        table2.getColumns().add(userColumn);
	        
	        //Create database object and call method  to set ObservableList
	        DatabaseConnector DBobj1 = new DatabaseConnector();
	        ObservableList<Bookings> bookedList =  DBobj1.getBookings();
	        
	        //Put Olist1 in the tableview and set size for the table
	        
	        table2.setItems(bookedList);
	        table2.setMaxSize(300, 300);
	        
	        
	        LinearGradient gradient = new LinearGradient(0,0,0,1,true,CycleMethod.NO_CYCLE,
	    			new Stop(0.2, Color.AQUAMARINE), new Stop(0.8, Color.BLACK));
	    	Text text = new Text("Booked Flights");
	    	text.setFont(new Font("Italic", 20));
	    	text.setStroke(Color.DARKSLATEBLUE);
	    	text.setStrokeWidth(2);
	    	text.setFill(gradient);
	    	
	    	Button deleteBooked = new Button("Delete");
	    	deleteBooked.setMaxWidth(150);
	    	deleteBooked.setOnAction(e -> {
	    		Bookings bookedFlight = (Bookings) table2.getSelectionModel().getSelectedItem();
	        if(bookedFlight.getUsername().equalsIgnoreCase( AirlineLogin.currentUser) || AirlineLogin.currentUser.equalsIgnoreCase("admin1")) {
	    		table2.getItems().remove(bookedFlight);
	    		Data dat = new Data();
	    		dat.setBookings(bookedFlight);
	    		DatabaseConnector obj = new DatabaseConnector();
	    		try {
					obj.deleteBooking(dat);
				} catch ( Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	        else {
	        	AlertBox.display("WARNING", "You can cancel only your flight");
	        }
	    		
	    	});
	    	
	    	Button backButton = new Button("GO back");
	    	backButton.setMaxWidth(150);
	    	backButton.setOnAction(e  ->{
	    		CustomerChoice ch = new CustomerChoice();
	    		try {
					ch.start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	});
	        
	        VBox bookedBox = new VBox();
	        bookedBox.setPadding(new Insets(10,10,10,10));
	        bookedBox.setSpacing(10);
	        bookedBox.setAlignment(Pos.TOP_CENTER);;
	        bookedBox.getChildren().addAll(text, table2, deleteBooked, backButton);
	        
	        Scene scene = new Scene(bookedBox, 600, 600);
	        scene.getStylesheets().add("background.css");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	}
	
	

}
