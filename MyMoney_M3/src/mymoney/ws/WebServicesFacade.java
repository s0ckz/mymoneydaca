package mymoney.ws;

import java.util.Collection;
import java.util.LinkedList;

import mymoney.model.MyMoney;
import mymoney.model.MyMoneyImpl;
import mymoney.model.exceptions.AccountNotFoundException;
import mymoney.model.exceptions.DuplicatedLoginException;
import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.LoginUnregisteredException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.PasswordMismatchException;
import mymoney.model.exceptions.PermissionDeniedException;
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
	
	public String registerUser(String login, String password, String name,
			String gender, String mail) throws MissingArgumentException, InvalidArgumentException, InvalidEmailException, DuplicatedLoginException {
		myMoney.registerUser(login, password, name, gender, mail);
		return null;
	}

	/**
	 * Retorna uma colecao com todas as contas de um dado usuario.
	 * @param login O login do usuario.
	 */
	public Collection<Long> getAllAccountsIds(String login) {
		return myMoney.getAllAccountsIds(login);
	}

	public Collection<String> getAccountsLabels(String login, Collection<Number> accountsIds) throws PermissionDeniedException, AccountNotFoundException {
		Collection<String> labels = new LinkedList<String>();
		for (Number accId : accountsIds) {
			labels.add(getAccountLabel(login, accId.longValue()));
		}
		return labels;
	}
	
	/**
	 * Recupera o label de uma conta de um dado usuario.
	 * @param login O login do usuario dono da conta.
	 * @param accId O id da conta.
	 * @throws PermissionDeniedException Se o usuario nao for o dono da conta.
	 * @throws AccountNotFoundException Se a conta nao estiver cadastrada no sistema.
	 */
	public String getAccountLabel(String login, Long accId) throws PermissionDeniedException, AccountNotFoundException {
		return myMoney.getAccountLabel(login, accId);
	}

}
