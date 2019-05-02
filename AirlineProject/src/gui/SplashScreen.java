package gui;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashScreen extends Application  {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
	
		String imageForSplash = "file:///C:/Users/Max/git/Repo1/TestRepo/src/gui/7Clouds jet.jpg";
		Image image = new Image(imageForSplash);
		
		
		VBox pane = new VBox();
		pane.setPadding(new Insets(10,10,10,10));
		Scene scene = new Scene(pane, 600, 560);
		Text text = new Text("Loading please wait...");
		text.setFont( new Font("Times New Roman ", 14));
		ProgressIndicator pi = new ProgressIndicator();
		
		
		ImageView iview = new ImageView();
		iview.setImage(image);
		iview.setFitWidth(580);
		iview.setFitHeight(480);
		pane.getChildren().addAll(iview, text,pi);
		
		
		pane.setEffect(new DropShadow());
		
		FadeTransition fadeOut = new FadeTransition(Duration.seconds(6), pane);
		fadeOut.setFromValue(1);
		fadeOut.setToValue(0);
		fadeOut.setCycleCount(1);
		stage.setScene(scene);
		stage.setTitle("7Clouds Airline");
		stage.show();
		fadeOut.play();
		
		fadeOut.setOnFinished(event->{
			AirlineLogin menu = new AirlineLogin();
			try {
				menu.start(stage);
				
				
			}catch (Exception e) {
				e.printStackTrace();
				
			}
		});
		
	   
		
	}
	

}
