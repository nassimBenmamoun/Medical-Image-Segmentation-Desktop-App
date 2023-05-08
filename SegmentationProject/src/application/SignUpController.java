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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SignUpController implements Initializable{
	
	@FXML
	private TextField user_textField;
	
	@FXML
	private TextField pass_textField;
	
	@FXML
	private Button signup_button;
	
	@FXML
	private Button fleche2;
	
	@FXML
	private Hyperlink go_to_login_Hyperlink;

	private Stage stage;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
		signup_button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String username = user_textField.getText();
				String password = pass_textField.getText();
				DBUtils.signUpUser(event, username, password);
			}
		});
		
		go_to_login_Hyperlink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				DBUtils.changeScene(event, "login.fxml", "Medical Image Segmentation", null);
			}
		});
		
		fleche2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				DBUtils.changeScene(event, "login.fxml", "Medical Image Segmentation", null);
				
			}
		});
	}


}
