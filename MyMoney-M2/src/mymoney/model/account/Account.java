package mymoney.model.account;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Accounts")
public class Account implements Serializable {
	
	private static final long serialVersionUID = 8807547932013814904L;

	private long id;
	
	private String login;
	
	private String label;
	
	private String agency;

	private Set<Operation> operations = new HashSet<Operation>();;

	private String account;

	public Account() {
	}
	
	public Account(String login, String label, String agency, String account) {
		this.login = login;
		this.label = label;
		this.agency = agency;
		this.account = account;
	}

	@Id    
	@GeneratedValue
	@Column(name = "id")
	public long getId() {
		return id;
	}

	@Column(name = "login")
	public String getLogin() {
		return login;
	}

	@Column(name = "label")
	public String getLabel() {
		return label;
	}

	@Column(name = "agency")
	public String getAgency() {
		return agency;
	}

	@Column(name = "account")
	public String getAccount() {
		return account;
	}

	@OneToMany(mappedBy="account", fetch=FetchType.EAGER)
	public Set<Operation> getOperations() {
		return operations;
	}
	
	public String toString() {
		return "id: " + getId() + ", login: " + getLogin() + ", agency: " + getAgency() + ", account: " + getAccount();
	}
	
	public void setId(long id) {
		this.id = id;
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

	public void setOperations(Set<Operation> operations) {
		this.operations = operations;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void addOperation(Operation operation) {
		operations.add(operation);
	}
	
}