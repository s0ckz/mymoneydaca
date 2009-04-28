package mymoney.model;

import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.user.UserManager;
import mymoney.model.user.UserManagerImpl;


public class MyMoneyImpl implements MyMoney {
	
	private UserManager userManager = new UserManagerImpl();
	
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
			String gender, String mail) throws MissingArgumentException, InvalidArgumentException, InvalidEmailException {
		userManager.register(login, password, name, gender, mail);
	}

	public void removeUser(String login) {
		userManager.removeUser(login);
	}

}
