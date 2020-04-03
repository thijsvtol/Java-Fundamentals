package nl.inholland.university.Model;

public abstract class Person {
	
	private Integer id;
	private String username;
	private String password;
	
	public Person(int id, String username,  String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
}