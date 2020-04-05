package nl.inholland.university;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import nl.inholland.university.Model.Person;
import nl.inholland.university.Model.Student;

public class EditStudentReport {
	private Student student;
	private ArrayList<Person> userList;
	
	public EditStudentReport(ArrayList<Person> userList, Student student) {
		this.student = student;
		this.userList = userList;
	}
	
	public void editReport(int java, int cSharp, int php, int python, int retakes) {
		//Remove current student from list
		for (Person user : userList) {
			if (user.getId() == student.getId()) {
				userList.remove(user);
				break;
			}
		}
		
		//Add student to list with new (updated) reports
		student.getReport().setJava(java);
		student.getReport().setcSharp(cSharp);
		student.getReport().setPhp(php);
		student.getReport().setPython(python);
		student.getReport().setRetakes(retakes);
		
		userList.add(student);
		
		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setContentText("Succesfully edited the report for: " + student.getFirstName() +" "+student.getLastName());
		a.show();
	}

	public ArrayList<Person> getUserList() {
		return userList;
	}
}
