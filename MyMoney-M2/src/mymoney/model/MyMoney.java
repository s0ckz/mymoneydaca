package mymoney.model;

import mymoney.model.exceptions.DuplicatedLoginException;
import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.LoginUnregisteredException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.PasswordMismatchException;

public interface MyMoney {

	void registerUser(String login, String password, String name,
			String gender, String mail) throws MissingArgumentException, InvalidArgumentException, InvalidEmailException, DuplicatedLoginException;

	void removeUser(String login);

	String getUserEmail(String login);

	String getUserGender(String login);

	String getUserName(String login);

	void doLogin(String login, String password) throws PasswordMismatchException, InvalidArgumentException, LoginUnregisteredException;
	
}
