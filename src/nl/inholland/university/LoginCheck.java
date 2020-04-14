package nl.inholland.university;

import java.util.ArrayList;

import nl.inholland.university.Model.*;
import nl.inholland.university.View.MainWindow;;

public class LoginCheck {
	
	private ArrayList<Person> userList;
	private Person currentUser;
	
	public LoginCheck(ArrayList<Person> userList) {
		this.userList = userList;
	}
	
	// Check filled in password is the same as the password in the list
	public Boolean checkUserLogin(String username, String password) {
		for (Person user : userList) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				setCurrentUser(user);
				return true;
			}
		}
		return false;
	}
	
	// Go to main window if user is logged in
	public void showMainWindow() {
		new MainWindow(userList, currentUser);
	}

	public Person getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Person currentUser) {
		this.currentUser = currentUser;
	}
}