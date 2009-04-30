package mymoney.model;

import java.io.IOException;

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
import mymoney.model.exceptions.MisunderstandingFileContent;
import mymoney.model.exceptions.MyMoneyException;
import mymoney.model.exceptions.PasswordMismatchException;
import mymoney.model.exceptions.PermissionDeniedException;
import mymoney.model.exceptions.UnknownOperationException;
import mymoney.model.exceptions.UserAlreadyLoggedException;
import mymoney.model.exceptions.UserNotLoggedException;
import mymoney.model.exceptions.UserUnregisteredException;
import mymoney.model.user.UserManager;
import mymoney.model.user.UserManagerImpl;
import mymoney.model.xpto.XptoManager;
import mymoney.model.xpto.XptoManagerImpl;


public class MyMoneyImpl implements MyMoney {
	
	private UserManager userManager = new UserManagerImpl();
	private AuthManager authManager = new AuthManagerImpl();
	private AccountManager accountManager = new AccountManagerImpl();
	private XptoManager xptoManager = new XptoManagerImpl();
	
	public MyMoneyImpl() {
	}

	public String getUserEmail(String login) throws UserUnregisteredException {
		return userManager.getUserEmail(login);
	}

	public String getUserGender(String login) throws UserUnregisteredException {
		return userManager.getUserGender(login);
	}

	public String getUserName(String login) throws UserUnregisteredException {
		return userManager.getUserName(login);
	}

	public void registerUser(String login, String password, String name,
			String gender, String mail) throws MissingArgumentException, InvalidArgumentException, InvalidEmailException, DuplicatedLoginException {
		userManager.register(login, name, gender, mail);
		try {
			authManager.register(login, password);
		} catch (InvalidArgumentException e) {
			rollbackUserRegister(login, e);
			throw e;
		} catch (MissingArgumentException e) {
			rollbackUserRegister(login, e);
			throw e;
		} catch (DuplicatedLoginException e) {
			rollbackUserRegister(login, e);
			throw e;
		}
	}

	private void rollbackUserRegister(String login, MyMoneyException e) {
		try {
			userManager.removeUser(login);
		} catch (UserUnregisteredException e1) {}
	}

	public void removeUser(String login) throws UserUnregisteredException, LoginUnregisteredException {
		userManager.removeUser(login);
		authManager.remove(login);
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

	@Override
	public void updateUser(String login, String name, String gender, String mail) throws MissingArgumentException, InvalidEmailException, InvalidArgumentException, UserUnregisteredException {
		userManager.updateUser(login, name, gender, mail);
	}

	@Override
	public long[] submitBankOperationsCSV(String login, String fileContent) throws BusinessException, PermissionDeniedException, AccountNotFoundException, MisunderstandingFileContent {
		return xptoManager.submitBankOperationsCSV(login, fileContent);
	}

	@Override
	public long[] submitBankOperationsTXT(String login, String fileContent) throws MisunderstandingFileContent, BusinessException, PermissionDeniedException, AccountNotFoundException {
		return xptoManager.submitBankOperationsTXT(login, fileContent);
	}

	@Override
	public void exportBankOperationsCSV(String login, long accId,
			String pathToFile) throws IOException {
		xptoManager.exportBankOperationsCSV(login, accId, pathToFile);
	}
	
	@Override
	public void exportBankOperationsTXT(String login, long accId,
			String pathToFile) throws IOException {
		xptoManager.exportBankOperationsTXT(login, accId, pathToFile);
	}
}
