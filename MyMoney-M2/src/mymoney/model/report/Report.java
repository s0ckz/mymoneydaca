/**
 * 
 */
package mymoney.model.report;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Rodrigo
 * 
 */
@Entity
@Table(name="reports")
public class Report {

	private String login = "";
	private String dateBegin = "";
	private String dateEnd = "";
	private String operationType = "";
	private long idAccount;
	private String label;
	private long reportCode;
	
	/**
	 * @param commitments
	 * @param dateBegin
	 * @param dateEnd
	 * @param idAccount
	 * @param login
	 * @param operationType
	 */
	public Report(String login , String dateBegin, String dateEnd,
			long idAccount, String operationType) {
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
		this.idAccount = idAccount;
		this.login = login;
		this.operationType = operationType;
		this.label="";
		
		
	}

	public Report() {
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
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the dateBegin
	 * 
	 */
	@Column(name = "datebegin")
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
	@Column(name = "dateEnd")
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
	@Column(name = "type")
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
	@Column(name = "idAccount")
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
	 * @param label the label to set
	 */
	
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the label
	 */
	@Column(name = "label")
	public String getLabel() {
		return label;
	}

	/**
	 * @param reportCode the reportCode to set
	 */
	public void setReportCode(long reportCode) {
		this.reportCode = reportCode;
	}

	/**
	 * @return the reportCode
	 */
	@Id
	@GeneratedValue
	@Column(name = "code")
	public long getReportCode() {
		return reportCode;
	}

	
	

	
}
