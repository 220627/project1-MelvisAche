package com.melvis.models;

public class LoginUsersDTO {
	private int usersID;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String author;
	private String resolver;
	private Roles role;
	private int role_id_fk;
	
	@Override
	public String toString() {
		return "LoginUsersDTO [usersID=" + usersID + ", username=" + username + ", password=" + password
				+ ", firstName=" + firstName + ", secondName=" + lastName + ", email=" + email + ", author=" + author
				+ ", resolver=" + resolver + ", role=" + role + ", role_id_fk=" + role_id_fk + "]";
	}

	private LoginUsersDTO(int usersID, String username, String password, String firstName, String lastName,
			String email, String author, String resolver, Roles role, int role_id_fk) {
		super();
		this.usersID = usersID;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.author = author;
		this.resolver = resolver;
		this.role = role;
		this.role_id_fk = role_id_fk;
	}

	private LoginUsersDTO(String username, String password, String firstName, String lastName, String email,
			String author, String resolver, Roles role, int role_id_fk) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.author = author;
		this.resolver = resolver;
		this.role = role;
		this.role_id_fk = role_id_fk;
	}

	public int getUsersID() {
		return usersID;
	}

	public void setUsersID(int usersID) {
		this.usersID = usersID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setSecondName(String secondName) {
		this.lastName = secondName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getResolver() {
		return resolver;
	}

	public void setResolver(String resolver) {
		this.resolver = resolver;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public int getRole_id_fk() {
		return role_id_fk;
	}

	public void setRole_id_fk(int role_id_fk) {
		this.role_id_fk = role_id_fk;
	}
	
	
	

}

