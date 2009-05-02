package mymoney.model.exceptions;

/**
 * Escecao lancada quando ha chamada de metodo com argumentos vazios
 * 
 * @author Danilo Resende
 * @author Leandro Jose
 * @author Rodrigo Bruno
 * 
 */
public class MissingArgumentException extends MyMoneyException {

	private static final long serialVersionUID = 4760087469912578578L;

	public MissingArgumentException(String arg) {
		super("Missing Argument: " + arg);
	}

	public MissingArgumentException() {
		super("Missing Argument");
	}
}
