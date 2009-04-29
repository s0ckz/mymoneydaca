package mymoney.model.auth;

import mymoney.model.util.HibernateUtil;

public class AuthManagerImpl implements AuthManager {

	@Override
	public void register(String login, String password) {
		Auth auth = new Auth(login, password, false);
		HibernateUtil.save(auth);
	}

	@Override
	public void doLogin(String login, String password) {
		Auth gotten = (Auth) HibernateUtil.load(Auth.class, login);
		if (!gotten.getPassword().equals(password)) {
//			throw new Exception();
		}
		gotten.setLoggedIn(true);
		HibernateUtil.update(gotten);
	}

}
