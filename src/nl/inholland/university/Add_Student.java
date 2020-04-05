package nl.inholland.university;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import nl.inholland.university.Model.Person;
import nl.inholland.university.Model.Report;
import nl.inholland.university.Model.Student;

public class Add_Student {
	
	private ArrayList<Person> userList;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private Integer age;
	private String group;
	
	public Add_Student(String username, String password, String firstName, String lastName, LocalDate birthDate, String group, ArrayList<Person> users) {
		setUserList(users);
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.group = group;
		this.age = calculateAge();
		addStudentToList();
	}

	private void addStudentToList() {
		// TODO Auto-generated method stub
		int id = getHighestIdNumber()+1;
		if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || birthDate == null || group.isEmpty()) {
			showMessage(AlertType.WARNING, "Please fill in all the fields!");
		}
		else if (birthDate.isAfter(LocalDate.now())) {
			showMessage(AlertType.WARNING, "You can't select a date in the furure!");
		}
		else {
			userList.add(new Student(id, username, password, firstName, lastName, birthDate, age, group, new Report(0,0,0,0,0)));
			showMessage(AlertType.CONFIRMATION, "Student succesfully added!");
		}
	}
	
	private Integer calculateAge() {
		LocalDate currentDate = LocalDate.now();
		return Period.between(birthDate, currentDate).getYears();
    }
	
	private Integer getHighestIdNumber() {
		int max = 0;
		for (Person user : userList) {
			if(user.getId() > max) {
				max = user.getId();
			}
		}
		return max;
	}
	
	private void showMessage(AlertType type, String message) {
		Alert a = new Alert(type);
		a.setContentText(message);
		a.show();
	}

	public ArrayList<Person> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<Person> userList) {
		this.userList = userList;
	}
	
	
}
