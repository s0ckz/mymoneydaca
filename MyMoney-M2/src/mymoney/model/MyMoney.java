package mymoney.model;

import mymoney.model.exceptions.AccountNotFoundException;
import mymoney.model.exceptions.BusinessException;
import mymoney.model.exceptions.DuplicatedAccountException;
import mymoney.model.exceptions.DuplicatedLoginException;
import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.LoginUnregisteredException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.PasswordMismatchException;
import mymoney.model.exceptions.PermissionDeniedException;
import mymoney.model.exceptions.UnknownOperationException;
import mymoney.model.exceptions.UserAlreadyLoggedException;
import mymoney.model.exceptions.UserNotLoggedException;
import mymoney.model.exceptions.UserUnregisteredException;

public interface MyMoney {

	void registerUser(String login, String password, String name,
			String gender, String mail) throws MissingArgumentException, InvalidArgumentException, InvalidEmailException, DuplicatedLoginException;

	void removeUser(String login) throws UserUnregisteredException, LoginUnregisteredException;

	String getUserEmail(String login) throws UserUnregisteredException;

	String getUserGender(String login) throws UserUnregisteredException;

	String getUserName(String login) throws UserUnregisteredException;

	void doLogin(String login, String password) throws PasswordMismatchException, InvalidArgumentException, LoginUnregisteredException, UserAlreadyLoggedException;

	long createAccount(String login, String label, String agency, String account) throws MissingArgumentException, DuplicatedAccountException;

	boolean isLogged(String login) throws LoginUnregisteredException;

	String getOperationType(long opId);

	String getoperationWay(long opId);

	double getOperationAmount(long opId);

	long getNumberOfOperations(String login);

	long addOperationIntoDefaultAccount(String login, String type, String way,
			double amount) throws BusinessException, PermissionDeniedException, AccountNotFoundException;

	long addOperation(String login, long accId, String type, String way, double amount) throws BusinessException, PermissionDeniedException, AccountNotFoundException;

	double getDefAccOverallAmount(String login) throws PermissionDeniedException, AccountNotFoundException;
	
	double getAccOverallAmount(String login, long accId) throws PermissionDeniedException, AccountNotFoundException;

	void removeOperation(String login, long opId) throws PermissionDeniedException, UnknownOperationException;

	void removeAccount(String login, long accId) throws PermissionDeniedException, AccountNotFoundException;

	void doLogoff(String login, String password) throws InvalidArgumentException, LoginUnregisteredException, PasswordMismatchException, UserNotLoggedException;

	void updateUser(String login, String name, String gender, String mail) throws MissingArgumentException, InvalidEmailException, InvalidArgumentException, UserUnregisteredException;

	void submitBankOperations(String login, String fileContent);
	
}
