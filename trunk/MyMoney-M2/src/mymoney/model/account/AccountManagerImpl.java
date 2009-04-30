package mymoney.model.account;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import mymoney.model.exceptions.AccountNotFoundException;
import mymoney.model.exceptions.BusinessException;
import mymoney.model.exceptions.DuplicatedAccountException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.PermissionDeniedException;
import mymoney.model.exceptions.UnknownOperationException;
import mymoney.model.util.ExceptionUtil;
import mymoney.model.util.HibernateUtil;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

public class AccountManagerImpl implements AccountManager {

	private static final String DEFAULT_ACCOUNT = "default";

	public long addOperation(String login, long accId, String type, String way,
			double amount) throws BusinessException, PermissionDeniedException, AccountNotFoundException {
		if (amount < 0.0)
			throw new BusinessException("negative amount");
		else if (amount == 0.0)
			throw new BusinessException("zero amount");
		Account account = getAccount(login, accId);
		Operation operation = new Operation(account, type, way, amount);
		return (Long) HibernateUtil.save(operation);
	}

	public long addOperationIntoDefaultAccount(String login, String type,
			String way, double amount) throws BusinessException, PermissionDeniedException, AccountNotFoundException {
		long defaultAccount = getDefaultAccount(login).getId();
		return addOperation(login, defaultAccount, type, way, amount);
	}

	public long createAccount(String login, String label, String agency,
			String account) throws MissingArgumentException, DuplicatedAccountException {
		ExceptionUtil.checkMissingArguments("login", login, "label", label, "agency", 
				agency, "account", account);
		if (getAccount(login, label, agency, account) != null)
			throw new DuplicatedAccountException();
		Account accountToBeCreated = new Account(login, label, agency, account);
		return (Long) HibernateUtil.save(accountToBeCreated);
	}

	public String getOperationType(long opId) {
		Operation operation = getOperation(opId);
		if (operation == null)
			return "";
		return operation.getType();
	}

	public String getOperationWay(long opId) {
		Operation operation = getOperation(opId);
		if (operation == null)
			return "";
		return operation.getWay();
	}

	public long getNumberOfOperations(String login) {
//		Operation operation = (Operation) HibernateUtil.load(Operation.class, opId);
//		if (operation == null)
//			return 0;
		return 0;
	}

	public double getOperationAmount(long opId) {
		Operation operation = getOperation(opId);
		if (operation == null)
			return 0.0;
		return operation.getAmount();
	}
	
	public double getAccOverallAmount(String login, long accId) throws PermissionDeniedException, AccountNotFoundException {
		Account account = getAccount(login, accId);
		double amount = 0.0;
		for (Operation op : account.getOperations()) {
			amount += op.correctAmount();
		}
		return amount;
	}

	public double getDefAccOverallAmount(String login) throws PermissionDeniedException, AccountNotFoundException {
		long defaultAccount = getDefaultAccount(login).getId();
		return getAccOverallAmount(login, defaultAccount);
	}

	public void removeAccount(String login, long id) throws PermissionDeniedException, AccountNotFoundException {
		Account account = getAccount(login, id);
		HibernateUtil.delete(account);
	}

	public void removeOperation(String login, long opId) throws UnknownOperationException, PermissionDeniedException {
		Operation operation = getOperation(login, opId);
		HibernateUtil.delete(operation);
	}

	@SuppressWarnings("unchecked")
	private Account getAccount(String login, String label, String agency,
			String account) {
		Collection<SimpleExpression> expressions = 
			Arrays.asList(Restrictions.eq("login", login), 
					Restrictions.eq("label", label), 
					Restrictions.eq("agency", agency), 
					Restrictions.eq("account", account));
		
		List<Account> list = (List<Account>) HibernateUtil.createQueryBasedOnExpressions(Account.class, expressions);
		
		if (list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	private Account getDefaultAccount(String login) {
		Account account = getAccount(login, DEFAULT_ACCOUNT, DEFAULT_ACCOUNT, DEFAULT_ACCOUNT);
		if (account == null) {
			try {
				createAccount(login, DEFAULT_ACCOUNT, DEFAULT_ACCOUNT, DEFAULT_ACCOUNT);
			} catch (MissingArgumentException e) {
			} catch (DuplicatedAccountException e) {
			}
			return getAccount(login, DEFAULT_ACCOUNT, DEFAULT_ACCOUNT, DEFAULT_ACCOUNT);
		}
		return account;
	}

	private Account getAccount(String login, long accId) throws PermissionDeniedException, AccountNotFoundException {
		Account account = (Account) HibernateUtil.load(Account.class, accId);
		if (account == null)
			throw new AccountNotFoundException();
		if (!account.getLogin().equals(login))
			throw new PermissionDeniedException("you do not own this account");
		return account;
	}

	private Operation getOperation(long opId) {
		Operation operation = (Operation) HibernateUtil.load(Operation.class, opId);
		return operation;
	}
	
	private Operation getOperation(String login, long opId) throws PermissionDeniedException, UnknownOperationException {
		Operation operation = getOperation(opId);
		if (operation == null)
			throw new UnknownOperationException();
		if (!operation.getAccount().getLogin().equals(login))
			throw new PermissionDeniedException("you do not own this operation");
		return operation;
	}
	
	public static void main(String[] args) throws MissingArgumentException, DuplicatedAccountException, BusinessException, PermissionDeniedException, AccountNotFoundException {
		AccountManager acc = new AccountManagerImpl();
		long accId = acc.createAccount("teste", "a", "b", "c");
		long opId = acc.addOperation("teste", accId, "blah", "bleh", 200);
		
		System.out.println(HibernateUtil.load(Operation.class, opId));
		System.out.println(((Account) HibernateUtil.load(Account.class, accId)).getOperations());
	}

	@Override
	public long[] getAllOperations(String login, long accId) {
		// TODO
		return new long[]{1,2};
	}
	
}
