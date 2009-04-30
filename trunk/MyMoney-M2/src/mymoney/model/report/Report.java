/**
 * 
 */
package mymoney.model.report;

/**
 * @author Rodrigo
 * 
 */
public class Report {

	private String login;
	private String dateBegin;
	private String dateEnd;
	private String operationType;
	private long idAccount;
	private boolean commitments;
	private String report;
	
	/**
	 * @param commitments
	 * @param dateBegin
	 * @param dateEnd
	 * @param idAccount
	 * @param login
	 * @param operationType
	 */
	public Report(boolean commitments, String dateBegin, String dateEnd,
			long idAccount, String login, String operationType) {
		this.commitments = commitments;
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
		this.idAccount = idAccount;
		this.login = login;
		this.operationType = operationType;
	}

	public Report() {
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the dateBegin
	 */
	public String getDateBegin() {
		return dateBegin;
	}

	/**
	 * @param dateBegin
	 *            the dateBegin to set
	 */
	public void setDateBegin(String dateBegin) {
		this.dateBegin = dateBegin;
	}

	/**
	 * @return the dateEnd
	 */
	public String getDateEnd() {
		return dateEnd;
	}

	/**
	 * @param dateEnd
	 *            the dateEnd to set
	 */
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	/**
	 * @return the operationType
	 */
	public String getOperationType() {
		return operationType;
	}

	/**
	 * @param operationType
	 *            the operationType to set
	 */
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	/**
	 * @return the idAccount
	 */
	public long getIdAccount() {
		return idAccount;
	}

	/**
	 * @param idAccount
	 *            the idAccount to set
	 */
	public void setIdAccount(long idAccount) {
		this.idAccount = idAccount;
	}

	/**
	 * @return the commitments
	 */
	public boolean isCommitments() {
		return commitments;
	}

	/**
	 * @param commitments
	 *            the commitments to set
	 */
	public void setCommitments(boolean commitments) {
		this.commitments = commitments;
	}

	/**
	 * @param report the report to set
	 */
	public void setReport(String report) {
		this.report = report;
	}

	/**
	 * @return the report
	 */
	public String getReport() {
		return report;
	}

}
