package mymoney.model.exceptions;

public class MissingArgumentException extends MyMoneyException {

	private static final long serialVersionUID = 4760087469912578578L;

	public MissingArgumentException(String arg) {
		super("Missing Argument: " + arg);
	}

	public MissingArgumentException() {
		super("Missing Argument");
	}
}
