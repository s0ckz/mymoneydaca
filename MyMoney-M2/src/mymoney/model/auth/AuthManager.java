package mymoney.model.auth;

import mymoney.model.exceptions.DuplicatedLoginException;
import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.LoginUnregisteredException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.PasswordMismatchException;
import mymoney.model.exceptions.UserAlreadyLoggedException;
import mymoney.model.exceptions.UserNotLoggedException;

/**
 * Interface para operacoes envolvendo autenticacao de usuarios. Permite que
 * um login seja cadastrado no sistema e que um usuario efetue login/logout.
 *
 */
public interface AuthManager {

	/**
	 * Cadastra um novo login no sistema.
	 * <br>
	 * Para que o cadastro seja efetuado com sucesso, os parametros login/senha devem ser uma cadeia de caracteres 
	 * de tamanho maior ou igual a 4 contendo apenas caracteres que podem ser alfa numericos, underline, hifen ou ponto.
	 * <br>
	 * Cada entidade Auth deve possuir um login unico que o diferencie dos demais. 
	 * 
	 * @param login O login do usuario.
	 * @param password A senha do usuario.
	 * @throws InvalidArgumentException Se login e/ou senha forem invalidos.
	 * @throws MissingArgumentException Se login e/ou senha forem nulos ou vazios. 
	 * @throws DuplicatedLoginException Se ja houver um login identico cadastrado.
	 */
	void register(String login, String password) throws InvalidArgumentException, MissingArgumentException, DuplicatedLoginException;

	/**
	 * Executa login no sistema. Essa operacao permite que o usuario possa usar todas 
	 * as funcionalidades do MyMoney, por exemplo: cadastrar uma conta, adicionar uma operacao financeira, etc.
	 * 
	 * @param login O login do usuario.
	 * @param password A senha do usuario.
	 * @throws PasswordMismatchException Se a senha nao conferir com a cadastrada no sistema.
	 * @throws InvalidArgumentException Se login e/ou senha forem invalidos.
	 * @throws LoginUnregisteredException Se o login nao tiver sido cadastrado no sistema.
	 * @throws UserAlreadyLoggedException Se o usuario ja estiver logado no sistema.
	 */
	void doLogin(String login, String password) throws PasswordMismatchException, InvalidArgumentException, LoginUnregisteredException, UserAlreadyLoggedException;

	/**
	 * Retorna <code>true</code> se o usuario estiver logado no sistema, caso contrario, retorna <code>false</code>.
	 * 
	 * @param login O login do usuario.
	 * @return Se o usuario estiver logado. 
	 * @throws LoginUnregisteredException Se o login nao tiver sido cadastrado no sistema
	 */
	boolean isLogged(String login) throws LoginUnregisteredException;

	/**
	 * Executa logout no sistema. Apos executar este metodo, qualquer operacao que o usuario deseje realizar serah
	 * invalida ate que ele volte a fazer login no sistema.
	 * 
	 * @param login O login do usuario.
	 * @param password A senha do usuario.
	 * @throws InvalidArgumentException Se login e/ou senha forem invalidos.
	 * @throws LoginUnregisteredException Se o login nao tiver sido cadastrado no sistema.
	 * @throws PasswordMismatchException Se a senha nao conferir com a cadastrada no sistema.
	 * @throws UserNotLoggedException Se o usuario nao estiver logado no sistema.
	 */
	void doLogout(String login, String password) throws InvalidArgumentException, LoginUnregisteredException, PasswordMismatchException, UserNotLoggedException;

	/**
	 * Remove uma entidade Auth do sistema.
	 * 
	 * @param login O login do usuario.
	 * @throws LoginUnregisteredException Se o login nao tiver sido cadastrado no sistema.
	 */
	void remove(String login) throws LoginUnregisteredException;

}
