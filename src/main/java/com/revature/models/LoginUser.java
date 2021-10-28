package com.revature.models;

import java.util.Objects;

public class LoginUser {
	private String username;
	private String password;// you might change this to a string and then hash later
	
	private String role;
	
	public LoginUser() {
		super();
	}
	public LoginUser(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}
	public String getPassword() {
		return this.password;
	}

	public String getUsername() {
		return username;
	}

	public String getRole() {
		return role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "LoginUser [username=" + username + ", password=" + password + ", role=" + role + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(password, role, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginUser other = (LoginUser) obj;
		return password == other.password && Objects.equals(role, other.role)
				&& Objects.equals(username, other.username);
	}


}
