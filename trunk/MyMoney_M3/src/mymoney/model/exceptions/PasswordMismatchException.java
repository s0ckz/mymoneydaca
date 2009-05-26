package mymoney.model.exceptions;

/**
 * Excecao lancada quando o password passado como entrada nao confere com o
 * cadastrado no sistema
 * 
 * @author Danilo Resende
 * @author Leandro Jose
 * @author Rodrigo Bruno
 * 
 */
public class PasswordMismatchException extends MyMoneyException {

	private static final long serialVersionUID = 7912396531558097592L;

	/**
	 * Construtor de uma nova excecao.
	 */
	public PasswordMismatchException() {
		super("Wrong password");
	}

}
