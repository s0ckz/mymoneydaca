package mymoney.model.exceptions;

/**
 * Excecao lancada quando o usuario nao estah logado.
 */
public class UserNotLoggedException extends MyMoneyException {

	private static final long serialVersionUID = -7671577116777015368L;

	/**
	 * Construtor de uma nova excecao.
	 */
	public UserNotLoggedException() {
		super("User is not logged in");
	}

}
