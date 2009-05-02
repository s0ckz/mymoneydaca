package mymoney.model.commitment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import mymoney.model.exceptions.MissingArgumentException;

/**
 * Classe que implementa um compromisso.
 * 
 * @author Danilo Resende
 * @author Leandro Jose
 * @author Rodrigo Bruno
 * 
 */
@Entity
@Table(name = "Commitment")
public class Commitment {

	/**
	 * O login do usuario
	 */
	private String login = "";
	/**
	 * Descricao do compromisso
	 */
	private String label = "";
	/**
	 * A data do compromisso
	 */
	private String data = "";
	/**
	 * O tipo do compromisso
	 */
	private String type = "";
	/**
	 * O valor atribuido ao compromisso
	 */
	private double amount;
	/**
	 * A frequencia do compromisso
	 */
	private String frequency;
	/**
	 * O codigo do compromisso
	 */
	private long code;

	/**
	 * 
	 * Construtor da classe
	 * 
	 * @param login
	 *            O login do usuario
	 * @param amount
	 *            O valor atribuido ao compromisso
	 * @param data
	 *            A data do compromisso
	 * @param frequency
	 *            A frequencia do compromisso
	 * @param label
	 *            Descricao do compromisso
	 * @param type
	 *            O tipo do compromisso
	 */
	public Commitment(String login, double amount, String data,
			String frequency, String label, String type) {
		this.amount = amount;
		this.data = data;
		this.frequency = frequency;
		this.label = label;
		this.type = type;
		this.login = login;
	}

	/**
	 * Construtor da classe
	 */
	public Commitment() {

	}

	/**
	 * @param code
	 *            o codigo a ser setado
	 */
	public void setCode(long code) {
		this.code = code;
	}

	/**
	 * @return o login
	 */
	@Column(name = "login")
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            o login a ser setado
	 */
	public void setLogin(String login) throws MissingArgumentException {
		if (login.isEmpty()) {
			throw new MissingArgumentException(
					"the user�s login of the commitment cannot be empty.");
		}
		this.login = login;
	}

	/**
	 * @return o label
	 */
	@Column(name = "label")
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            o label a ser setado
	 */
	public void setLabel(String label) throws MissingArgumentException {
		if (label.isEmpty()) {
			throw new MissingArgumentException(
					"the label of the commitment cannot be empty.");
		}
		this.label = label;
	}

	/**
	 * @return a data
	 */
	@Column(name = "data")
	public String getData() {
		return data;
	}

	/**
	 * @param data
	 *            a data a ser setada
	 * @throws MissingArgumentException
	 */
	public void setData(String data) throws MissingArgumentException {
		if (data.isEmpty()) {
			throw new MissingArgumentException(
					"the date of the commitment cannot be empty.");
		}
		this.data = data;
	}

	/**
	 * @return o type
	 */
	@Column(name = "type")
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            o tipo a ser setado
	 * @throws MissingArgumentException
	 */
	public void setType(String type) throws MissingArgumentException {
		if (type.isEmpty()) {
			throw new MissingArgumentException(
					"the type of the commitment cannot be empty.");
		}
		this.type = type;
	}

	/**
	 * @return o amount
	 */
	@Column(name = "amount")
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            o montante a ser setado
	 */
	public void setAmount(double amount) throws Exception {
		if (amount <= 0.0) {
			throw new MissingArgumentException(
					"the amount of the commitment cannot be negative or 0.0");
		}
		this.amount = amount;
	}

	/**
	 * @return a frequencia
	 */
	@Column(name = "frequency")
	public String getFrequency() {
		return frequency;
	}

	/**
	 * @param frequency
	 *            a frequencia a ser setada
	 * @throws MissingArgumentException
	 */
	public void setFrequency(String frequency) throws MissingArgumentException {
		if (data.isEmpty()) {
			throw new MissingArgumentException(
					"the frequency of the commitment cannot be empty.");
		}
		this.frequency = frequency;
	}

	/**
	 * @return o codigo
	 */
	@Id
	@GeneratedValue
	@Column(name = "code")
	public long getCode() {
		return code;
	}

}
