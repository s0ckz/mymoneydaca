package mymoney.model.exceptions;

public class UserNotLoggedException extends MyMoneyException {

	private static final long serialVersionUID = -7671577116777015368L;

	public UserNotLoggedException() {
		super("User is not logged in");
	}

}
