package mymoney.model.exceptions;

public class AccountNotFoundException extends MyMoneyException {

	private static final long serialVersionUID = -756106789994451835L;

	public AccountNotFoundException() {
		super("Account not found");
	}
}
