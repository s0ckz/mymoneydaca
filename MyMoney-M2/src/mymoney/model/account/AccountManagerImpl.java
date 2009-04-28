package mymoney.model.account;

import mymoney.model.util.HibernateUtil;

public class AccountManagerImpl implements AccountManager {

	public long addOperation(String login, long accId, String type, String way,
			double amount) {
		Account account = getAccount(accId);
		if (account == null) /*LANCAR EXCECAO*/;
		Operation operation = new Operation(account, type, way, amount);
		return (Long) HibernateUtil.save(operation);
	}

	private Account getAccount(long accId) {
		return (Account) HibernateUtil.load(Account.class, accId);
	}

	public long addOperationIntoDefaultAccount(String login, String type,
			String way, double amount) {
		return 0;
	}

	public long createAccount(String login, String label, String agency,
			String account) {
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
	
	public static void main(String[] args) {
		AccountManager acc = new AccountManagerImpl();
		long accId = acc.createAccount("teste", "a", "b", "c");
		long opId = acc.addOperation("teste", accId, "blah", "bleh", 200);
		
		System.out.println(HibernateUtil.load(Operation.class, opId));
		System.out.println(((Account) HibernateUtil.load(Account.class, accId)).getOperations());
	}
	
}
