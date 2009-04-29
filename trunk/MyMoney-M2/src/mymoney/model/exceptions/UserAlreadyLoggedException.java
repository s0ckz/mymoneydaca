package mymoney.model.exceptions;

public class UserAlreadyLoggedException extends MyMoneyException {

	private static final long serialVersionUID = -6177097177239719010L;

	public UserAlreadyLoggedException() {
		super("User is already logged in");
	}

}
