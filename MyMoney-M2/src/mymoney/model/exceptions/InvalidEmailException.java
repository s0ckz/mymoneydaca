package mymoney.model.exceptions;

/*
 * Excecao criada somente por que nos testes o parametro email eh diferenciado de outros.
 *
 */
public class InvalidEmailException extends MyMoneyException {

	private static final long serialVersionUID = -9184704081827666850L;

	public InvalidEmailException() {
		super("Invalid e-mail");
	}

	public InvalidEmailException(String msg) {
		super(msg);
	}

	
}
