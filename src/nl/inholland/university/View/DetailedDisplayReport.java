package nl.inholland.university.View;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.inholland.university.Model.Person;
import nl.inholland.university.Model.Student;

public class DetailedDisplayReport {
	
	private ArrayList<Person> userList;
	private Person currentUser;
	private Student student;

	public DetailedDisplayReport(ArrayList<Person> userList, Person currentUser, Student student) {
		this.userList = userList;
		this.currentUser = currentUser;
		this.student = student;
		setupScene();
	}
	
	private void setupScene() {
		Stage window = new Stage();
		window.setTitle("Detailed Report");
		
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(10);
		gridPane.setHgap(8);
		
		Text header = new Text("Report of student: "+student.getFirstName()+" "+student.getLastName());
		header.setFont(Font.font("Monospaced", 20));
		GridPane.setConstraints(header, 0, 0);
		
		Text detail = new Text(10, 30, student.toString());
		detail.setFont(Font.font("Monospaced", 20));
		GridPane.setConstraints(detail, 0, 1);
		
		Button btnGoBack = new Button("Go Back");
	    GridPane.setConstraints(btnGoBack, 0, 2);
		
	    gridPane.getChildren().addAll(header, detail, btnGoBack);
	    
		Scene scene = new Scene(gridPane, 500, 450);
		window.setScene(scene);
		window.show();
		
		btnGoBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new DisplayReports(userList, currentUser);
				window.close();
			}
		});
	}
}
