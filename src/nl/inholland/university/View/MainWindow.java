package nl.inholland.university.View;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nl.inholland.university.Model.Manager;
import nl.inholland.university.Model.Person;
import nl.inholland.university.Model.Student;
import nl.inholland.university.Model.Teacher;

public class MainWindow {
	private ArrayList<Person> userList;
	private Person currentUser;

	public MainWindow(ArrayList<Person> userList, Person currentUser) {
		this.userList = userList;
		this.currentUser = currentUser;
		setupScene();
	}
	
	private void setupScene() {
		Stage window = new Stage();
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(10); // Vertical spacing between grid items
		gridPane.setHgap(8); // Horizontal spacing between grid items
		
		Label lblPageTitle = new Label("Welcome!");
		GridPane.setConstraints(lblPageTitle, 0, 0); // column row
		
		Button btnDisplayStudents = new Button("Display Students");
		GridPane.setConstraints(btnDisplayStudents, 0, 1);
		btnDisplayStudents.setMinSize(150, 60);
		
		Button btnDisplayTeachers = new Button("Display Teachers");
		GridPane.setConstraints(btnDisplayTeachers, 1, 1);
		btnDisplayTeachers.setMinSize(150, 60);
		
		Button btnAddStudent = new Button("Add Student");
		GridPane.setConstraints(btnAddStudent, 2, 1);
		btnAddStudent.setMinSize(150, 60);
		
		Button btnDisplayReports = new Button("Display/Edit Reports");
		GridPane.setConstraints(btnDisplayReports, 0, 2);
		btnDisplayReports.setMinSize(150, 60);
		
		Button btnEditStudent = new Button("Edit student");
		GridPane.setConstraints(btnEditStudent, 1, 2);
		btnEditStudent.setMinSize(150, 60);
		
		Button btnSaveReports = new Button("Save Reports");
		GridPane.setConstraints(btnSaveReports, 2, 2);
		btnSaveReports.setMinSize(150, 60);
		
		Scene scene = new Scene(gridPane, 500, 250);
		window.setTitle("Main panel");
		window.setScene(scene);
		window.show();
		
		// Decide user rights
		if (currentUser instanceof Student || currentUser instanceof Teacher || currentUser instanceof Manager) {
			// show options for a student
			gridPane.getChildren().addAll(lblPageTitle, btnDisplayStudents, btnDisplayTeachers);
		}
		if (currentUser instanceof Teacher || currentUser instanceof Manager) {
			// show options for a teacher
			gridPane.getChildren().addAll(btnAddStudent, btnDisplayReports, btnEditStudent);
		}
		if (currentUser instanceof Manager) {
			// show options for a manager
			gridPane.getChildren().addAll(btnSaveReports);
		}
		
		// Go to display students
		btnDisplayStudents.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new DisplayStudents(userList, currentUser);
				window.close();
			}
		});
		
		// Go to display teachers
		btnDisplayTeachers.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new DisplayTeachers(userList, currentUser);
				window.close();
			}
		});
		
		// Go to add student
		btnAddStudent.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new AddStudent(userList, currentUser);
				window.close();
			}
		});
		
		// Go to display reports of students
		btnDisplayReports.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new DisplayReports(userList, currentUser);
				window.close();
			}
		});
		
		// Go to export reports to txt file
		btnSaveReports.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new ExportReports(userList, currentUser);
				window.close();
			}
		});
		
		// Go to edit student
		btnEditStudent.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new EditStudent(userList, currentUser);
				window.close();
			}
		});
	}
}