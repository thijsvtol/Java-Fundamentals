package nl.inholland.university.View;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.inholland.university.Model.Person;
import nl.inholland.university.Model.Student;

public class DisplayReports {

	private ArrayList<Person> userList;
	private ObservableList<Student> students = FXCollections.observableArrayList();
	private Person currentUser;
	private Student selectedStudent;

	public DisplayReports(ArrayList<Person> userList, Person currentUser) {
		this.userList = userList;
		this.currentUser = currentUser;
		getStudents();
		setupScene();
	}

	// Get all students from user list
	private void getStudents() {
		// TODO Auto-generated method stub
		for(Person user : userList) {
			if(user instanceof Student) {
				students.add((Student) user);
			}	
		}
	}

	@SuppressWarnings("unchecked")
	private void setupScene() {
		// TODO Auto-generated method stub
		Stage window = new Stage();
		window.setTitle("Reports");
		window.setMinWidth(250);
		
		VBox layout = new VBox();
		layout.setPadding(new Insets(10, 10, 10, 10));
			
		TableView<Student> tableView = new TableView<>();
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		//Set columns
		TableColumn<Student, Integer> idColumn = new TableColumn<>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idColumn.setMinWidth(10);
		
		TableColumn<Student, String> firstNameColumn = new TableColumn<>("Firstname");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		firstNameColumn.setMinWidth(100);
		
		TableColumn<Student, String> lastNameColumn = new TableColumn<>("Lastname");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		lastNameColumn.setMinWidth(100);
		
		TableColumn<Student, LocalDate> birthDateColumn = new TableColumn<>("Birthdate");
		birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
		birthDateColumn.setMinWidth(100);
		
		TableColumn<Student, Integer> ageColumn = new TableColumn<>("Age");
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
		ageColumn.setMinWidth(100);
		
		TableColumn<Student, String> groupColumn = new TableColumn<>("Group");
		groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
		groupColumn.setMinWidth(100);
		
		TableColumn<Student, Number> cSharpColumn = new TableColumn<>("C#");
		cSharpColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getReport().getcSharp()));
		cSharpColumn.setMinWidth(100);
		
		TableColumn<Student, Number> javaColumn = new TableColumn<>("Java");
		javaColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getReport().getJava()));
		javaColumn.setMinWidth(100);
		
		TableColumn<Student, Number> phpColumn = new TableColumn<>("PHP");
		phpColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getReport().getPhp()));
		phpColumn.setMinWidth(100);
		
		TableColumn<Student, Number> pythonColumn = new TableColumn<>("Python");
		pythonColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getReport().getPython()));
		pythonColumn.setMinWidth(100);
		
		tableView.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn, birthDateColumn, ageColumn, groupColumn, cSharpColumn, javaColumn, phpColumn, pythonColumn);
		tableView.setItems(students);
		
		Label lblTitle = new Label("Student Reports List");
		Button btnGoBack = new Button("Go Back");
		Button btnEditSelectedStudent = new Button("Edit selected student");
		Button btnDetailView = new Button("View slected student report details");
		
		layout.getChildren().addAll(lblTitle, tableView, btnDetailView, btnEditSelectedStudent, btnGoBack);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.show();
		
		//Go back to main panel
		btnGoBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new MainWindow(userList, currentUser);
				window.close();
			}
		});
		
		// Edit selected student in table
		btnEditSelectedStudent.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//Check if a student is selected
				if (selectedStudent != null) {
					new EditAddStudentReport(userList, currentUser, selectedStudent);
					window.close();
				}
				else {
					showAlert(AlertType.WARNING,"Please select a student!");
				}
			}
		});
		
		// See detailed view of student his report
		btnDetailView.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//Check if student is selected
				if (selectedStudent != null) {
					new DetailedDisplayReport(userList, currentUser, selectedStudent);
					window.close();
				}
				else {
					showAlert(AlertType.WARNING,"Please select a student!");
				}
			}
		});
		
		// Set selected user
		tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		        //set selected student
		    	selectedStudent = tableView.getSelectionModel().getSelectedItem();
		    }
		});
	}
	
	private void showAlert(AlertType type, String message) {
		Alert a = new Alert(type);
		a.setContentText(message);
		a.show();
	}
}
