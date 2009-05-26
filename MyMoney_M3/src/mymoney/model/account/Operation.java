package mymoney.model.account;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe que representa uma operacao bancaria.
 */
@Entity
@Table(name="Operations")
public class Operation implements Serializable {
	
	private static final long serialVersionUID = -907936723747353376L;

	private long id;
	private Account account;
	
	private String type;
	
	private String way;
	
	private double amount;

	private Date date;
	
	/**
	 * Construtor padrao usado pelo Hibernate.
	 */
	public Operation() {
		
	}
	
	/**
	 * Construtor de uma nova conta.
	 * @param account Conta que contempla essa operacao.
	 * @param type Tipo da operacao.
	 * @param way Modo de pagamento.
	 * @param amount Quantia de dinheiro.
	 * @param date Data da operacao.
	 * @see AccountManager#addOperation(String, long, String, String, double, Date)
	 */
	public Operation(Account account, String type, String way, double amount, Date date) {
		this.account = account;
		this.type = type;
		this.way = way;
		this.amount = amount;
		this.date = date;
	}

	/**
	 * Metodo de acesso ao identificador dessa operacao.
	 * @return Um numero maior do que zero.
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
	public long getId() {
		return id;
	}

	/**
	 * Metodo de alteracao do identificador dessa operacao. Deve ser utilizado somente pelo Hibernate.
	 * @param id Novo identificador.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Metodo de acesso ao tipo dessa operacao.
	 * @return O tipo da operacao.
	 * @see AccountManager#addOperation(String, long, String, String, double, Date)
	 */
	@Column(name = "type")
	public String getType() {
		return type;
	}

	/**
	 * Metodo de alteracao do tipo dessa operacao.
	 * @param type O novo tipo da operacao.
	 * @see AccountManager#addOperation(String, long, String, String, double, Date)
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Metodo de acesso a forma de pagamento.
	 * @return A nova forma de pagamento.
	 */
	@Column(name = "way")
	public String getWay() {
		return way;
	}

	/**
	 * Metodo de alteracao da forma de pagamento.
	 * @param way Nova forma de pagamento.
	 * @see AccountManager#addOperation(String, long, String, String, double, Date)
	 */
	public void setWay(String way) {
		this.way = way;
	}

	/**
	 * Metodo de acesso a quantia de dinheiro que essa operacao movimentou.
	 * @return Um numero maior do que zero.
	 */
	@Column(name = "amount")
	public double getAmount() {
		return amount;
	}

	/**
	 * Metodo de alteracao da quantia de dinheiro.
	 * @param amount A nova quantia.
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}


	/**
	 * Metodo de acesso a conta que contempla essa operacao.
	 * @return Uma conta.
	 */
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="account_fk")
	public Account getAccount() {
		return account;
	}

	/**
	 * Metodo de alteracao da conta que contempla essa operacao.
	 * @param account A nova conta.
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public String toString() {
		return "id: " + getId() + ", account: " + getAccount() + ", " + getType() + ", way: " + getWay() + ", amount: " + getAmount();
	}

	/**
	 * Metodo que retorna valores positivos caso a operacao seja de credito, e negativos
	 * se forem debito.
	 * @return Um numero real.
	 */
	public double correctAmount() {
		return type.equals("debit") ? -amount : amount;
	}

	/**
	 * Metodo de acesso a data da operacao.
	 * @return Data da operacao.
	 */
	@Column(name="date")
	public Date getDate() {
		return date;
	}

	/**
	 * Metodo de alteracao da data da operacao.
	 * @param date Data da operacao.
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
}
