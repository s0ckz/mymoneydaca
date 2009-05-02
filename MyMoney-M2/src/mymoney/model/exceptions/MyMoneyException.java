package mymoney.model.exceptions;

/**
 * Excecao lancada quando algum evento inesperado da logica do sistema e
 * capturado
 * 
 * @author Danilo Resende
 * @author Leandro Jose
 * @author Rodrigo Bruno
 * 
 */
public class MyMoneyException extends Exception {

	public MyMoneyException(String msg) {
		super(msg);
	}

	public MyMoneyException() {
		super();
	}

	private static final long serialVersionUID = -4216569308095845664L;

}
