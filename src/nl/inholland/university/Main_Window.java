package nl.inholland.university;
import java.util.ArrayList;

import nl.inholland.university.Model.*;
import nl.inholland.university.View.MainWindow;;

public class Main_Window {
	
	ArrayList<Person> userList;
	private Person currentUser;

	public void showMainWindow() {
		new MainWindow(userList, currentUser);
	}
	
	public Boolean checkUserLogin(String username, String password) {
		for (Person user : userList) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				this.setCurrentUser(user);
				return true;
			}
		}
		return false;
	}

	public void setUserList(ArrayList<Person> userList2) {
		// TODO Auto-generated method stub
		this.userList = userList2;
	}

	public Person getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Person currentUser) {
		this.currentUser = currentUser;
	}
}
