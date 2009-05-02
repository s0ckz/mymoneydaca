package mymoney.model.exceptions;

/**
 * Excecao lancada quando o formato de um arquivo eh invalido.
 */
public class MisunderstandingFileContent extends MyMoneyException {

	private static final long serialVersionUID = -2964061109622046016L;

	/**
	 * Construtor de uma nova excecao.
	 */
	public MisunderstandingFileContent() {
		super("File content cannot be understood");
	}

}
