package mymoney.model.user;

import mymoney.model.exceptions.DuplicatedLoginException;
import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.UserUnregisteredException;
import mymoney.model.util.ExceptionUtil;
import mymoney.model.util.HibernateUtil;

/**
 * Implementacao da interface <code>UserManager</code>.
 * 
 * @see <code>UserManager</code>
 */
public class UserManagerImpl implements UserManager {
	
	public void register(String login, String name,
			String gender, String eMail) throws MissingArgumentException, InvalidArgumentException, InvalidEmailException, DuplicatedLoginException {
		ExceptionUtil.checkMissingArguments("login", login, "name", name);
		ExceptionUtil.checkInvalidRequiredArguments("login", login);
		ExceptionUtil.checkEmail(eMail);
		
		// TODO: FAZER ALGO MAIS ESPERTO, UMA CONSULTA...
		User temp = (User) HibernateUtil.load(User.class, login);
		if (temp != null) throw new DuplicatedLoginException();
		
		User user = new User(login, name, gender, eMail);
		HibernateUtil.save(user);
	}
	
	public String getUserName(String login) throws UserUnregisteredException {
		User user = makeUser(login);
		return user.getName();
	}
	
	public String getUserGender(String login) throws UserUnregisteredException {
		User user = makeUser(login);
		return user.getGender();
	}
	
	public String getUserEmail(String login) throws UserUnregisteredException {
		User user = makeUser(login);
		return user.getEmail();
	}
	
	public void removeUser(String login) throws UserUnregisteredException {
		User user = makeUser(login);
		if (user != null) HibernateUtil.delete(user);
	}

	/*
	 * Carrega um usuario do banco de dados.
	 */
	private User makeUser(String login) throws UserUnregisteredException {
		User user = (User) HibernateUtil.load(User.class, login);
		if (user == null) {
			throw new UserUnregisteredException();
		}
		return user;
	}

	@Override
	public void updateUser(String login, String name, String gender,
			String email) throws UserUnregisteredException, MissingArgumentException, InvalidEmailException, InvalidArgumentException {
		ExceptionUtil.checkMissingArguments("login", login, "name", name);
		ExceptionUtil.checkInvalidRequiredArguments("login", login);
		ExceptionUtil.checkEmail(email);

		User user = makeUser(login);
		user.setName(name);
		user.setGender(gender);
		user.setEmail(email);
		HibernateUtil.update(user);
	}

}
