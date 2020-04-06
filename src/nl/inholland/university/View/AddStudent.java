package nl.inholland.university.View;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nl.inholland.university.Add_Student;
import nl.inholland.university.Model.Person;

public class AddStudent {

	private ArrayList<Person> userList;
	private Person currentUser;

	public AddStudent(ArrayList<Person> userList, Person currentUser) {
		this.userList = userList;
		this.currentUser = currentUser;
		setupScene();
	}

	private void setupScene() {
		Stage window = new Stage();
		window.setTitle("Add student");
		window.setMinWidth(400);
		window.setMinHeight(420);
		
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(20);
		gridPane.setHgap(12);
		
		Label header = new Label("ADD STUDENT");
		GridPane.setConstraints(header, 0, 0);
		
		Label username = new Label("Username:");
		GridPane.setConstraints(username, 0, 1);
		
		TextField studentUsername = new TextField();
		GridPane.setConstraints(studentUsername, 1, 1);
		
		Label password = new Label("Password:");
		GridPane.setConstraints(password, 0, 2);
		
		PasswordField studentPassword = new PasswordField();
		GridPane.setConstraints(studentPassword, 1, 2);
		
		Label firstName = new Label("First name:");
		GridPane.setConstraints(firstName, 0, 3);
		
		TextField studentFirstName = new TextField();
		GridPane.setConstraints(studentFirstName, 1, 3);
		
		Label lastName = new Label("Last name:");
		GridPane.setConstraints(lastName, 0, 4);
		
		TextField studentLastName = new TextField();
		GridPane.setConstraints(studentLastName, 1, 4);
		
		Label birthDate = new Label("Birth date:");
		GridPane.setConstraints(birthDate, 0, 5);
		
		DatePicker studentBirthDate = new DatePicker();
		studentBirthDate.getEditor().setDisable(true);
		GridPane.setConstraints(studentBirthDate, 1, 5);
		
		Label group = new Label("Group:");
		GridPane.setConstraints(group, 0, 6);
		
		TextField studentGroup = new TextField();
		GridPane.setConstraints(studentGroup, 1, 6);
		
		Button addStudent = new Button("Add Student");
		GridPane.setConstraints(addStudent, 0, 7);
		
		Button goBack = new Button("Go Back");
		GridPane.setConstraints(goBack, 1, 7);
		
		gridPane.getChildren().addAll(header, username, studentUsername, password, studentPassword, firstName, studentFirstName, lastName, studentLastName, birthDate, studentBirthDate, group, studentGroup, addStudent, goBack);
		Scene scene = new Scene(gridPane, 400, 420);
		window.setTitle("Add Student");
		window.setScene(scene);
		window.show();
		
		//Go back to main panel
		goBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new MainWindow(userList, currentUser);
				window.close();
			}
		});
		
		//Insert user in array list
		addStudent.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Add_Student student = new Add_Student(studentUsername.getText(), studentPassword.getText(), studentFirstName.getText(), studentLastName.getText(), studentBirthDate.getValue(), studentGroup.getText(), userList);
				userList = student.getUserList();
			}
		});
	}
}

