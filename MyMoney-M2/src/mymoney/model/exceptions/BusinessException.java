package mymoney.model.exceptions;

public class BusinessException extends MyMoneyException {

	private static final long serialVersionUID = -7465506009493730126L;

	public BusinessException(String error) {
		super("Business validation: " + error);
	}

}
