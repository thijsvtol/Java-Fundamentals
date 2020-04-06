package nl.inholland.university;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import nl.inholland.university.Model.Person;
import nl.inholland.university.Model.Report;
import nl.inholland.university.Model.Student;

public class Export_Reports {
	
	private File path;
	private ArrayList<Person> userList;
	private String file;
	
	public Export_Reports(ArrayList<Person> userList, File path) {
		this.path = path;
		this.userList = userList;
		getAllStudents();
	}
	
	private void getAllStudents() {
		try {
			for (Person person : userList) {
				if (person instanceof Student) {
					exportStudentToFile((Student) person);
				}
			}
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setContentText("Files added succesfully to:\r"+file);
			a.show();
		} 
		catch (IOException e) {
			Alert a = new Alert(AlertType.WARNING);
			a.setContentText(e.toString());
			a.show();
		}
	}

	private void exportStudentToFile(Student student) throws IOException {
		file = path.getAbsolutePath()+"\\"+student.getId()+" "+student.getFirstName()+" "+student.getLastName()+".txt";
		
		BufferedWriter writer = new BufferedWriter(new FileWriter((file)));
		writer.write("Report of Student "+student.getFirstName()+" "+student.getLastName());writer.newLine();
		writer.write("");writer.newLine();
		writer.write("Student ID    ................     "+student.getId());writer.newLine();
		writer.write("First Name    ................     "+student.getFirstName());writer.newLine();
		writer.write("Last Name     ................     "+student.getLastName());writer.newLine();
		writer.write("Birthday      ................     "+student.getBirthDate());writer.newLine();
		writer.write("Age           ................     "+student.getAge());writer.newLine();
		writer.write("Group         ................     "+student.getGroup());writer.newLine();
		writer.write("");writer.newLine();
		writer.write("COURSES");writer.newLine();
		writer.write("");writer.newLine();
		writer.write("Java          ................     "+student.getReport().getJava());writer.newLine();
		writer.write("CSharp        ................     "+student.getReport().getcSharp());writer.newLine();
		writer.write("Python        ................     "+student.getReport().getPython());writer.newLine();
		writer.write("PHP           ................     "+student.getReport().getPhp());writer.newLine();
		writer.write("");writer.newLine();
		writer.write("RESULTS");writer.newLine();
		writer.write("");writer.newLine();
		writer.write("Result        ................     "+getResult(student));writer.newLine();
		writer.write("Retakes       ................     "+student.getReport().getRetakes());
		writer.flush();
		writer.close();
	}
	
	private String getResult(Student student) {
		int result = 0;
		int numberOfCourses = Report.class.getDeclaredFields().length-1;
		result = student.getReport().getcSharp() + student.getReport().getJava() + student.getReport().getPython() + student.getReport().getPhp();
		result = result / numberOfCourses;
		
		if (result >= 55)
			return "passed";
		else
			return "not passed";
	}
}