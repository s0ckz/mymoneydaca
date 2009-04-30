package mymoney.model.commitment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import mymoney.model.exceptions.MissingArgumentException;

@Entity
@Table(name = "Commitment")
public class Commitment {

	private String login = "";
	private String label = "";
	private String data = "";
	private String type = "";
	private double amount;
	private String frequency;
	private long code;

	/**
	 * @param login
	 * @param amount
	 * @param data
	 * @param frequency
	 * @param label
	 * @param type
	 */
	public Commitment(String login, double amount, String data,
			String frequency, String label, String type) {
		this.amount = amount;
		this.data = data;
		this.frequency = frequency;
		this.label = label;
		this.type = type;
	}

	/**
	 * @return the login
	 */
	@Column(name = "login")
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) throws MissingArgumentException {
		if (login.isEmpty()) {
			throw new MissingArgumentException(
					"the user´s login of the commitment cannot be empty.");
		}
		this.login = login;
	}

	/**
	 * @return the label
	 */
	@Column(name = "label")
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) throws MissingArgumentException {
		if (label.isEmpty()) {
			throw new MissingArgumentException(
					"the label of the commitment cannot be empty.");
		}
		this.label = label;
	}

	/**
	 * @return the data
	 */
	@Column(name="data")
	public String getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
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
	 * @return the type
	 */
	@Column(name="type")
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
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
	 * @return the amount
	 */
	@Column(name="amount")
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) throws Exception {
		if (amount <= 0.0) {
			throw new MissingArgumentException(
					"the amount of the commitment cannot be negative or 0.0");
		}
		this.amount = amount;
	}

	/**
	 * @return the frequency
	 */
	@Column(name="frequency")
	public String getFrequency() {
		return frequency;
	}

	/**
	 * @param frequency
	 *            the frequency to set
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
	 * @return the code
	 */
	@Id
	@GeneratedValue
	@Column(name = "code")
	public long getCode() {
		return code;
	}

	

}
