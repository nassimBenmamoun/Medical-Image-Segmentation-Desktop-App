package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class WelcomeController implements Initializable{
	
	@FXML
	private Button upload_image;
	
	@FXML
	private Text welcome_msg;
	
	@FXML
	private Button logout_button;
	
	public static String imgPath;
	
	public static String user;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		upload_image.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				WelcomeController.user = getUser();
				
				FileChooser flC = new FileChooser();
				//flC.getExtensionFilters().add(new ExtensionFilter("Image", "*.jpg"));
				File selectedF = flC.showOpenDialog(null);
				if (selectedF != null) {
					imgPath = selectedF.getAbsolutePath();
					Segmentation.changeScene(event, "autoSegmentation.fxml", "Auto Segmentation", imgPath);
				}else {
					System.out.println("error");
				}
				
				
				
			}
		});
		
		logout_button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				WelcomeController.user = getUser();
				DBUtils.changeScene(event, "quit.fxml", "Quit", null);
				
			}
		});
			
	}
	
	public void setUserInformation(String username) {
		welcome_msg.setText("Welcome Dr."+username);
	}
	
	public String getUser() {
		String s = welcome_msg.getText();
		String []arr = s.split("[.]");
		return arr[1];
	}
	

}
