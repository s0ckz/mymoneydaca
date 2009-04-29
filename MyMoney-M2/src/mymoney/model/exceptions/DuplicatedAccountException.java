package mymoney.model.exceptions;

public class DuplicatedAccountException extends MyMoneyException {

	private static final long serialVersionUID = 2547513262477931687L;

	public DuplicatedAccountException() {
		super("Account Already Exists");
	}

}
