package nl.inholland.university;

import nl.inholland.university.Model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import nl.inholland.university.View.Login;

public class App extends Application {
	
	ArrayList<Person> userList;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage window) throws Exception {
		//Get all users
		userList = getUserList();
		
		new Login(window, userList);
		
		window.setTitle("University Assignment");
		window.show();
	}
	
	private ArrayList<Person> getUserList() {
		ArrayList<Person> userList = new ArrayList<>();
		userList.add(new Manager(1, "test1@inholland.nl", "test1"));
		userList.add(new Teacher(2, "test2@inholland.nl", "test2", "Pietje", "Puk", LocalDate.of(1980, 5, 23), 39, 2500.0));
		userList.add(new Student(3, "test3@inholland.nl", "test3", "John", "Doe", LocalDate.of(1990, 9, 16), 29, "INF2Sb", new Report(79,55,88,91,0)));
		userList.add(new Student(4, "Thijs", "welkom", "Thijs", "van Tol", LocalDate.of(2001, 8, 25), 18, "INF2Sa", new Report(60,40,32,10,2)));
		return userList;
	}
}