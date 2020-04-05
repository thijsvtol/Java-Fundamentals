package nl.inholland.university.View;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nl.inholland.university.Main_Window;
import nl.inholland.university.Model.Person;

public class Login {
	
	Scene scene;
	
	public void displayLoginScreen(Stage window, ArrayList<Person> userList) {
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
		
		Button button = new Button("Log in");
		GridPane.setConstraints(button, 1, 2);
		
		gridPane.getChildren().addAll(username, userUsername, password, userPassword, button);
		scene = new Scene(gridPane, 300, 200);
		window.setScene(scene);
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main_Window mainWindow = new Main_Window();
				mainWindow.setUserList(userList);
				if(mainWindow.checkUserLogin(userUsername.getText(), userPassword.getText())) {
					//User can go to manage panel
					mainWindow.showMainWindow();
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
