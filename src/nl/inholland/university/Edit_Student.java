package nl.inholland.university;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import nl.inholland.university.Model.Person;
import nl.inholland.university.Model.Student;

public class Edit_Student {
	
	private ArrayList<Person> userList;
	private Student selectedStudent;

	public Edit_Student(ArrayList<Person> userList, Student selectedStudent) {
		this.userList = userList;
		this.selectedStudent = selectedStudent;
	}

	public void editStudent(String username, String password, String firstName, String lastName, LocalDate birthDate, String group) {
		//Remove current student first before updating
		userList.remove(selectedStudent);
		
		//Check if all fields are not null and check birth date
		if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || birthDate == null || group.isEmpty()) {
			showMessage(AlertType.WARNING, "Please fill in all the fields!");
		}
		else if (birthDate.isAfter(LocalDate.now())) {
			showMessage(AlertType.WARNING, "You can't select a date in the furure!");
		}
		else {
			//Insert updated student
			userList.add(new Student(selectedStudent.getId(), username, password, firstName, lastName, birthDate, calculateAge(birthDate), group, selectedStudent.getReport()));
			showMessage(AlertType.CONFIRMATION, "Student succesfully added!");
		}
	}

	public ArrayList<Person> getUsers() {
		return userList;
	}
	
	private Integer calculateAge(LocalDate birthDate) {
		LocalDate currentDate = LocalDate.now();
		return Period.between(birthDate, currentDate).getYears();
    }
	
	private void showMessage(AlertType type, String message) {
		Alert a = new Alert(type);
		a.setContentText(message);
		a.show();
	}
}