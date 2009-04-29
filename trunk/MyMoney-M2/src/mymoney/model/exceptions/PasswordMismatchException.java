package mymoney.model.exceptions;

public class PasswordMismatchException extends MyMoneyException {

	private static final long serialVersionUID = 7912396531558097592L;

	public PasswordMismatchException() {
		super("Wrong password");
	}

	
}
