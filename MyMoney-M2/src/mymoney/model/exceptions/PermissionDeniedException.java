package mymoney.model.exceptions;

public class PermissionDeniedException extends MyMoneyException {

	private static final long serialVersionUID = -7465506509493730126L;

	public PermissionDeniedException(String error) {
		super("Permission denied: " + error);
	}

}
