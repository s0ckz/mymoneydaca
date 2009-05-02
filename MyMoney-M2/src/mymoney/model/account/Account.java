package mymoney.model.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe que representa uma conta. Cada usuario pode ter multiplas contas, e cada conta pode conter varias operacões.
 * @see Operation
 */
@Entity
@Table(name="Accounts")
public class Account implements Serializable {
	
	private static final long serialVersionUID = 8807547932013814904L;

	private long id;
	
	private String login;
	
	private String label;
	
	private String agency;

	private List<Operation> operations = new ArrayList<Operation>();

	private String account;

	/**
	 * Construtor padrao para ser utilizado pelo Hibernate.
	 */
	public Account() {
	}
	
	/**
	 * Construtor de uma nova conta.
	 * @param login Login do usuario que eh dono dessa conta.
	 * @param label Descricao da conta a ser criada.
	 * @param agency Agencia da conta a ser criada.
	 * @param account Numero da conta a ser criada.
	 */
	public Account(String login, String label, String agency, String account) {
		this.login = login;
		this.label = label;
		this.agency = agency;
		this.account = account;
	}

	/**
	 * Metodo de acesso ao identificador (auto-incrementavel) dessa conta.
	 * @return Um numero maior ou igual a um.
	 */
	@Id    
	@GeneratedValue
	@Column(name = "id")
	public long getId() {
		return id;
	}

	/**
	 * Metodo de acesso ao login do usuario que eh dono dessa conta.
	 * @return Uma cadeia de caracteres.
	 */	
	@Column(name = "login")
	public String getLogin() {
		return login;
	}

	/**
	 * Metodo de acesso a descricao dessa conta.
	 * @return Uma cadeia de caracteres.
	 */
	@Column(name = "label")
	public String getLabel() {
		return label;
	}

	/**
	 * Metodo de acesso a agencia dessa conta.
	 * @return Uma cadeia de caracteres.
	 */
	@Column(name = "agency")
	public String getAgency() {
		return agency;
	}

	/**
	 * Metodo de acesso ao numero dessa conta.
	 * @return Uma cadeia de caracteres.
	 */
	@Column(name = "account")
	public String getAccount() {
		return account;
	}

	/**
	 * Metodo de acesso as operacões que foram realizadas com essa conta.
	 * @return Uma lista de operacões, ordenadas pela ordem de insercao.
	 */
	@OneToMany(mappedBy="account", fetch=FetchType.EAGER)
	public List<Operation> getOperations() {
		return operations;
	}
	
	public String toString() {
		return "id: " + getId() + ", login: " + getLogin() + ", agency: " + getAgency() + ", account: " + getAccount();
	}
	
	/**
	 * Metodo de alteracao do id (auto-incrementavel) dessa conta. Deve ser utilizado somente pelo Hibernate.
	 * @param id Novo identificador para a conta.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Metodo de alteracao do login do usuario que eh dono dessa conta.
	 * @param login Novo login, que representara o novo dono dessa conta.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Metodo de alteracao da descricao dessa conta.
	 * @param label Nova descricao.
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Metodo de alteracao da agencia dessa conta.
	 * @param agency Nova agencia.
	 */
	public void setAgency(String agency) {
		this.agency = agency;
	}

	/**
	 * Metodo de alteracao da lista de operacões que essa conta contempla.
	 * @param operations Nova lista de operacões
	 */
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	/**
	 * Metodo de alteracao do numero dessa conta.
	 * @param account Novo numero da conta.
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * Metodo para adicionar uma nova operacao a essa conta.
	 * @param operation Nova operacao a ser criada.
	 */
	public void addOperation(Operation operation) {
		operations.add(operation);
	}
	
}
