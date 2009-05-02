package mymoney.model.exceptions;

/**
 * Excecao que eh lancada quando algum erro relativo
 * a negocios aconteca, como valores negativos, etc.
 */
public class BusinessException extends MyMoneyException {

	private static final long serialVersionUID = -7465506009493730126L;

	/**
	 * Construtor de uma nova excecao.
	 * @param error Mensagem de erro.
	 */
	public BusinessException(String error) {
		super("Business validation: " + error);
	}

}
