package mymoney.model.account;

import mymoney.model.exceptions.BusinessException;
import mymoney.model.exceptions.DuplicatedAccountException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.PermissionDeniedException;
import mymoney.model.exceptions.UnknownOperationException;


public interface AccountManager {
	
	long createAccount(String login, String label, String agency,
			String account) throws MissingArgumentException, DuplicatedAccountException;
	
	void removeAccount(String login, long id);
	
	long addOperationIntoDefaultAccount(String login, String type,
			String way, double amount) throws BusinessException, PermissionDeniedException;
	
	long addOperation(String login, long accId, String type, String way, double amount) throws BusinessException, PermissionDeniedException;
	
	String getOperationType(long opId);
	
	String getOperationWay(long opId);

	long getNumberOfOperations(String login);

	double getOperationAmount(long opId);

	double getDefAccOverallAmount(String login) throws PermissionDeniedException;

	double getAccOverallAmount(String login, long accId) throws PermissionDeniedException;

	void removeOperation(String login, long opId) throws PermissionDeniedException, UnknownOperationException;

}
