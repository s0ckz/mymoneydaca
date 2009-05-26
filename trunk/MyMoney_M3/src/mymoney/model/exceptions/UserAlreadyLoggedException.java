package mymoney.model.exceptions;

/**
 * Excecao lancada quando o usuario que esta tentando fazer login ja esta logado
 * no sistema
 * 
 * @author Danilo Resende
 * @author Leandro Jose
 * @author Rodrigo Bruno
 * 
 */
public class UserAlreadyLoggedException extends MyMoneyException {

	private static final long serialVersionUID = -6177097177239719010L;

	/**
	 * Construtor de uma nova excecao.
	 */
	public UserAlreadyLoggedException() {
		super("User is already logged in");
	}

}
