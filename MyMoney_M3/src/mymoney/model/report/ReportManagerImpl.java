/**
 * 
 */
package mymoney.model.report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import mymoney.model.account.AccountManager;
import mymoney.model.account.AccountManagerImpl;
import mymoney.model.account.Operation;
import mymoney.model.exceptions.AccountNotFoundException;
import mymoney.model.exceptions.InvalidDateException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.PermissionDeniedException;
import mymoney.model.util.DateUtils;
import mymoney.model.util.ExceptionUtil;
import mymoney.model.util.HibernateUtil;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.sun.org.omg.CORBA.OpDescriptionSeqHelper;

/**
 * 
 * Classe que implementa um Relat�rio
 * 
 * @author Danilo Resende
 * @author Leandro Jos�
 * @author Rodrigo Bruno
 */
public class ReportManagerImpl implements ReportManager {

	/**
	 * 
	 */
	private static final String DEBIT = "debit";

	/**
	 * 
	 */
	private static final String CREDIT = "credit";

	AccountManager accountManager;

	public ReportManagerImpl() {
		this.accountManager = new AccountManagerImpl();

	}

	public Collection<Long> generateReport(String login, String begin, String end,
			long idAccount, String typeOperation,
			Collection<Operation> operacoes) throws MissingArgumentException,
			PermissionDeniedException, AccountNotFoundException,
			InvalidDateException {
		ExceptionUtil.checkMissingArguments("login", login, "begin", begin,
				"end", end);

		Collection<Long> operacoesToReport = new ArrayList<Long>();
		for (Operation op : operacoes) {
			Date beginDate = DateUtils.createDate(begin);
			Date endDate = DateUtils.createDate(end);
			if(beginDate.compareTo(endDate) > 0){
				throw new InvalidDateException("Invalid Dates");
			}
			if (op.getDate().compareTo(beginDate) >= 0
					&& op.getDate().compareTo(endDate) <= 0) {
				if (!typeOperation.isEmpty()) {
					if (op.getType().equalsIgnoreCase(typeOperation)) {
						operacoesToReport.add(op.getId());
					}
				} else {
					operacoesToReport.add(op.getId());
				}
			}

		}

		Report report = new Report(login, begin, end, idAccount, typeOperation,
				operacoesToReport);
		
		
		return operacoesToReport;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getReports(String login) {
		Collection<SimpleExpression> expressions = Arrays.asList(Restrictions
				.eq("login", login));
		List<Report> list = (List<Report>) HibernateUtil
				.createQueryBasedOnExpressions(Report.class, expressions);
		return list.size();
	}

	@SuppressWarnings("unchecked")
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
