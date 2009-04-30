/**
 * 
 */
package mymoney.model.exceptions;

/**
 * @author Rodrigo
 * 
 */
public class CommitmentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6094210601700324028L;

	public CommitmentException(long commitmentCode) {
		super("Commitment " + commitmentCode + " not found.");
	}

	public CommitmentException(String message) {
		super(message);
	}

	
}
