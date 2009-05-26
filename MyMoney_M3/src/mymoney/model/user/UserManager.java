package mymoney.model.user;

import mymoney.model.exceptions.DuplicatedLoginException;
import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.UserUnregisteredException;

/**
 * Interface com operacoes sobre a entidade usuario (<code>User</code>)do MyMoney.
 * Permite operacoes de cadastro/atualizacao/remocao/recuperacao de informacoes sobre a entidade <code>User</code>.
 *
 */
public interface UserManager {

	/**
	 * Realiza cadastra de um novo usuario no sistema.
	 * <br>
	 * Para que o cadastro seja efetuado com sucesso, o parametro login deve ser uma cadeia de caracteres 
	 * de tamanho maior ou igual a 4 contendo apenas caracteres que podem ser alfa numericos, underline, hifen ou ponto.
	 * 
	 * O e-mail deve ser vazio ou respeitar o seguinte padrao: $usuario@$dominio.$extensao, em que $usuario, $dominio 
	 * devem ser uma cadeira de caracteres qualquer de tamanho maior ou igual a 1 e $extensao deve ser uma cadeia de letras minusculas. 
	 * 
	 * @param login O login do usuario.
	 * @param name O nome do usuario.
	 * @param gender O sexo do usuario.
	 * @param eMail O e-mail do usuario.
	 * @throws MissingArgumentException Se login e/ou nome forem nulos ou vazios.
	 * @throws InvalidArgumentException Se login for invalido.
	 * @throws InvalidEmailException Se o e-mail for invalido.
	 * @throws DuplicatedLoginException Se ja houver um login identico cadastrado.
	 */
	public void register(String login, String name,
			String gender, String eMail) throws MissingArgumentException, InvalidArgumentException, InvalidEmailException, DuplicatedLoginException;

	/**
	 * Recupera o nome de um usuario.
	 * 
	 * @param login O login do usuario.
	 * @return O nome do usuario.
	 * @throws UserUnregisteredException Se o usuario nao tiver sido cadastrado no sistema.
	 */
	public String getUserName(String login) throws UserUnregisteredException;

	/**
	 * Recupera o sexo do usuario.
	 * @param login O login do usuario.
	 * @return O sexo do usuario.
	 * @throws UserUnregisteredException Se o usuario nao tiver sido cadastrado no sistema.
	 */
	public String getUserGender(String login) throws UserUnregisteredException;

	/**
	 * Recupera o e-mail do usuario.
	 * @param login O login do usuario.
	 * @return O e-mail do usuario.
	 * @throws UserUnregisteredException Se o usuario nao tiver sido cadastrado no sistema.
	 */
	public String getUserEmail(String login) throws UserUnregisteredException;

	/**
	 * Remove um usuario do sistema.
	 * @param login O login do usuario a ser removido.
	 * @throws UserUnregisteredException Se o usuario nao tiver sido cadastrado no sistema.
	 */
	public void removeUser(String login) throws UserUnregisteredException;

	/**
	 * Atualiza informacoes de um usuario no sistema.
	 * <br>
	 * O atributo login nao pode ser atualizado.
	 * 
	 * @param login O login do usuario.
	 * @param name O novo nome do usuario.
	 * @param gender O novo sexo do usuario.
	 * @param email O novo e-mail do usuario.
	 * @throws UserUnregisteredException Se o usuario nao tiver sido cadastrado no sistema.
	 * @throws MissingArgumentException Se login e/ou nome forem nulos ou vazios.
	 * @throws InvalidArgumentException Se login for invalido.
	 * @throws InvalidEmailException Se o e-mail for invalido.
	 */
	public void updateUser(String login, String name, String gender, String email) throws UserUnregisteredException, MissingArgumentException, InvalidEmailException, InvalidArgumentException;
	
}
