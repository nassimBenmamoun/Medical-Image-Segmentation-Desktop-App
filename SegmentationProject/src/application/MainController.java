package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainController implements Initializable{
	
	@FXML
	private TextField username_textField;
	
	@FXML
	private PasswordField password_textField;
	
	@FXML
	private Button login_button;
	
	@FXML
	private Hyperlink go_to_signup_Hyperlink;
	
	@FXML
	private Button fleche;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		login_button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String username = username_textField.getText();
				String password = password_textField.getText();
				DBUtils.logInUser(event, username , password);
			}
		});
		
		go_to_signup_Hyperlink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				DBUtils.changeScene(event, "signup.fxml", "Sign Up", null);
			}
		});
		
		fleche.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				DBUtils.changeScene(event, "signup.fxml", "Sign Up", null);
			}
		});
	}

}
