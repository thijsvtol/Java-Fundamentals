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
		
		Label pageTitle = new Label("Main panel");
		GridPane.setConstraints(pageTitle, 0, 0); // column row
		
		Button displayStudentsBtn = new Button("Display Students");
		GridPane.setConstraints(displayStudentsBtn, 0, 1);
		
		Button displayTeachersBtn = new Button("Display Teachers");
		GridPane.setConstraints(displayTeachersBtn, 1, 1);
		
		Button saveReportsBtn = new Button("Save Reports");
		GridPane.setConstraints(saveReportsBtn, 4, 1);
		
		Button addStudentsBtn = new Button("Add Students");
		GridPane.setConstraints(addStudentsBtn, 2, 1);
		
		Button displayReportsBtn = new Button("Display Reports");
		GridPane.setConstraints(displayReportsBtn, 3, 1);
		
		
		if (currentUser instanceof Student || currentUser instanceof Teacher || currentUser instanceof Manager) {
			// show options for a student
			gridPane.getChildren().addAll(pageTitle, displayStudentsBtn, displayTeachersBtn);
		}
		if (currentUser instanceof Teacher || currentUser instanceof Manager) {
			// show options for a teacher
			
			
			gridPane.getChildren().addAll(addStudentsBtn, displayReportsBtn);
		}
		if (currentUser instanceof Manager) {
			// show options for a manager
		}
		
		displayStudentsBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		
		Scene scene = new Scene(gridPane, 500, 400);
		window.setTitle("Main panel");
		window.setScene(scene);
		window.show();
	}
}
