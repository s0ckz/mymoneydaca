package mymoney.model.account;

import mymoney.model.util.HibernateUtil;

public class AccountManagerImpl implements AccountManager {

	public long addOperation(String login, long accId, String type, String way,
			double amount) {
		return 0;
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
		new AccountManagerImpl().createAccount("login", "label", "agency", "acc");
	}

}
