package mymoney.model;

import mymoney.model.exceptions.BusinessException;
import mymoney.model.exceptions.DuplicatedAccountException;
import mymoney.model.exceptions.DuplicatedLoginException;
import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.LoginUnregisteredException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.PasswordMismatchException;
import mymoney.model.exceptions.PermissionDeniedException;

public interface MyMoney {

	void registerUser(String login, String password, String name,
			String gender, String mail) throws MissingArgumentException, InvalidArgumentException, InvalidEmailException, DuplicatedLoginException;

	void removeUser(String login);

	String getUserEmail(String login);

	String getUserGender(String login);

	String getUserName(String login);

	void doLogin(String login, String password) throws PasswordMismatchException, InvalidArgumentException, LoginUnregisteredException;

	long createAccount(String login, String label, String agency, String account) throws MissingArgumentException, DuplicatedAccountException;

	boolean isLogged(String login) throws LoginUnregisteredException;

	String getOperationType(long opId);

	String getoperationWay(long opId);

	double getOperationAmount(long opId);

	long getNumberOfOperations(String login);

	long addOperationIntoDefaultAccount(String login, String type, String way,
			double amount) throws BusinessException, PermissionDeniedException;

	long addOperation(String login, long accId, String type, String way, double amount) throws BusinessException, PermissionDeniedException;

	double getDefAccOverallAmount(String login) throws PermissionDeniedException;
	
	double getAccOverallAmount(String login, long accId) throws PermissionDeniedException;
	
}
