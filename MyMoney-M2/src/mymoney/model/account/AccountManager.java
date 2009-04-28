package mymoney.model.account;

import mymoney.model.user.User;

public interface AccountManager {
	
	long createAccount(String login, String label, String agency,
			String account);
	
	void removeAccount(String login, long id);
	
	long addOperationIntoDefaultAccount(String login, String type,
			String way, double amount);
	
	long addOperation(String login, long accId, String type, String way, double amount);
	
	String getOperationType(long opId);
	
	String getOperationWay(long opId);

}
