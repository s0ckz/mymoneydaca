package mymoney.model;

import mymoney.model.auth.AuthManager;
import mymoney.model.auth.AuthManagerImpl;
import mymoney.model.exceptions.DuplicatedLoginException;
import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.LoginUnregisteredException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.PasswordMismatchException;
import mymoney.model.user.UserManager;
import mymoney.model.user.UserManagerImpl;


public class MyMoneyImpl implements MyMoney {
	
	private UserManager userManager = new UserManagerImpl();
	private AuthManager authManager = new AuthManagerImpl();
	
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

	@Override
	public void doLogin(String login, String password) throws PasswordMismatchException, InvalidArgumentException, LoginUnregisteredException {
		authManager.doLogin(login, password);
	}

}
