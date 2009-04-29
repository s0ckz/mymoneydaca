package mymoney.model;

import mymoney.model.account.AccountManager;
import mymoney.model.account.AccountManagerImpl;
import mymoney.model.auth.AuthManager;
import mymoney.model.auth.AuthManagerImpl;
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
import mymoney.model.user.UserManager;
import mymoney.model.user.UserManagerImpl;


public class MyMoneyImpl implements MyMoney {
	
	private UserManager userManager = new UserManagerImpl();
	private AuthManager authManager = new AuthManagerImpl();
	private AccountManager accountManager = new AccountManagerImpl();
	
	public MyMoneyImpl() {
	}

	public String getUserEmail(String login) {
		return userManager.getUserEmail(login);
	}

	public String getUserGender(String login) {
		return userManager.getUserGender(login);
	}

	public String getUserName(String login) {
		return userManager.getUserName(login);
	}

	public void registerUser(String login, String password, String name,
			String gender, String mail) throws MissingArgumentException, InvalidArgumentException, InvalidEmailException, DuplicatedLoginException {
		userManager.register(login, password, name, gender, mail);
		authManager.register(login, password);
	}

	public void removeUser(String login) {
		userManager.removeUser(login);
	}

	public void doLogin(String login, String password) throws PasswordMismatchException, InvalidArgumentException, LoginUnregisteredException, UserAlreadyLoggedException {
		authManager.doLogin(login, password);
	}

	public long createAccount(String login, String label, String agency,
			String account) throws MissingArgumentException, DuplicatedAccountException {
		return accountManager.createAccount(login, label, agency, account);
	}

	public boolean isLogged(String login) throws LoginUnregisteredException {
		return authManager.isLogged(login);
	}

	public String getOperationType(long opId) {
		return accountManager.getOperationType(opId);
	}

	public String getoperationWay(long opId) {
		return accountManager.getOperationWay(opId);
	}

	public long getNumberOfOperations(String login) {
		return accountManager.getNumberOfOperations(login);
	}

	public double getOperationAmount(long opId) {
		return accountManager.getOperationAmount(opId);
	}

	public long addOperationIntoDefaultAccount(String login, String type,
			String way, double amount) throws BusinessException, PermissionDeniedException, AccountNotFoundException {
		return accountManager.addOperationIntoDefaultAccount(login, type, way, amount);
	}

	public long addOperation(String login, long accId, String type, String way,
			double amount) throws BusinessException, PermissionDeniedException, AccountNotFoundException {
		return accountManager.addOperation(login, accId, type, way, amount);
	}

	public double getDefAccOverallAmount(String login) throws PermissionDeniedException, AccountNotFoundException {
		return accountManager.getDefAccOverallAmount(login);
	}

	public double getAccOverallAmount(String login, long accId) throws PermissionDeniedException, AccountNotFoundException {
		return accountManager.getAccOverallAmount(login, accId);
	}

	public void removeOperation(String login, long opId) throws PermissionDeniedException, UnknownOperationException {
		accountManager.removeOperation(login, opId);
	}

	public void removeAccount(String login, long accId) throws PermissionDeniedException, AccountNotFoundException {
		accountManager.removeAccount(login, accId);
	}

	@Override
	public void doLogoff(String login, String password) throws InvalidArgumentException, LoginUnregisteredException, PasswordMismatchException, UserNotLoggedException {
		authManager.doLogoff(login, password);
	}

}
