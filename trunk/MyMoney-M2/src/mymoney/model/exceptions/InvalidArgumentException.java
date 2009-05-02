package mymoney.model.exceptions;

/**
 * Excecao lancada quando um argumento invalido eh passado
 * 
 * @author Danilo Resende
 * @author Leandro Jose
 * @author Rodrigo Bruno
 * 
 */
public class InvalidArgumentException extends MyMoneyException {

	private static final long serialVersionUID = -5061543715389961172L;

	public InvalidArgumentException() {
		super("Invalid Argument");
	}

	public InvalidArgumentException(String arg) {
		super("Invalid Argument: " + arg);
	}

}
