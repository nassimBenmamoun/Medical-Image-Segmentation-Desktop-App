package application;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.BreakIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.imageio.plugins.tiff.ExifGPSTagSet;
import javax.management.loading.PrivateClassLoader;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controllers implements Initializable{
	
	@FXML
	private AnchorPane anchorPane;
	
	private VBox vb;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FadeTransition f=new FadeTransition(Duration.seconds(3),anchorPane);
		f.setFromValue(0);
		f.setToValue(1);
		f.setCycleCount(1);
		f.play();
		f.setOnFinished(e->{
			try {
				vb = FXMLLoader.load(getClass().getResource("login.fxml"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Scene s= new Scene(vb);
			Stage st=new Stage();
			st.setScene(s);
			st.show();
			st.setTitle("Medical Image Segmentation");
			s.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Image icon = new Image("png.png");
			st.getIcons().add(icon);
			st.show();
			anchorPane.getScene().getWindow().hide();
			st.setOnCloseRequest(event ->{
				event.consume();
				logout(st);
				});
			
		});
	}
	
	private Scene scene;
	private Parent root;
	
	public void logout(Stage stage) {
		try {
			root = FXMLLoader.load(getClass().getResource("Credit.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Credit");
			stage.show();
			PauseTransition delay = new PauseTransition(Duration.seconds(3));
			delay.setOnFinished( e -> stage.close() );
			delay.play();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
