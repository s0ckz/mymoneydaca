package mymoney.model.user;

import mymoney.model.util.HibernateUtil;

public class UserManagerImpl implements UserManager {
	
	public void register(String login, String password, String name,
			String gender, String eMail) {
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
