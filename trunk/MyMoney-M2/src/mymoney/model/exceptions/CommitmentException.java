/**
 * 
 */
package mymoney.model.exceptions;

/**
 * 
 * Excecao que eh lancada quando ocorre algum evento inesperado com relacao a um
 * comprimisso
 * 
 * @author Danilo Resende
 * @author Leandro Jose
 * @author Rodrigo Bruno
 * 
 */
public class CommitmentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6094210601700324028L;

	/**
	 * Construtor de uma nova excecao.
	 * @param commitmentCode Codigo do compromisso.
	 */
	public CommitmentException(long commitmentCode) {
		super("Commitment " + commitmentCode + " not found.");
	}

	/**
	 * Construtor de uma nova excecao.
	 * @param message Mensagem de erro.
	 */
	public CommitmentException(String message) {
		super(message);
	}

}
