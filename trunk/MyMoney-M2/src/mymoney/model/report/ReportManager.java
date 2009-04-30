/**
 * 
 */
package mymoney.model.report;

/**
 * @author Danilo de Sá
 * @author Rodrigo Bruno
 * @author Leandro José
 * 
 */
public interface ReportManager {

	/**
	 * Generates a Report
	 * 
	 * @param login
	 *            user´s login
	 * @param begin
	 *            begin date
	 * @param end
	 *            end date
	 * @param operationType
	 *            type of operation
	 * @param idAccount
	 *            the id of the account
	 * @param commitments
	 */
	void generateReport(String login, String begin, String end,
			String operationType, long idAccount, boolean commitments);

	/**
	 * Return the number of operations report
	 * 
	 * @return int that represents the number of operations report
	 */
	int getNumberOfOperationsReport();

	/**
	 * 
	 * @return
	 */
	int getNumberOfCreditOperationsReport();

	/**
	 * 
	 * @return
	 */
	int getNumberOfDebtOperationsReport();

	/**
	 * 
	 * @return
	 */
	public int getNumberOfCommitmentsReport();
}
