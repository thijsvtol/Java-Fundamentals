package nl.inholland.university.View;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import nl.inholland.university.EditStudentReport;
import nl.inholland.university.Model.Person;
import nl.inholland.university.Model.Student;

public class EditAddStudentReport {
	private Student student;
	private ArrayList<Person> userList;
	private Person currentUser;
	
	public EditAddStudentReport(ArrayList<Person> userList, Person currentUser, Student student) {
		this.userList = userList;
		this.currentUser = currentUser;
		this.student = student;
		setupScene();
	}

	private void setupScene() {
		Stage window = new Stage();
		window.setTitle("Edit report of student");
		window.setMinWidth(400);
		window.setMinHeight(320);
		
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(20);
		gridPane.setHgap(12);
		
		Label header = new Label("Edit "+student.getFirstName()+" "+student.getLastName()+" report");
		GridPane.setConstraints(header, 0, 0);
		
		Label java = new Label("Java:");
		GridPane.setConstraints(java, 0, 1);
		
		TextField reportJava = new TextField();
		reportJava.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
		GridPane.setConstraints(reportJava, 1, 1);
		reportJava.setText(student.getReport().getJava().toString());
		
		Label cSharp = new Label("C#:");
		GridPane.setConstraints(cSharp, 0, 2);
		
		TextField reportCSharp = new TextField();
		reportCSharp.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
		GridPane.setConstraints(reportCSharp, 1, 2);
		reportCSharp.setText(student.getReport().getcSharp().toString());
		
		Label php = new Label("PHP:");
		GridPane.setConstraints(php, 0, 3);
		
		TextField reportPhp = new TextField();
		reportPhp.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
		GridPane.setConstraints(reportPhp, 1, 3);
		reportPhp.setText(student.getReport().getPhp().toString());
		
		Label python = new Label("Python:");
		GridPane.setConstraints(python, 0, 4);
		
		TextField reportPython = new TextField();
		reportPython.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
		GridPane.setConstraints(reportPython, 1, 4);
		reportPython.setText(student.getReport().getPython().toString());
		
		Label retakes = new Label("Retakes:");
		GridPane.setConstraints(retakes, 0, 5);
		
		TextField reportRetakes = new TextField();
		reportRetakes.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
		GridPane.setConstraints(reportRetakes, 1, 5);
		reportRetakes.setText(student.getReport().getRetakes().toString());
		
		Button save = new Button("Save");
		GridPane.setConstraints(save,0,6);
		
		Button goBack = new Button("Go Back");
		GridPane.setConstraints(goBack,1,6);
		
		gridPane.getChildren().addAll(header, java, cSharp, python, php, retakes, save, goBack, reportCSharp, reportJava, reportRetakes, reportPhp, reportPython);
		
		Scene scene = new Scene(gridPane, 400, 320);
		window.setTitle("Edit report");
		window.setScene(scene);
		window.show();
		
		//Go back to main panel
		goBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new DisplayReports(userList, currentUser);
				window.close();
			}
		});
		
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				EditStudentReport studentReport = new EditStudentReport(userList, student);
				studentReport.editReport(Integer.valueOf(reportJava.getText()), Integer.valueOf(reportCSharp.getText()), 
						Integer.valueOf(reportPhp.getText()), Integer.valueOf(reportPython.getText()), Integer.valueOf(reportRetakes.getText()));
				userList = studentReport.getUserList();
			}
		});
	}
}
