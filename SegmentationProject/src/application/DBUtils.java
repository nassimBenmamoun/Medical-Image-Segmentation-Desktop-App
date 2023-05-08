package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DBUtils {
	
	private static final String USER = "system";
	private static final String PASS = "Your Password";
	
	public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
		
		Parent root = null;
		
		if(username != null) {
			try {
				FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
				root = loader.load();
				WelcomeController welcomeController = loader.getController();
				welcomeController.setUserInformation(username);
				
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {
			try {
				FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
				root = loader.load();
				
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(DBUtils.class.getResource("application.css").toExternalForm());
		stage.setTitle(title);
		//stage.setScene(new Scene(root));
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
	
	public static void signUpUser(ActionEvent event, String username, String password) {
		Connection con = null;
		PreparedStatement psInsert = null;
		PreparedStatement psCheckUserExists = null;
		ResultSet result = null;
		
		if(!username.trim().isEmpty() && !password.trim().isEmpty()) {
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",USER,PASS);
				String sqlCheck = "SELECT * FROM users WHERE username=?";
				psCheckUserExists = con.prepareStatement(sqlCheck);
				psCheckUserExists.setString(1, username);
				result = psCheckUserExists.executeQuery();
				
				if(result.isBeforeFirst()) { // true if resultSet is not empty
					System.out.println("User already exists");
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("You can't choose this username");
					alert.show();
					
				}else {
					String sqlInsert = "INSERT INTO users(username,password) VALUES(?,?)";
					psInsert = con.prepareStatement(sqlInsert);
					psInsert.setString(1,username);
					psInsert.setString(2,password);
					psInsert.executeUpdate();
					changeScene(event, "login.fxml", "Medical Image Segmentation", null);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if(result != null) {
					try {
						result.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(psCheckUserExists != null) {
					try {
						psCheckUserExists.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(psInsert != null) {
					try {
						psInsert.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}else {
			System.out.println("All fields are required");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("All fields are required");
			alert.show();
		}
		
		
	}
	
	public static void logInUser(ActionEvent event, String username, String password) {
		Connection con = null;
		PreparedStatement psCheck = null;
		ResultSet res = null;
		
		if(!username.isEmpty() && !password.isEmpty()) {
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",USER,PASS);
				String sqlCheck = "SELECT * FROM users WHERE username=? AND password=?";
				psCheck = con.prepareStatement(sqlCheck);
				psCheck.setString(1, username);
				psCheck.setString(2, password);
				res = psCheck.executeQuery();
				
				if(res.isBeforeFirst()) { // true if resultSet is not empty
					changeScene(event, "welcome.fxml", "Welcome", username);
					
				}else {
					System.out.println("Username or password incorrect");
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Username or password incorrect");
					alert.show();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if(res != null) {
					try {
						res.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(psCheck != null) {
					try {
						psCheck.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}else {
			System.out.println("All fields are required");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("All fields are required");
			alert.show();
		}
		
		
	}

}
