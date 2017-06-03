package com.mtit.assignment2.session;

import com.mtit.assignment2.models.User;

public class CurrentLogin {
	protected static User logged = null;

	public CurrentLogin() {
	}

	public static void createSession(User user) {
		logged = user;
	}

	public static User getSession() {
		return logged;
	}

	public static boolean checkSession() {
		if (logged != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isAdmin() {
		if (logged.getType().equals("admin")) {
			return true;
		} else {
			return false;
		}
	}

	public static void logout() {
		logged = null;
	}
}
