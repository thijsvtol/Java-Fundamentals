package nl.inholland.university.View;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nl.inholland.university.LoginCheck;
import nl.inholland.university.Model.Person;

public class Login {
	
	private Stage window;
	private ArrayList<Person> userList;
	
	public Login(Stage window, ArrayList<Person> userList) {
		this.window = window;
		this.userList = userList;
		displayLoginScreen();
	}
	
	private void displayLoginScreen() {
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(10);
		gridPane.setHgap(8);
		
		Label username = new Label("Username:");
		GridPane.setConstraints(username, 0, 0);
		
		TextField userUsername = new TextField();
		GridPane.setConstraints(userUsername, 1, 0);
		
		Label password = new Label("Password:");
		GridPane.setConstraints(password, 0, 1);
		
		PasswordField userPassword = new PasswordField();
		GridPane.setConstraints(userPassword, 1, 1);
		
		Button btnLogin = new Button("Log in");
		GridPane.setConstraints(btnLogin, 1, 2);
		
		gridPane.getChildren().addAll(username, userUsername, password, userPassword, btnLogin);
		
		Scene scene = new Scene(gridPane, 250, 150);
		
		window.setScene(scene);
		
		btnLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LoginCheck loginCheck = new LoginCheck(userList);
				
				if(loginCheck.checkUserLogin(userUsername.getText(), userPassword.getText())) {
					//User can go to manage panel
					loginCheck.showMainWindow();
					window.close();
				}
				else {
					//User can try again
					Alert a = new Alert(AlertType.WARNING);
					a.setContentText("Wrong password/username");
					a.show();
				}
			}
		});
	}
}