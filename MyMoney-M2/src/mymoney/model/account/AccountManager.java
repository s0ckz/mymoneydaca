package mymoney.model.account;

import mymoney.model.exceptions.DuplicatedAccountException;
import mymoney.model.exceptions.MissingArgumentException;


public interface AccountManager {
	
	long createAccount(String login, String label, String agency,
			String account) throws MissingArgumentException, DuplicatedAccountException;
	
	void removeAccount(String login, long id);
	
	long addOperationIntoDefaultAccount(String login, String type,
			String way, double amount);
	
	long addOperation(String login, long accId, String type, String way, double amount);
	
	String getOperationType(long opId);
	
	String getOperationWay(long opId);

	long getNumberOfOperations(String login);

	double getOperationAmount(long opId);

}
