package mymoney.model.exceptions;

/**
 * Excecao que e lancada quando uma conta nao eh encontrada
 * no sistema.
 */
public class AccountNotFoundException extends MyMoneyException {

	private static final long serialVersionUID = -756106789994451835L;

	public AccountNotFoundException() {
		super("Account not found");
	}
}
