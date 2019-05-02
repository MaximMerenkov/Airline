package gui;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import BusinessLogic.Bookings;
import BusinessLogic.Data;
import BusinessLogic.ExceptionsHandlers;
import BusinessLogic.Flight;
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

public class AdminChoice extends Application {
	 Stage window;
	    
	    //TextField flightIdInput,departingCityInput, depatingDateInput,departingTimeInput, arrivalCityInput,basicPriceInput, availableSeatsInput ;
	 static ObservableList<Flight> oblist = FXCollections.observableArrayList();
	    
	    public static void main(String[] args) {
	        launch(args);
	    }

	    @Override
	    public void start(Stage primaryStage) throws Exception {
	        
	        primaryStage.setTitle("Book flight table");
	        
	        // Crate table to to display data
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
	        
	       //Create DB object to call method to get info about all flights
	        DatabaseConnector DBobj = new DatabaseConnector();
	        ObservableList<Flight> OList =  DBobj.getFlights();
	      
	        table1.setItems(OList);
	        table1.setMaxWidth(650);
	        table1.setMaxHeight(200);
	        
            //Set info text
	        Text findFlightText = new Text("ADMIN TOOLS");
	    	LinearGradient gradient = new LinearGradient(0,0,0,1,true,CycleMethod.NO_CYCLE,
		    			new Stop(0.2, Color.ALICEBLUE), new Stop(0.8, Color.ALICEBLUE));
		    	Text allFlightsText = new Text("All currently available flights");
		    	allFlightsText.setFont(new Font("Times New Roman", 16));
		    	allFlightsText.setStroke(Color.CORNFLOWERBLUE);
		    	allFlightsText.setStrokeWidth(1);
		    	allFlightsText.setFill(gradient);
		    	GridPane.setConstraints(findFlightText, 1, 0);
	     
	        
	        //Create nodes for admin use
		    Label departCity = new Label("Departing City");
			GridPane.setConstraints(departCity, 0, 1);
	        ComboBox<String> depCity = new ComboBox<>();
	        depCity.setMaxWidth(100);
	    	depCity.getItems().addAll("Atlanta", "Boston", "Miami", "Sochi", "New York", "Rome");
	    	depCity.setPromptText("From");
	    	GridPane.setConstraints(depCity, 1, 1);
	    	
	    	
		    Label toCity = new Label("Arrival City");
		    GridPane.setConstraints(toCity, 0, 2);
	    	ComboBox<String> arrCity = new ComboBox<>();
	    	arrCity.setMaxWidth(100);
	    	arrCity.getItems().addAll("Moscow", "Miami", "Boston", "Atlanta", "Paris", "Dubai");
	    	arrCity.setPromptText("To");
	    	GridPane.setConstraints(arrCity, 1, 2);
	    	
	    	Label lbl = new Label();
	    	Label datePicker = new Label("Departing date");
	    	GridPane.setConstraints(datePicker, 0, 3);
	    	DatePicker dp = new DatePicker();
	    	dp.setMaxWidth(200);
	    	
	    	dp.setOnAction(e -> {
	    		LocalDate date = dp.getValue();
	    		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
	    	    String str = date.format(dateFormat);
	    		lbl.setText(date.toString());
	    		//System.out.println(str);
	    		
	    	});
	    	GridPane.setConstraints(dp, 1, 3);
	    	
	    	
	    	Label deparTime = new Label("Departing Time");
	    	GridPane.setConstraints(deparTime, 0, 4);
	    	ComboBox<String>  depTime = new ComboBox<>();
	    	depTime.setMaxWidth(100);
	    	depTime.getItems().addAll("1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00", "8:00", "9:00", 
                    "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00",
                    "19:00", "20:00", "21:00", "22:00", "23:00");
	    	depTime.setMaxWidth(70);
	    	GridPane.setConstraints(depTime, 1, 4);
	    	
	    	Label bP = new Label("Basic Price");
	    	GridPane.setConstraints(bP, 0, 5);
	    	TextField basePrice = new TextField();
	    	basePrice.setMaxWidth(100);
	    	GridPane.setConstraints(basePrice, 1, 5);
	    	
	    	Label seatLeft = new Label("Remaining seats");
	    	GridPane.setConstraints(seatLeft, 0, 6);
	    	TextField remSeats = new TextField();
	    	remSeats.setMaxWidth(100);
	    	GridPane.setConstraints(remSeats, 1, 6);
	    	
	    	Label flightIdLabel = new Label("Flight ID");
	    	GridPane.setConstraints(flightIdLabel, 0, 7);
	    	TextField flightIdTxt = new TextField();
	    	flightIdTxt.setMaxWidth(100);
	    	GridPane.setConstraints(flightIdTxt, 1, 7);
	    	
	    	Button searchButton = new Button("Search");
	    	searchButton.setMaxWidth(200);
	    	GridPane.setConstraints(searchButton, 1, 8);
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
	        GridPane.setConstraints(bookButton, 1, 9);
	        
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
	        
	        Button deleteButton = new Button("Delete Flight");
	        deleteButton.setMaxWidth(200);
	        GridPane.setConstraints(deleteButton, 1, 10);
	        deleteButton.setOnAction(e -> {
	        	Flight selectedFlight = (Flight) table1.getSelectionModel().getSelectedItem();
	        	Data dat = new Data();
	    		dat.setFlight(selectedFlight);
	    		
	    		DatabaseConnector obj = new DatabaseConnector();
	    		try {
					obj.deleteFlight(dat);
					table1.getItems().remove(selectedFlight);
				} catch (Exception e1) {
					AlertBox.display("OOOPS", "There is a problem please try again");
					e1.printStackTrace();
				}
	    		
	        });
	        
	        
	        
	        // Admin option to add new flight
	        Button addButton = new Button("Add Flight");
	        addButton.setMaxWidth(200);
	        GridPane.setConstraints(addButton, 1, 11);
	        addButton.setOnAction(e -> {
	        	Flight destination = new Flight(Integer.parseInt(flightIdTxt.getText()), depCity.getValue(), lbl.getText(),
	        			                           depTime.getValue(), arrCity.getValue(), basePrice.getText(),
	        			                           Integer.parseInt(remSeats.getText()));
	        	Data wrapper = new Data();
	        	wrapper.setFlight(destination);
	        	DatabaseConnector obj = new DatabaseConnector();
	        	try {
					obj.addFlight(wrapper);
				} catch (Exception e1) {
					AlertBox.display("WARNING", "Cant add flight");
					e1.printStackTrace();
				}
	        });
	        
	        Button updateButton = new Button("Update Flight");
	        updateButton.setMaxWidth(200);
	        GridPane.setConstraints(updateButton, 1, 12);
	                    
	        Button backButton = new Button("Main menu");
	        backButton.setMaxWidth(200);
	        
	        GridPane.setConstraints(backButton, 1, 13);
	        backButton.setOnAction(event -> {
	    		AirlineLogin window = new AirlineLogin();
	    		try {
	    			window.start(primaryStage);
	    		} catch (Exception e1) {
	    			
	    			e1.printStackTrace();
	    		}
	    	});
	        
	        Button bookedButton = new Button("Booked flights");
	        bookedButton.setMaxWidth(200);
	        GridPane.setConstraints(bookedButton, 1, 14);
	        bookedButton.setOnAction(e -> {
	        	BookedFlights bookedFlights = new BookedFlights();
	        	try {
					bookedFlights.start(primaryStage);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				};
	        });
	        
	        GridPane adminTools  = new GridPane();
	        adminTools.setPadding(new Insets(10,10,10,5));
	        adminTools.setVgap(8);
	        adminTools.setHgap(8);
	        adminTools.setAlignment(Pos.CENTER);
	        adminTools.getChildren().addAll(findFlightText ,departCity, depCity, toCity,arrCity, datePicker, dp,
	        		                        deparTime,depTime, bP,basePrice, seatLeft,remSeats,flightIdLabel,
	        		                        flightIdTxt, searchButton, bookButton, deleteButton, addButton, updateButton, bookedButton, backButton);
	        /* Not used
	        VBox adminTools = new VBox();
	        adminTools.setPadding(new Insets(10,10,10,10));
	        adminTools.setSpacing(10);
	        adminTools.setAlignment(Pos.BASELINE_RIGHT);
	        adminTools.getChildren().addAll(findFlightText ,depCity, arrCity, dp, searchButton, bookButton, bookedButton, backButton);
	         */ 
	        
	        // Create layout and set table to display all flights
	        VBox centerBox = new VBox() ;
	        centerBox.setSpacing(10);
	        centerBox.setAlignment(Pos.CENTER);
	        centerBox.getChildren().addAll(allFlightsText, table1, refresh);
	        
	      // Main layout pane
	        BorderPane borderPane = new BorderPane();
	    	borderPane.setLeft(adminTools);
	    	borderPane.setCenter(centerBox);
	    	//borderPane.setTop(bookedBox);

	        Scene scene = new Scene(borderPane, 1100, 600);
	        scene.getStylesheets().add("background.css");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	    



}
