package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
 
 
 
public class DynamicTable extends Application {

    //TABLE VIEW AND DATA
    private ObservableList<ObservableList> data;

    private TableView tableview;

    //MAIN EXECUTOR
    public static void main(String[] args) {
        launch(args);
    }

    //CONNECTION DATABASE
    public void buildData() {
    	
        
        data = FXCollections.observableArrayList();
        try {
        	String databaseURL = "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
			String databaseUser = "root";
			String databasePass = "Westsmyrna-2020";
			Connection con = DriverManager.getConnection(databaseURL, databaseUser, databasePass);
            //SQL FOR SELECTING ALL data From table
            String SQL = "SELECT * from confirmedbookings";
            //ResultSet
            ResultSet rs = con.createStatement().executeQuery(SQL);

           
             // TABLE COLUMN ADDED DYNAMICALLY 
           
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            
             // Data added to ObservableList 
            
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        //TableView
        tableview = new TableView();
        buildData();

        //Main Scene
        Scene scene = new Scene(tableview);

        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        
        stage.show();
    }
    public static TableView getTable() {
    	TableView table = new TableView();
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
    	 try {
         	String databaseURL = "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
 			String databaseUser = "root";
 			String databasePass = "Westsmyrna-2020";
 			Connection con = DriverManager.getConnection(databaseURL, databaseUser, databasePass);
             //SQL FOR SELECTING ALL data From confirmedbookings table
             String SQL = "SELECT * from confirmedbookings";
             //ResultSet
             ResultSet rs = con.createStatement().executeQuery(SQL);

            
              // TABLE COLUMN ADDED DYNAMICALLY 
            
             for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                 //We are using non property style for making dynamic table
                 final int j = i;
                 TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                 col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                     public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                         return new SimpleStringProperty(param.getValue().get(j).toString());
                     }
                 });

                 table.getColumns().addAll(col);
                 System.out.println("Column [" + i + "] ");
             }

             
              // Data added to ObservableList 
             
             while (rs.next()) {
                 //Iterate Row
                 ObservableList<String> row = FXCollections.observableArrayList();
                 for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                     //Iterate Column
                     row.add(rs.getString(i));
                 }
                 System.out.println("Row [1] added " + row);
                 data.add(row);

             }
             rs.close();
			    
			    con.close();

            
         } catch (Exception e) {
             e.printStackTrace();
             System.out.println("Error on Building Data");
         }
    	 return table;
     }
    	
    	
    
}
