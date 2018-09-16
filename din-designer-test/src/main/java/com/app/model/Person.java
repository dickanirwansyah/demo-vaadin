package com.app.model;

public class Person {

	private String firstname;
	private String lastname;
	private String nickname;
	private String email;
	private String password;
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getNickname(){
		return nickname;
	}

	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
