package mymoney.model.exceptions;

/**
 * Excecao lancada quando uma conta jah existe no sistema.
 */
public class DuplicatedAccountException extends MyMoneyException {

	private static final long serialVersionUID = 2547513262477931687L;

	public DuplicatedAccountException() {
		super("Account Already Exists");
	}

}
