package mymoney.model.exceptions;

public class MisunderstandingFileContent extends MyMoneyException {

	private static final long serialVersionUID = -2964061109622046016L;

	public MisunderstandingFileContent() {
		super("File content cannot be understood");
	}

}
