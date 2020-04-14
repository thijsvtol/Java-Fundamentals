package nl.inholland.university.View;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.inholland.university.Edit_Student;
import nl.inholland.university.Model.Person;
import nl.inholland.university.Model.Student;

public class EditStudent {
	
	private ArrayList<Person> userList;
	private ObservableList<Student> students = FXCollections.observableArrayList();
	private Person currentUser;
	private Student selectedStudent;
	
	public EditStudent(ArrayList<Person> userList, Person currentUser) {
		this.userList = userList;
		this.currentUser = currentUser;
		getStudents();
		setupScene();
	}

	@SuppressWarnings("unchecked")
	private void setupScene() {
		Stage window = new Stage();
		window.setTitle("Edit student");
		window.setMinWidth(250);
		
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(10); // Vertical spacing between grid items
		gridPane.setHgap(8); // Horizontal spacing between grid items
		
		//Set columns
		TableView<Student> tableView = new TableView<>();
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
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
		
		tableView.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn, birthDateColumn, ageColumn, groupColumn);
		tableView.setItems(students);
		
		//Set fields aside the table for editing
		Label lblTitle = new Label("Select a student to edit");
		Button btnGoBack = new Button("Go Back");
		Label lblUsername = new Label("Username:");
		TextField tfUsername = new TextField();
		Label lblPassword = new Label("Password:");
		PasswordField pfPassword = new PasswordField();
		Label lblFirstName = new Label("First name:");
		TextField tfFirstName = new TextField();
		Label lblLastName = new Label("Last name:");
		TextField tfLastName = new TextField();
		Label lblBirthDate = new Label("Birth date:");
		DatePicker dpBirthDate = new DatePicker();
		dpBirthDate.getEditor().setDisable(true);
		Label lblGroup = new Label("Group:");
		TextField tfGroup = new TextField();
		Button btnEditStudent = new Button("Edit Student");
		
		VBox firstColumn = new VBox();
		firstColumn.getChildren().addAll(tableView, lblTitle, btnGoBack);
		GridPane.setConstraints(firstColumn, 0, 0);
		
		VBox secondColumn = new VBox();
		secondColumn.getChildren().addAll(lblUsername, tfUsername, lblPassword, pfPassword, lblFirstName, tfFirstName, lblLastName, tfLastName,
				lblBirthDate, dpBirthDate, lblGroup, tfGroup, btnEditStudent);
		GridPane.setConstraints(firstColumn, 1, 0);
		
		gridPane.getChildren().addAll(firstColumn, secondColumn);
		
		Scene scene = new Scene(gridPane);
		window.setScene(scene);
		window.show();
		
		//Go back to main window
		btnGoBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new MainWindow(userList, currentUser);
				window.close();
			}
		});
		
		// Fill text fields if student is selected
		tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	selectedStudent = tableView.getSelectionModel().getSelectedItem();
		    	tfUsername.setText(selectedStudent.getUsername());
		    	pfPassword.setText(selectedStudent.getPassword());
		    	tfFirstName.setText(selectedStudent.getFirstName());
		    	tfLastName.setText(selectedStudent.getLastName());
		    	dpBirthDate.setValue(selectedStudent.getBirthDate());
		    	tfGroup.setText(selectedStudent.getGroup());
		    }
		});
		
		// Edit selected student
		btnEditStudent.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Edit_Student student = new Edit_Student(userList, selectedStudent);
				student.editStudent(tfUsername.getText(), pfPassword.getText(), tfFirstName.getText(),
						tfLastName.getText(), dpBirthDate.getValue(), tfGroup.getText());
				userList = student.getUsers();
				getStudents();
				tableView.setItems(students);
				tableView.getSortOrder().add(idColumn);
			}
		});
	}

	// Get all students from user list
	private void getStudents() {
		students.clear();
		for(Person user : userList) {
			if(user instanceof Student) {
				students.add((Student) user);
			}	
		}
	}
}