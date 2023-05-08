package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class QuitController implements Initializable{
	
	@FXML
	private Button go_to_welcome_Button;
	
	@FXML
	private Button go_to_login_Button;
	
	@FXML
	private Button quit_Button;

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		go_to_login_Button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				DBUtils.changeScene(event, "login.fxml", "Medical Image Segmentation", null);
				
			}
		});
		
		
		quit_Button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//DBUtils.changeScene(event, "Credit.fxml", "Credit", null);
				//Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
				//stage.close();
				try {
					root = FXMLLoader.load(getClass().getResource("Credit.fxml"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Credit");
				stage.show();
				PauseTransition delay = new PauseTransition(Duration.seconds(3));
				delay.setOnFinished( e -> stage.close() );
				delay.play();
			}
		});
		
		go_to_welcome_Button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				DBUtils.changeScene(event, "welcome.fxml", "Welcome", WelcomeController.user);
			}
		});
		
	}

}
