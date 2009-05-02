package mymoney.model.exceptions;

/**
 * Excecao lancada quando uma operacao nao eh encontrada.
 */
public class UnknownOperationException extends MyMoneyException {

	private static final long serialVersionUID = 8144729152219535519L;

	public UnknownOperationException() {
		super("Unknown operation");
	}

}
