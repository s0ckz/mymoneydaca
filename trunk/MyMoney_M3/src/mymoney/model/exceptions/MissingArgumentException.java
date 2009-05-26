package mymoney.model.exceptions;

/**
 * Excecao lancada quando ha chamada de metodo com argumentos vazios
 * 
 * @author Danilo Resende
 * @author Leandro Jose
 * @author Rodrigo Bruno
 * 
 */
public class MissingArgumentException extends MyMoneyException {

	private static final long serialVersionUID = 4760087469912578578L;

	/**
	 * Construtor de uma nova excecao.
	 * @param arg Argumento que falta.
	 */
	public MissingArgumentException(String arg) {
		super("Missing Argument: " + arg);
	}

	/**
	 * Construtor de uma nova excecao.
	 */
	public MissingArgumentException() {
		super("Missing Argument");
	}
}
