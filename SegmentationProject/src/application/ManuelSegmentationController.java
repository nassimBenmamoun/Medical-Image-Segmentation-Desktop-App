package application;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ManuelSegmentationController implements Initializable{
	
	@FXML
	private ImageView image_To_Segment;
	
	@FXML
	private Button gray_Button;
	
	@FXML
	private Slider slide;
	
	@FXML
	private Text text_value;
	
	@FXML
	private Button save_Button;
	
	@FXML
	private Button chooseOtherImage;
	
	@FXML
	private Button back;
	
	@FXML
	private Button goToAutoSeg;
	
	@FXML
	private Button go;
	
	int treshold_value;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		gray_Button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				convertToGray();
			}
		});
		
		save_Button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				FileChooser fls = new FileChooser();
				fls.setTitle("saved");
				fls.setInitialDirectory(new File("C:\\Users"));
				fls.getExtensionFilters().add(new ExtensionFilter("Image", ".png", ".jpg"));
				File slectedFile =fls.showSaveDialog(new Stage());
				if (slectedFile != null) {
					try {
						ImageIO.write(SwingFXUtils.fromFXImage(image_To_Segment.getImage(),null), "png", slectedFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("saved");
				}else {
					System.out.println("error");
				}
				
			}
		});
		
		chooseOtherImage.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				DBUtils.changeScene(event, "welcome.fxml", "Welcome", WelcomeController.user);
			}
		});
		
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				DBUtils.changeScene(event, "welcome.fxml", "Welcome", WelcomeController.user);
			}
		});
		
		goToAutoSeg.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				Segmentation.changeScene(event, "autoSegmentation.fxml", "Auto Segmentation", WelcomeController.imgPath);
				
			}
		});
		
		go.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				Segmentation.changeScene(event, "autoSegmentation.fxml", "Auto Segmentation", WelcomeController.imgPath);
				
			}
		});
		
		slide.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				treshold_value=(int)slide.getValue();
				text_value.setText(Integer.toString(treshold_value));
				
				String img = WelcomeController.imgPath;
			    System.loadLibrary( Core.NATIVE_LIBRARY_NAME ); 
			    Mat src = Imgcodecs.imread(img,Imgcodecs.IMREAD_GRAYSCALE);
			    if (src.empty()) {
			        System.out.println("Empty image: " + img);
			        System.exit(0);
			    }
			    Mat dst = new Mat();
			    Imgproc.threshold(src, dst, treshold_value, 255, Imgproc.THRESH_TOZERO_INV); 
			    MatOfByte matOfByte = new MatOfByte();       
			    Imgcodecs.imencode(".jpg", dst, matOfByte); 
			    byte[] byteArray = matOfByte.toArray(); 
			    InputStream in = new ByteArrayInputStream(byteArray); 
			    Image res = new Image(in);
			    image_To_Segment.setImage(res);
			}
			
		});
		
	}
	
	
	public void convertToGray() {
		
		String path = WelcomeController.imgPath;
	    System.loadLibrary( Core.NATIVE_LIBRARY_NAME ); 
	    Mat src = Imgcodecs.imread(path);
	    if (src.empty()) {
	        System.out.println("Empty image: " + path);
	        System.exit(0);
	    }
	    Mat dst = new Mat();
	    Imgproc.cvtColor(src, dst, Imgproc.COLOR_RGB2GRAY);
	    MatOfByte matOfByte = new MatOfByte();       
	    Imgcodecs.imencode(".jpg", dst, matOfByte); 
	    byte[] byteArray = matOfByte.toArray(); 
	    InputStream in = new ByteArrayInputStream(byteArray); 
	    Image res = new Image(in);
	    image_To_Segment.setImage(res);
	    
	}
	
	
	public void setImageSeg(String path) {
		image_To_Segment.setImage(new Image(path));
	}

}
