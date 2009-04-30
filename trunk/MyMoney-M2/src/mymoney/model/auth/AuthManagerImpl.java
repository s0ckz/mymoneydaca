package mymoney.model.auth;

import mymoney.model.exceptions.DuplicatedLoginException;
import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.LoginUnregisteredException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.PasswordMismatchException;
import mymoney.model.exceptions.UserAlreadyLoggedException;
import mymoney.model.exceptions.UserNotLoggedException;
import mymoney.model.util.ExceptionUtil;
import mymoney.model.util.HibernateUtil;

public class AuthManagerImpl implements AuthManager {

	@Override
	public void register(String login, String password) throws InvalidArgumentException, MissingArgumentException, DuplicatedLoginException {
		ExceptionUtil.checkMissingArguments("login", login, "password", password);
		ExceptionUtil.checkInvalidRequiredArguments("login", login, "password", password);
		Auth temp = (Auth) HibernateUtil.load(Auth.class, login);
		if (temp != null) throw new DuplicatedLoginException();

		Auth auth = new Auth(login, password, false);
		HibernateUtil.save(auth);
	}

	@Override
	public void doLogin(String login, String password) throws PasswordMismatchException, InvalidArgumentException, LoginUnregisteredException, UserAlreadyLoggedException {
		ExceptionUtil.checkInvalidRequiredArguments("login", login, "password", password);
		
		Auth gotten = make(login);
		if (!gotten.getPassword().equals(password)) {
			throw new PasswordMismatchException();
		}
		if (gotten.isLoggedIn()) {
			throw new UserAlreadyLoggedException();
		}
		gotten.setLoggedIn(true);
		HibernateUtil.update(gotten);
	}

	@Override
	public boolean isLogged(String login) throws LoginUnregisteredException {
		Auth gotten = make(login);
		return gotten.isLoggedIn();
	}

	@Override
	public void doLogoff(String login, String password) throws InvalidArgumentException, LoginUnregisteredException, PasswordMismatchException, UserNotLoggedException {
		ExceptionUtil.checkInvalidRequiredArguments("login", login, "password", password);
		Auth gotten = make(login);
		if (!gotten.getPassword().equals(password)) {
			throw new PasswordMismatchException();
		}
		if (!gotten.isLoggedIn()) {
			throw new UserNotLoggedException();
		}
		gotten.setLoggedIn(false);
		HibernateUtil.update(gotten);
	}

	private Auth make(String login) throws LoginUnregisteredException {
		Auth gotten = (Auth) HibernateUtil.load(Auth.class, login);
		if (gotten == null) throw new LoginUnregisteredException();
		return gotten;
	}

	@Override
	public void remove(String login) throws LoginUnregisteredException {
		Auth auth = make(login);
		HibernateUtil.delete(auth);
	}

}
