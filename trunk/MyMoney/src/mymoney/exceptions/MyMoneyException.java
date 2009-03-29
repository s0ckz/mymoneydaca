package mymoney.exceptions;

@SuppressWarnings("serial")
public class MyMoneyException extends Exception {

	public MyMoneyException() {
		super();
	}
	
	public MyMoneyException(String msg) {
		super(msg);
	}
	
	public MyMoneyException(Throwable t) {
		super(t);
	}

}
