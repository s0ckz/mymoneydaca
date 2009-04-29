package mymoney.model.auth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="Auth")
public class Auth implements Serializable {
	
	private static final long serialVersionUID = 8449558455878716745L;

	private String login;
	
	private String password;
	
	private boolean isLoggedIn;
	
	public Auth() {
	}
	
	public Auth(String login, String password, boolean isLoggedIn) {
		this.login = login;
		this.password = password;
	}

	@Id
	@ForeignKey(name = "login", inverseName="login")
	@Column(name = "login")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "isLoggedIn")
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

}
