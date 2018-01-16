package org.elsys.ip.servlet.service;

import java.util.ArrayList;
import java.util.List;

import org.elsys.ip.servlet.model.User;

public class UserService {
	private static List<User> users = new ArrayList<>();
	private static User loggedUser = null;
	public UserService() {
	}

	public List<User> getUsers() {
		return users;
	}

	public User getByName(String name) {
		if (name != null) {
			return users.stream().filter(u -> name.equals(u.getName())).findFirst().orElse(null);
		} else {
			return null;
		}
	}

	public static User getLoggedUser() {
		return loggedUser;
	}

	public static void setLoggedUser(User loggedUser) {
		UserService.loggedUser = loggedUser;
	}

	public static void add(User user) {
		users.add(user);
	}
}
