package application;
	
import java.io.IOException;

import javax.print.DocFlavor.URL;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("loading.fxml"));
			//BorderPane root = new BorderPane();
			Scene scene = new Scene(root);
			//css
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//or//scene.getStylesheets().add(var_string_path);
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			//primaryStage.setTitle("Medical Image Segmentation");
			Image icon = new Image("png.png");
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(icon);
			primaryStage.show();			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
