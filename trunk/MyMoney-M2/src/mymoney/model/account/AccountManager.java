package mymoney.model.account;

import mymoney.model.exceptions.AccountNotFoundException;
import mymoney.model.exceptions.BusinessException;
import mymoney.model.exceptions.DuplicatedAccountException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.PermissionDeniedException;
import mymoney.model.exceptions.UnknownOperationException;


public interface AccountManager {
	
	long createAccount(String login, String label, String agency,
			String account) throws MissingArgumentException, DuplicatedAccountException;
	
	void removeAccount(String login, long id) throws PermissionDeniedException, AccountNotFoundException;
	
	long addOperationIntoDefaultAccount(String login, String type,
			String way, double amount) throws BusinessException, PermissionDeniedException, AccountNotFoundException;
	
	long addOperation(String login, long accId, String type, String way, double amount) throws BusinessException, PermissionDeniedException, AccountNotFoundException;
	
	String getOperationType(long opId);
	
	String getOperationWay(long opId);

	long getNumberOfOperations(String login);

	double getOperationAmount(long opId);

	double getDefAccOverallAmount(String login) throws PermissionDeniedException, AccountNotFoundException;

	double getAccOverallAmount(String login, long accId) throws PermissionDeniedException, AccountNotFoundException;

	void removeOperation(String login, long opId) throws PermissionDeniedException, UnknownOperationException;

	long[] getAllOperations(String login, long accId);

}
