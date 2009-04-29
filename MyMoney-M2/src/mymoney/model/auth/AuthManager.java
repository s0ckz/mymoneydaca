package mymoney.model.auth;

import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.LoginUnregisteredException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.PasswordMismatchException;

public interface AuthManager {

	void register(String login, String password) throws InvalidArgumentException, MissingArgumentException;

	void doLogin(String login, String password) throws PasswordMismatchException, InvalidArgumentException, LoginUnregisteredException;

	boolean isLogged(String login) throws LoginUnregisteredException;

}
