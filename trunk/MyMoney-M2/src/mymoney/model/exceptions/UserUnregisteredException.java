package mymoney.model.exceptions;

public class UserUnregisteredException extends Exception {

	private static final long serialVersionUID = -1332936091956433477L;

	public UserUnregisteredException() {
		super("User unregistered");
	}

}
