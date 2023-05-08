package application;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Segmentation {
	
public static void changeScene(ActionEvent event, String fxmlFile, String title, String imgpath) {
		
		Parent root = null;
		
		if(imgpath != null) {
			try {
				FXMLLoader loader = new FXMLLoader(Segmentation.class.getResource(fxmlFile));
				root = loader.load();
				AutoSegmentationController autoSegmentationController = loader.getController();
				autoSegmentationController.setImageToSegment(imgpath);
				
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {
			try {
				FXMLLoader loader = new FXMLLoader(Segmentation.class.getResource(fxmlFile));
				root = loader.load();
				
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		Scene scene=new Scene(root);
		stage.setScene(scene);
		scene.getStylesheets().add(DBUtils.class.getResource("application.css").toExternalForm());
		stage.show();
		stage.setOnCloseRequest(e ->{
			e.consume();
			logout(stage);
			});
		
}



public static void changeScene2(ActionEvent event, String fxmlFile, String title, String imgpath) {
	
	Parent root = null;
	
	if(imgpath != null) {
		try {
			FXMLLoader loader = new FXMLLoader(Segmentation.class.getResource(fxmlFile));
			root = loader.load();
			ManuelSegmentationController manuelSegmentationController = loader.getController();
			manuelSegmentationController.setImageSeg(imgpath);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}else {
		try {
			FXMLLoader loader = new FXMLLoader(Segmentation.class.getResource(fxmlFile));
			root = loader.load();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	stage.setTitle(title);
	Scene scene = new Scene(root);
	scene.getStylesheets().add(Segmentation.class.getResource("application.css").toExternalForm());
	stage.setScene(scene);
	stage.show();
	stage.setOnCloseRequest(e ->{
		e.consume();
		logout(stage);
		});
}

public static void logout(Stage stage) {
	Scene scene;
	Parent root = null;
	try {
		root = FXMLLoader.load(DBUtils.class.getResource("Credit.fxml"));
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	scene = new Scene(root);
	stage.setScene(scene);
	stage.setTitle("Credit");
	stage.show();
	PauseTransition delay = new PauseTransition(Duration.seconds(3));
	delay.setOnFinished( e -> stage.close() );
	delay.play();
}




}
