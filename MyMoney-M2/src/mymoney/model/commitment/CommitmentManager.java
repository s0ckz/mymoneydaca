/**
 * 
 */
package mymoney.model.commitment;

import mymoney.model.exceptions.CommitmentException;
import mymoney.model.exceptions.MissingArgumentException;

/**
 * @author Danilo de Sá
 * @author Leandro José
 * @author Rodrigo Bruno
 * 
 */
public interface CommitmentManager {

	/**
	 * This method adds a commitment
	 * 
	 * @param login
	 *            the login of the commitment owner
	 * @param label
	 *            the commitment´s label
	 * @param date
	 *            the commitment´s date
	 * @param amount
	 *            the commitment´s amount
	 * @param type
	 *            the commitment´s type
	 * @param frequency
	 *            the commitment´s frequency
	 * @return code the code of the commitment added
	 * @throws MissingArgumentException
	 */
	long addCommitment(String login, String label, String date, double amount,
			String type, String frequency) throws MissingArgumentException;

	/**
	 * Return the label of the commitment
	 * 
	 * @param login
	 *            the login of the commitment owner
	 * @param id
	 *            the commitment´s id
	 * @return label
	 * @throws CommitmentException
	 */
	public String getCommitmentLabel(String login, long id)
			throws CommitmentException;

	/**
	 * Return the date of the commitment
	 * 
	 * @param login
	 *            the login of the commitment owner
	 * @param id
	 *            the commitment´s id
	 * @return date the commitment´s date
	 * @throws CommitmentException
	 */
	public String getCommitmentDate(String login, long id)
			throws CommitmentException;

	/**
	 * Return the amount of the commitment
	 * 
	 * @param login
	 *            the login of the commitment owner
	 * @param id
	 *            the commitment´s id
	 * @return amount the commitment´s amount
	 * @throws CommitmentException
	 */
	public double getCommitmentAmount(String login, long id)
			throws CommitmentException;

	/**
	 * Return the type of the commitment
	 * 
	 * @param login
	 * @param id
	 *            the commitment´s id
	 * @return type the commitment´s type
	 * @throws CommitmentException
	 */
	public String getCommitmentType(String login, long id)
			throws CommitmentException;

	/**
	 * Return the frequency of the commitment
	 * 
	 * @param login
	 * @param id
	 *            the commitment´s id
	 * @return frequency the commitment´s frequency
	 * @throws CommitmentException
	 */
	public String getCommitmentFrequency(String login, long id)
			throws CommitmentException;

	/**
	 * Returns the user´s commitments number
	 * 
	 * @param login
	 *            the user´s login
	 * 
	 * @return a long that represents the number of commitments
	 */
	public long numberOfCommitments(String login);

	/**
	 * Remove a commitment
	 * 
	 * @param login
	 *            the login of the owner
	 * @param id
	 *            the id of the commitment
	 *        
	 * @throws CommitmentException
	 */
	public void removeCommitment(String login, long id)
			throws CommitmentException;

}
