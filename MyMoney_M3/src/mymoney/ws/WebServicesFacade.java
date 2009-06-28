package mymoney.ws;

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
import mymoney.model.exceptions.UserNotLoggedException;
import mymoney.model.util.HibernateUtil;

public class WebServicesFacade {

	private MyMoney myMoney;

	public WebServicesFacade() {
		myMoney = new MyMoneyImpl();
	}

	// Utils - utilizado para testes do Selenium

	/**
	 * Limpa o banco de dados.
	 */
	public String cleanAll() {
		HibernateUtil.cleanAll();
		return null;
	}

	/**
	 * Executa login no sistema. Essa operacao permite que o usuario possa usar
	 * todas as funcionalidades do MyMoney, por exemplo: cadastrar uma conta,
	 * adicionar uma operacao financeira, etc.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @param password
	 *            A senha do usuario.
	 * @throws PasswordMismatchException
	 *             Se a senha nao conferir com a cadastrada no sistema.
	 * @throws InvalidArgumentException
	 *             Se login e/ou senha forem invalidos.
	 * @throws LoginUnregisteredException
	 *             Se o login nao tiver sido cadastrado no sistema.
	 * @throws UserAlreadyLoggedException
	 *             Se o usuario ja estiver logado no sistema.
	 */
	public String doLogin(String login, String password)
			throws PasswordMismatchException, InvalidArgumentException,
			LoginUnregisteredException, UserAlreadyLoggedException {
		myMoney.doLogin(login, password);
		return null;
	}
	
	/**
	 * Retorna <code>true</code> se o usuario estiver logado no sistema, caso
	 * contrario, retorna <code>false</code>.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @return Se o usuario estiver logado.
	 * @throws LoginUnregisteredException
	 *             Se o login nao tiver sido cadastrado no sistema
	 */
	public boolean isLogged(String login) throws LoginUnregisteredException {
		return myMoney.isLogged(login);
	}
	
	/**
	 * Realiza cadastro de um novo usuario no sistema. <br>
	 * Para que o cadastro seja efetuado com sucesso, o parametro login deve ser
	 * uma cadeia de caracteres de tamanho maior ou igual a 4 contendo apenas
	 * caracteres que podem ser alfa numericos, underline, hifen ou ponto.
	 * 
	 * O e-mail deve ser vazio ou respeitar o seguinte padrao:
	 * $usuario@$dominio.$extensao, em que $usuario, $dominio devem ser uma
	 * cadeira de caracteres qualquer de tamanho maior ou igual a 1 e $extensao
	 * deve ser uma cadeia de letras minusculas.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @param name
	 *            O nome do usuario.
	 * @param gender
	 *            O sexo do usuario.
	 * @param eMail
	 *            O e-mail do usuario.
	 * @throws MissingArgumentException
	 *             Se login e/ou nome forem nulos ou vazios.
	 * @throws InvalidArgumentException
	 *             Se login for invalido.
	 * @throws InvalidEmailException
	 *             Se o e-mail for invalido.
	 * @throws DuplicatedLoginException
	 *             Se ja houver um login identico cadastrado.
	 */
	public String registerUser(String login, String password, String name,
			String gender, String mail) throws MissingArgumentException, InvalidArgumentException, InvalidEmailException, DuplicatedLoginException {
		myMoney.registerUser(login, password, name, gender, mail);
		return null;
	}

	/**
	 * Retorna uma colecao com todas as contas de um dado usuario.
	 * @param login O login do usuario.
	 */
	public Long[] getAllAccountsIds(String login) {
		return myMoney.getAllAccountsIds(login).toArray(new Long[0]);
	}

	public String[] getAccountsLabels(String login, long[] accountsIds) throws PermissionDeniedException, AccountNotFoundException {
		String[] labels = new String[accountsIds.length];
		int i = 0;
		for (long accId : accountsIds) {
			labels[i++] = getAccountLabel(login, accId);
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

	/**
	 * Executa logout no sistema. Apos executar este metodo, qualquer operacao
	 * que o usuario deseje realizar serah invalida ate que ele volte a fazer
	 * login no sistema.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @param password
	 *            A senha do usuario.
	 * @throws InvalidArgumentException
	 *             Se login e/ou senha forem invalidos.
	 * @throws LoginUnregisteredException
	 *             Se o login nao tiver sido cadastrado no sistema.
	 * @throws PasswordMismatchException
	 *             Se a senha nao conferir com a cadastrada no sistema.
	 * @throws UserNotLoggedException
	 *             Se o usuario nao estiver logado no sistema.
	 */
	public String doLogoff(String login, String password)
			throws InvalidArgumentException, LoginUnregisteredException,
			PasswordMismatchException, UserNotLoggedException {
		myMoney.doLogout(login, password);
		return null;
	}

}
