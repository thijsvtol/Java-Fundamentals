package nl.inholland.university;

import java.time.LocalDate;

public class Student extends Person {
	
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private Integer age;
	private String group;
	private Report report;
	
	public Student(int id, String username,  String password, String firstName, String lastName, LocalDate birthDate, int age, String group, Report report) {
		super(id, username, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.group = group;
		this.report = report;
	}

	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public LocalDate getBirthDate() {
		return this.birthDate;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getGroup() {
		return this.group;
	}
	
	public Report getReport() {
		return this.report;
	}
}