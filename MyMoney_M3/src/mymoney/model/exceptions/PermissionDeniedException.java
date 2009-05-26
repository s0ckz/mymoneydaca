package mymoney.model.exceptions;

/**
 * Excecao lancada quando o usuario nao tem permissao para fazer determinada acao.
 */
public class PermissionDeniedException extends MyMoneyException {

	private static final long serialVersionUID = -7465506509493730126L;

	/**
	 * Construtor de uma nova excecao.
	 */
	public PermissionDeniedException(String error) {
		super("Permission denied: " + error);
	}

}
