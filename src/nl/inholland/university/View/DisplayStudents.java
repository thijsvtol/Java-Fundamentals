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
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.inholland.university.Model.Person;
import nl.inholland.university.Model.Student;

public class DisplayStudents {

	private ArrayList<Person> userList;
	private ObservableList<Student> students = FXCollections.observableArrayList();
	private Person currentUser;

	public DisplayStudents(ArrayList<Person> userList, Person currentUser) {
		this.userList = userList;
		this.currentUser = currentUser;
		getStudents();
		setupScene();
	}

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
		window.setTitle("Students");
		window.setMinWidth(250);
		
		VBox layout = new VBox();
		layout.setPadding(new Insets(10, 10, 10, 10));
			
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
		
		
		Label lblTitle = new Label("Student List");
		Button btnGoBack = new Button("Go Back");
		layout.getChildren().addAll(lblTitle, tableView, btnGoBack);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.show();
		
		btnGoBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new MainWindow(userList, currentUser);
				window.close();
			}
		});
	}
}
