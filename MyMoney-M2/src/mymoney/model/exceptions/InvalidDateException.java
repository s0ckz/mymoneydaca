package mymoney.model.exceptions;

import mymoney.model.util.DateUtils;

/**
 * Excecao lancada quando o formato da data eh invalido.
 * @see DateUtils
 */
public class InvalidDateException extends MyMoneyException {

	private static final long serialVersionUID = -2964061109622046016L;

	/**
	 * Construtor de uma nova excecao.
	 */
	public InvalidDateException() {
		super("The date format is invalid");
	}

	
	/**
	 * Construtor de uma nova excecao.
	 */
	public InvalidDateException(String message) {
		super(message);
	}
}
