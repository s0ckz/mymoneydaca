/**
 * 
 */
package mymoney.model.report;

import java.util.Collection;

/**
 * 
 * Classe que implementa um Relatorio
 * 
 * @author Danilo Resende
 * @author Leandro Jose
 * @author Rodrigo Bruno
 * 
 */

public class Report {

	/**
	 * O login do usuario
	 */
	private String login = "";
	/**
	 * A data de inicio
	 */
	private String dateBegin = "";
	/**
	 * Data de fim
	 */
	private String dateEnd = "";
	/**
	 * Tipo de operacao
	 */
	private String operationType = "";
	/**
	 * id da conta
	 */
	private long idAccount;

	/**
	 * Codigo do relatorio
	 */
	private long reportCode;

	/**
	 * Operacoes
	 * 
	 */
	private Collection<Long> operacoes;

	/**
	 * Construtor da classe
	 * 
	 * @param dateBegin
	 *            A data de Inicio do relatorio
	 * @param dateEnd
	 *            A data de fim do relatorio
	 * @param idAccount
	 *            O id da conta
	 * @param login
	 *            O login do usuario
	 * @param operationType
	 *            O tipo de operacao
	 * @param operacoes
	 */
	public Report(String login, String dateBegin, String dateEnd,
			long idAccount, String operationType, Collection<Long> operacoes) {
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
		this.idAccount = idAccount;
		this.login = login;
		this.operationType = operationType;
		this.operacoes = operacoes;

	}

	/**
	 * @return the operacoes
	 */
	public Collection<Long> getOperacoes() {
		return operacoes;
	}

	/**
	 * @param operacoes
	 *            the operacoes to set
	 */
	public void setOperacoes(Collection<Long> operacoes) {
		this.operacoes = operacoes;
	}

	/**
	 * Construtor da classe
	 */
	public Report() {
	}

	/**
	 * @return o login
	 */

	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            o login a ser setado
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return o dateBegin
	 * 
	 */
	public String getDateBegin() {
		return dateBegin;
	}

	/**
	 * @param dateBegin
	 *            a data de inicio a ser setada
	 */
	public void setDateBegin(String dateBegin) {
		this.dateBegin = dateBegin;
	}

	/**
	 * @return a dateEnd
	 */
	public String getDateEnd() {
		return dateEnd;
	}

	/**
	 * @param dateEnd
	 *            a data de fim a ser setada
	 */
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	/**
	 * @return o operationType
	 */
	public String getOperationType() {
		return operationType;
	}

	/**
	 * @param operationType
	 *            o tipo de operacao a ser setado
	 */
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	/**
	 * @return o idAccount
	 */
	public long getIdAccount() {
		return idAccount;
	}

	/**
	 * @param idAccount
	 *            o id da conta a ser setado
	 */
	public void setIdAccount(long idAccount) {
		this.idAccount = idAccount;
	}

	/**
	 * @param reportCode
	 *            o codigo do relatorio a ser setado
	 */
	public void setReportCode(long reportCode) {
		this.reportCode = reportCode;
	}

	/**
	 * @return o reportCode
	 */
	public long getReportCode() {
		return reportCode;
	}

	public String toString() {
		String description = " operations: ";
		for (Long code : getOperacoes()) {
			description += code + " ";
		}
		return "Login: " + getLogin() + " Code: " + getReportCode()
				+ " date begin: " + getDateBegin() + " date end: "
				+ getDateEnd() + " account id: " + getIdAccount() + description;

	}

}
