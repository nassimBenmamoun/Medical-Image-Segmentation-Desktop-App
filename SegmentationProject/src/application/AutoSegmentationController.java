package application;

import java.awt.image.BufferedImage;
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

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class AutoSegmentationController implements Initializable{
	
	@FXML
	private ImageView imageToSegment;
	
	@FXML
	private Button grayscale_Button;
	
	@FXML
	private Button Segmentation_Button;
	
	@FXML
	private Button save_image_Button;
	
	@FXML
	private Button chooseOtherImage;
	
	@FXML
	private Button back;
	
	@FXML
	private Button go_to_manuel_seg_Button;
	
	@FXML
	private Button go;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
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
		
		grayscale_Button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				convertToGray();
			}
		});
		
		Segmentation_Button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				segmenter();
			}
		});
		
		save_image_Button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				FileChooser fls = new FileChooser();
				fls.setTitle("saved");
				fls.setInitialDirectory(new File("C:\\Users"));
				fls.getExtensionFilters().add(new ExtensionFilter("Image", ".png", ".jpg"));
				File slectedFile =fls.showSaveDialog(new Stage());
				if (slectedFile != null) {
					try {
						ImageIO.write(SwingFXUtils.fromFXImage(imageToSegment.getImage(),null), "png", slectedFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("saved");
				}else {
					System.out.println("error");
				}
				
			}
		});
		
		go_to_manuel_seg_Button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				Segmentation.changeScene2(event, "manuelSegmentation.fxml", "Manuel Segmentation", WelcomeController.imgPath);
				
			}
		});
		
		go.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				Segmentation.changeScene2(event, "manuelSegmentation.fxml", "Manuel Segmentation", WelcomeController.imgPath);
				
				
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
	    imageToSegment.setImage(res);
	    
	}
	
	public void segmenter() {
		
		String img = WelcomeController.imgPath;
	    System.loadLibrary( Core.NATIVE_LIBRARY_NAME ); 
	    Mat src = Imgcodecs.imread(img,Imgcodecs.IMREAD_GRAYSCALE);
	    if (src.empty()) {
	        System.out.println("Empty image: " + img);
	        System.exit(0);
	    }
	    Mat dst = new Mat();
	    Imgproc.threshold(src, dst, 157, 255, Imgproc.THRESH_TOZERO_INV); 
	    MatOfByte matOfByte = new MatOfByte();       
	    Imgcodecs.imencode(".jpg", dst, matOfByte); 
	    byte[] byteArray = matOfByte.toArray(); 
	    InputStream in = new ByteArrayInputStream(byteArray); 
	    Image res = new Image(in);
	    imageToSegment.setImage(res);
	}
	
	
	public void setImageToSegment(String path) {
		imageToSegment.setImage(new Image(path));
	}



}
