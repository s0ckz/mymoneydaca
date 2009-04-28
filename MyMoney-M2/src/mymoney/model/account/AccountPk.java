package mymoney.model.account;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AccountPk implements Serializable {

	private static final long serialVersionUID = 961837749011156891L;

	private String login;
	
	private String label;
	
	private String agency;
	
	private String account;

	public AccountPk() {
	}
	
	public AccountPk(String login, String label, String agency, String account) {
		this.login = login;
		this.label = label;
		this.agency = agency;
		this.account = account;
	}

	@Column(name = "login", length=30)
	public String getLogin() {
		return login;
	}

	@Column(name = "label", length=100)
	public String getLabel() {
		return label;
	}

	@Column(name = "agency", length=20)
	public String getAgency() {
		return agency;
	}

	@Column(name = "account", length=20)
	public String getAccount() {
		return account;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
}
