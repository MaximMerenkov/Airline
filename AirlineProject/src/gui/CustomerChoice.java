package gui;
import java.awt.Window;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import BusinessLogic.Bookings;
import BusinessLogic.BusinessProccess;
import BusinessLogic.Data;
import BusinessLogic.ExceptionsHandlers;
import BusinessLogic.Flight;
import BusinessLogic.Person;
import Database.DatabaseConnector;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
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

public class CustomerChoice extends Application {
	  
	    
	    
	    static ObservableList<Flight> oblist = FXCollections.observableArrayList();
	    
	    public static void main(String[] args) {
	        launch(args);
	    }

	    @Override
	    public void start(Stage primaryStage) throws Exception {
	        
	        primaryStage.setTitle("Book flight table");
	        
	        
	        TableView table1 = new TableView();
	        
	        TableColumn<Flight, Integer> idColumn = new TableColumn<>("Flight Id");
	        idColumn.setMinWidth(50);
	        idColumn.setCellValueFactory(new PropertyValueFactory<>("flightId"));

	        
	        TableColumn<Flight, String> departureColumn = new TableColumn<>("Departure City");
	        departureColumn.setMinWidth(100);
	        departureColumn.setCellValueFactory(new PropertyValueFactory<>("departureCity"));

	        
	        TableColumn<Flight, String> dateColumn = new TableColumn<>("Departure Date");
	        dateColumn.setMinWidth(100);
	        dateColumn.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
	        
	        TableColumn<Flight, String> timeColumn = new TableColumn<>("Departing Time");
	        timeColumn.setMinWidth(100);
	        timeColumn.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
	        
	        TableColumn<Flight, String> arrivalColumn = new TableColumn<>("Arrival City");
	        arrivalColumn.setMinWidth(70);
	        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalCity"));
	        
	        TableColumn<Flight, String> priceColumn = new TableColumn<>("Basic Price");
	        priceColumn.setMinWidth(100);
	        priceColumn.setCellValueFactory(new PropertyValueFactory<>("basicPrice"));
	        
	        TableColumn<Flight, Integer> seatsColumn = new TableColumn<>("Available seats");
	        seatsColumn.setMinWidth(100);
	        seatsColumn.setCellValueFactory(new PropertyValueFactory<>("availableSeats"));
	        
	        //adding columns
	        table1.getColumns().add(idColumn);
	        table1.getColumns().add(departureColumn);
	        table1.getColumns().add(dateColumn);
	        table1.getColumns().add(timeColumn);
	        table1.getColumns().add(arrivalColumn);
	        table1.getColumns().add(priceColumn);
	        table1.getColumns().add(seatsColumn);
	       
	        DatabaseConnector DBobj = new DatabaseConnector();
	        ObservableList<Flight> OList =  DBobj.getFlights();
	      
	        table1.setItems(OList);
	        table1.setMaxWidth(650);
	        table1.setMaxHeight(200);
            

          // Create another table
            
	       
	        
	     
	        //TableView table3 = new TableView();
	        
	        ComboBox<String> depCity = new ComboBox<>();
	        depCity.setMaxWidth(100);
	    	depCity.getItems().addAll("Atlanta", "Boston", "Miami", "Sochi");
	    	depCity.setPromptText("From");
	    	
	    	Text findFlightText = new Text("Find your flight");
	    	LinearGradient gradient = new LinearGradient(0,0,0,1,true,CycleMethod.NO_CYCLE,
		    			new Stop(0.2, Color.ALICEBLUE), new Stop(0.8, Color.ALICEBLUE));
		    	Text allFlightsText = new Text("All currently available flights");
		    	allFlightsText.setFont(new Font("Times New Roman", 16));
		    	allFlightsText.setStroke(Color.CORNFLOWERBLUE);
		    	allFlightsText.setStrokeWidth(1);
		    	allFlightsText.setFill(gradient);
	    	
	    	
	    	ComboBox<String> arrCity = new ComboBox<>();
	    	arrCity.setMaxWidth(100);
	    	arrCity.getItems().addAll("Moscow", "Miami", "Boston", "Atlanta");
	    	arrCity.setPromptText("To");
	    	Label lbl = new Label();
	    	DatePicker dp = new DatePicker();
	    	dp.setMaxWidth(200);
	    	dp.setPromptText("Choose date");
	    	dp.setOnAction(e -> {
	    		LocalDate date = dp.getValue();
	    		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
	    	    String str = date.format(dateFormat);
	    		lbl.setText(date.toString());
	    		System.out.println(str);
	    		
	    	});
	    	Button searchButton = new Button("Search");
	    	searchButton.setMaxWidth(200);
	    	searchButton.setOnAction(e -> {
	    		if(lbl.getText().isEmpty() || depCity.getValue().isEmpty() || arrCity.getValue().isEmpty()) {
	    			AlertBox.display("WARNING", "Please select all required fields for search");
	    		}
	    		Flight flight = new Flight(depCity.getValue(), lbl.getText(), arrCity.getValue());
	    		System.out.println(lbl.getText());
	    		Data d = new Data();
	    		d.setFlight(flight);
	    		DatabaseConnector obj = new DatabaseConnector();
	    		
	    		try {
	    			 	               
	    			 table1.setItems(obj.getSearch(d));
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				    		
	    		
	    	});
	    	Button refresh = new Button("Refresh search");
	    	refresh.setOnAction(e -> {
	    		DatabaseConnector obj = new DatabaseConnector();
		        try {
					table1.setItems(obj.getFlights());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	});
	    	
	        Button bookButton = new Button("Book");
	        bookButton.setMaxWidth(200);
	        
	        bookButton.setOnAction(e -> {
	        	Flight selectedFlight = (Flight) table1.getSelectionModel().getSelectedItem();
	        	Data dat = new Data();
	    		dat.setFlight(selectedFlight);
	    		System.out.println(selectedFlight.getFlightId());
	    		
	    		DatabaseConnector obj = new DatabaseConnector();
	    		try {
					obj.addBooking(dat);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		
	    		
	        	
	        });
	                    
	        Button backButton = new Button("Back");
	        backButton.setMaxWidth(200);
	        backButton.setOnAction(event -> {
	    		AirlineLogin window = new AirlineLogin();
	    		try {
	    			window.start(primaryStage);
	    		} catch (Exception e1) {
	    			
	    			e1.printStackTrace();
	    		}
	    	});
	        
	        Button bookedButton = new Button("My bookings");
	        bookedButton.setMaxWidth(200);
	        bookedButton.setOnAction(e -> {
	        	BookedFlights bookedFlights = new BookedFlights();
	        	try {
					bookedFlights.start(primaryStage);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				};
	        });
	        
	        VBox hBox = new VBox();
	        hBox.setPadding(new Insets(10,10,10,10));
	        hBox.setSpacing(10);
	        hBox.setAlignment(Pos.BASELINE_RIGHT);
	        hBox.getChildren().addAll(findFlightText, depCity, arrCity, dp, searchButton, bookButton, bookedButton, backButton);
	                       
	        VBox centerBox = new VBox() ;
	        centerBox.setSpacing(10);
	        centerBox.setAlignment(Pos.CENTER);
	        centerBox.getChildren().addAll(allFlightsText, table1, refresh);
	        
	       /* NOT USED
	        * HBox bookedBox = new HBox();
	        bookedBox.setPadding(new Insets(10,10,10,10));
	        bookedBox.setSpacing(10);
	        bookedBox.setAlignment(Pos.TOP_CENTER);;
	       // bookedBox.getChildren().add(table2);
	       */ 
	        BorderPane borderPane = new BorderPane();
	    	borderPane.setLeft(hBox);
	    	borderPane.setCenter(centerBox);
	    	//borderPane.setTop(bookedBox);

	        Scene scene = new Scene(borderPane, 1100, 600);
	        scene.getStylesheets().add("background.css");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	    



}
