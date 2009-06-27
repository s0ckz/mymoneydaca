package mymoney.ws;

import mymoney.model.MyMoney;
import mymoney.model.MyMoneyImpl;
import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.LoginUnregisteredException;
import mymoney.model.exceptions.PasswordMismatchException;
import mymoney.model.exceptions.UserAlreadyLoggedException;

public class WebServicesFacade {

	private MyMoney myMoney;

	public WebServicesFacade() {
		myMoney = new MyMoneyImpl();
	}

	public String doLogin(String login, String password)
			throws PasswordMismatchException, InvalidArgumentException,
			LoginUnregisteredException, UserAlreadyLoggedException {
		myMoney.doLogin(login, password);
		return null;
	}

	public boolean isLogged(String login) throws LoginUnregisteredException {
		return myMoney.isLogged(login);
	}

}
