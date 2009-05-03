/**
 * 
 */
package mymoney.model.report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
 * Classe que implementa um Relatório
 * 
 * @author Danilo Resende
 * @author Leandro José
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

	

	

	public long[] generateReport(String login, String begin, String end,
			long idAccount, String typeOperation)
			throws MissingArgumentException, PermissionDeniedException,
			AccountNotFoundException, InvalidDateException {
		ExceptionUtil.checkMissingArguments("login", login, "begin", begin,
				"end", end);

		Collection<Operation> operacoes = new ArrayList<Operation>();
		List<Long> operacoesToReport = new ArrayList<Long>();

		for (Long opId : accountManager.getAllOperations(login, idAccount)) {
			Operation operation = (Operation) HibernateUtil.load(
					Operation.class, opId);
			operacoes.add(operation);
		}

		for (Operation op : operacoes) {
			if (op.getDate().compareTo(DateUtils.createDate(begin)) >= 0
					&& op.getDate().compareTo(DateUtils.createDate(end)) <= 0) {
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
		HibernateUtil.save(report);
		long[] operationsIds = new long[operacoesToReport.size()];
		for (int i = 0; i < operationsIds.length; i++) {
			operacoesToReport.get(i);
		}
		return operationsIds;
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
