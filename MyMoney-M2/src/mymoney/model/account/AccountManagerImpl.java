package mymoney.model.account;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import mymoney.model.exceptions.DuplicatedAccountException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.util.ExceptionUtil;
import mymoney.model.util.HibernateUtil;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

public class AccountManagerImpl implements AccountManager {

	public long addOperation(String login, long accId, String type, String way,
			double amount) {
		Account account = getAccount(accId);
		if (account == null) /*LANCAR EXCECAO*/;
		Operation operation = new Operation(account, type, way, amount);
		return (Long) HibernateUtil.save(operation);
	}

	public long addOperationIntoDefaultAccount(String login, String type,
			String way, double amount) {
		return 0;
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
		return null;
	}

	public String getOperationWay(long opId) {
		return null;
	}

	public void removeAccount(String login, long id) {

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

	private Account getAccount(long accId) {
		return (Account) HibernateUtil.load(Account.class, accId);
	}
	
	public static void main(String[] args) throws MissingArgumentException, DuplicatedAccountException {
		AccountManager acc = new AccountManagerImpl();
		long accId = acc.createAccount("teste", "a", "b", "c");
		long opId = acc.addOperation("teste", accId, "blah", "bleh", 200);
		
		System.out.println(HibernateUtil.load(Operation.class, opId));
		System.out.println(((Account) HibernateUtil.load(Account.class, accId)).getOperations());
	}
	
}
