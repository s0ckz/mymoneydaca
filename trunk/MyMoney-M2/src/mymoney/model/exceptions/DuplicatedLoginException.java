package mymoney.model.exceptions;

/**
 * Excecao lancada quando um login jah existe no sistema.
 */
public class DuplicatedLoginException extends MyMoneyException {

	private static final long serialVersionUID = 5737630303023645349L;

	public DuplicatedLoginException() {
		super("Login already registered");
	}

}
