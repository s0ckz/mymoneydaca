/**
 * 
 */
package mymoney.model.report;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import mymoney.model.account.AccountManager;
import mymoney.model.account.AccountManagerImpl;
import mymoney.model.commitment.Commitment;
import mymoney.model.commitment.CommitmentManager;
import mymoney.model.commitment.CommitmentManagerImpl;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.util.ExceptionUtil;
import mymoney.model.util.HibernateUtil;
import mymoney.model.util.ParseDate;

/**
 * @author Rodrigo
 * 
 */
public class ReportManagerImpl implements ReportManager {

	public ReportManagerImpl() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mymoney.model.report.ReportManager#generateReportCreditOperations(java
	 * .lang.String, java.lang.String, java.lang.String, long)
	 */
	@Override
	public long generateReportCreditOperations(String login, String begin,
			String end, long idAccount) throws MissingArgumentException {

		return generateReportOperations(login, begin, end, idAccount, "Credit");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mymoney.model.report.ReportManager#generateReportDebtOperations(java.
	 * lang.String, java.lang.String, java.lang.String, long)
	 */
	@Override
	public long generateReportDebtOperations(String login, String begin,
			String end, long idAccount) throws MissingArgumentException {

		return generateReportOperations(login, begin, end, idAccount, "Debit");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mymoney.model.report.ReportManager#generateReportOperations(java.lang
	 * .String, java.lang.String, java.lang.String, long)
	 */
	@Override
	public long generateReportOperations(String login, String begin,
			String end, long idAccount, String typeOperation)
			throws MissingArgumentException {
		ExceptionUtil.checkMissingArguments("login", login, "begin", begin,
				"end", end, "typeOperation", typeOperation);

		Report report = new Report(login, begin, end, idAccount, typeOperation);
		return (Long) HibernateUtil.save(report);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mymoney.model.report.ReportManager#getReport(long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int getReports(String login) {
		Collection<SimpleExpression> expressions = Arrays.asList(Restrictions
				.eq("login", login));
		List<Report> list = (List<Report>) HibernateUtil
				.createQueryBasedOnExpressions(Report.class, expressions);
		return list.size();
	}

	
	
	public void removeReports(String login) {
		Collection<SimpleExpression> expressions = Arrays.asList(Restrictions
				.eq("login", login));
		List<Report> list = (List<Report>) HibernateUtil
				.createQueryBasedOnExpressions(Report.class, expressions);

		for (Report report : list) {
			HibernateUtil.delete(report);
		}
	}

}
