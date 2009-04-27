package model.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 2177626984202740302L;

	private String login;
	
	private String name;
	
	private String gender;
	
	private String email;
	
	public User() {
	}

	public User(String login, String name, String gender, String email) {
		this.login = login;
		this.name = name;
		this.gender = gender;
		this.email = email;
	}

	@Id
	@Column(name = "login")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString() {
		return "login: " + login + ", name: " + name + ", email: " + email + ", gender: " + gender;
	}
	
}
