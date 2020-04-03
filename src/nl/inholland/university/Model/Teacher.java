package nl.inholland.university.Model;

import java.time.LocalDate;

public class Teacher extends Person {
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private Integer age;
	private Double salary;
	
	public Teacher(int id, String username,  String password, String firstName, String lastName, LocalDate birthDate, int age, double salary) {
		super(id, username, password);
		this.age = age;
		this.salary = salary;
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
	
	public double getSalary() {
		return this.salary;
	}
}
