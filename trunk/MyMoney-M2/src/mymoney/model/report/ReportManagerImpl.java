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
import mymoney.model.util.HibernateUtil;

/**
 * @author Rodrigo
 * 
 */
public class ReportManagerImpl implements ReportManager {

	private int numberOfOperationsReport;
	private int numberofCreditOperationsReport;
	private int numberOfCommitmentsReport;
	private int numberofDebtOperationsReport;

	private AccountManager accountManager;
	private CommitmentManager commitmentManager;

	public ReportManagerImpl() {
		this.accountManager = new AccountManagerImpl();
		this.commitmentManager = new CommitmentManagerImpl();
		this.numberOfCommitmentsReport = 0;
		this.numberofCreditOperationsReport = 0;
		this.numberofDebtOperationsReport = 0;
		this.numberOfOperationsReport = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mymoney.model.report.ReportManager#generateReport(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, long, boolean)
	 */
	@Override
	public void generateReport(String login, String begin, String end,
			String operationType, long idAccount, boolean commitments) {

		
		if (commitments) {
			Collection<SimpleExpression> expressions = Arrays.asList(Restrictions
					.eq("login", login));
			List<Commitment> list = (List<Commitment>) HibernateUtil
					.createQueryBasedOnExpressions(Commitment.class, expressions);
			int i = 0;
			for (Commitment commitment : list) {
				
			}
		}
		
	}

	/**
	 * @return the numberofCreditOperationsReport
	 */
	public int getNumberofCreditOperationsReport() {
		return numberofCreditOperationsReport;
	}

	/**
	 * @param numberofCreditOperationsReport
	 *            the numberofCreditOperationsReport to set
	 */
	public void setNumberofCreditOperationsReport(
			int numberofCreditOperationsReport) {
		this.numberofCreditOperationsReport = numberofCreditOperationsReport;
	}

	/**
	 * @return the numberofDebtOperationsReport
	 */
	public int getNumberofDebtOperationsReport() {
		return numberofDebtOperationsReport;
	}

	/**
	 * @param numberofDebtOperationsReport
	 *            the numberofDebtOperationsReport to set
	 */
	public void setNumberofDebtOperationsReport(int numberofDebtOperationsReport) {
		this.numberofDebtOperationsReport = numberofDebtOperationsReport;
	}

	/**
	 * @return the accountManager
	 */
	public AccountManager getAccountManager() {
		return accountManager;
	}

	/**
	 * @param accountManager
	 *            the accountManager to set
	 */
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	/**
	 * @return the commitmentManager
	 */
	public CommitmentManager getCommitmentManager() {
		return commitmentManager;
	}

	/**
	 * @param commitmentManager
	 *            the commitmentManager to set
	 */
	public void setCommitmentManager(CommitmentManager commitmentManager) {
		this.commitmentManager = commitmentManager;
	}

	/**
	 * @param numberOfOperationsReport
	 *            the numberOfOperationsReport to set
	 */
	public void setNumberOfOperationsReport(int numberOfOperationsReport) {
		this.numberOfOperationsReport = numberOfOperationsReport;
	}

	/**
	 * @param numberOfCommitmentsReport
	 *            the numberOfCommitmentsReport to set
	 */
	public void setNumberOfCommitmentsReport(int numberOfCommitmentsReport) {
		this.numberOfCommitmentsReport = numberOfCommitmentsReport;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mymoney.model.report.ReportManager#getNumberOfCommitmentsReport()
	 */
	@Override
	public int getNumberOfCommitmentsReport() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mymoney.model.report.ReportManager#getNumberOfCreditOperationsReport()
	 */
	@Override
	public int getNumberOfCreditOperationsReport() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mymoney.model.report.ReportManager#getNumberOfDebtOperationsReport()
	 */
	@Override
	public int getNumberOfDebtOperationsReport() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mymoney.model.report.ReportManager#getNumberOfOperationsReport()
	 */
	@Override
	public int getNumberOfOperationsReport() {
		// TODO Auto-generated method stub
		return 0;
	}

}
