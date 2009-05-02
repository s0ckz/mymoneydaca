/**
 * 
 */
package mymoney.model.report;

import mymoney.model.exceptions.MissingArgumentException;

/**
 * @author Danilo de Sa
 * @author Rodrigo Bruno
 * @author Leandro Jose
 * 
 */
public interface ReportManager {

	long generateReportCreditOperations(String login, String begin, String end,
			long idAccount) throws MissingArgumentException;

	long generateReportDebtOperations(String login, String begin, String end,
			long idAccount) throws MissingArgumentException;

	long generateReportOperations(String login, String begin, String end,
			long idAccount, String typeOperation) throws MissingArgumentException;

	int getReports(String login);

	void removeReports(String login);
}
