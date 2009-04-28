package mymoney.model.user;

import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.util.ExceptionUtil;
import mymoney.model.util.HibernateUtil;

public class UserManagerImpl implements UserManager {
	
	public void register(String login, String password, String name,
			String gender, String eMail) throws MissingArgumentException, InvalidArgumentException, InvalidEmailException {
		ExceptionUtil.checkMissingArguments("login", login, "password", password, "name", name);
		ExceptionUtil.checkInvalidRequiredArguments("login", login, "password", password);
		ExceptionUtil.checkEmail(eMail);
		User user = new User(login, name, gender, eMail);
		HibernateUtil.save(user);
	}
	
	public String getUserName(String login) {
		User user = (User) HibernateUtil.load(User.class, login);
		return user.getName();
	}
	
	public String getUserGender(String login) {
		User user = (User) HibernateUtil.load(User.class, login);
		return user.getGender();
	}
	
	public String getUserEmail(String login) {
		User user = (User) HibernateUtil.load(mymoney.model.user.User.class, login);
		return user.getEmail();
	}
	
	public void removeUser(String login) {
		User user = (User) HibernateUtil.load(User.class, login);
		if (user != null) HibernateUtil.delete(user);
	}

}
