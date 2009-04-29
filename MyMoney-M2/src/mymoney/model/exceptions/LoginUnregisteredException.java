package mymoney.model.exceptions;

public class LoginUnregisteredException extends MyMoneyException {

	private static final long serialVersionUID = -2297487823651798288L;

	public LoginUnregisteredException() {
		super("Login unregistered");
	}
	
}
