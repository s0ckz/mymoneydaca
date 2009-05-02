/**
 * 
 */
package mymoney.model.commitment;

import mymoney.model.exceptions.CommitmentException;
import mymoney.model.exceptions.MissingArgumentException;

/**
 * @author Danilo de Sa
 * @author Leandro Jose
 * @author Rodrigo Bruno
 * 
 */
public interface CommitmentManager {

	/**
	 * Metodo que adiciona um compromisso
	 * 
	 * @param login
	 *            o login do dono do compromisso
	 * @param label
	 *            o label do compromisso
	 * @param date
	 *            a data do compromisso
	 * @param amount
	 *            o montante do compromisso
	 * @param type
	 *            o tipo do compromisso
	 * @param frequency
	 *            a frequencia do compromisso
	 * @return code the code of the commitment added
	 * @throws MissingArgumentException
	 */
	long addCommitment(String login, String label, String date, double amount,
			String type, String frequency) throws MissingArgumentException;

	/**
	 * Metodo que retorna o label de um compromisso
	 * 
	 * @param login
	 *            o login do dono do compromisso
	 * @param id
	 *            a id do compromisso
	 * @return label o label do compromisso
	 * @throws CommitmentException
	 */
	public String getCommitmentLabel(String login, long id)
			throws CommitmentException;

	/**
	 * Metodo que retorna a data de um compromisso
	 * 
	 * @param login
	 *            O login do dono do compromisso
	 * @param id
	 *            o id do compromisso
	 * @return date a data do compromisso
	 * @throws CommitmentException
	 */
	public String getCommitmentDate(String login, long id)
			throws CommitmentException;

	/**
	 * Retorna o montante do compromisso
	 * 
	 * @param login
	 *            o login do usuario dono do compromisso
	 * @param id
	 *            a id do compromisso
	 * @return amount o montante do comprimisso
	 * @throws CommitmentException
	 */
	public double getCommitmentAmount(String login, long id)
			throws CommitmentException;

	/**
	 * Retorna o tipo do compromisso
	 * 
	 * @param login
	 *            o login do dono do compromisso
	 * @param id
	 *            o id do compromisso
	 * 
	 * @return type o tipo do compromisso
	 * @throws CommitmentException
	 */
	public String getCommitmentType(String login, long id)
			throws CommitmentException;

	/**
	 * Retorna a frequencia do comprimisso
	 * 
	 * @param login
	 *            o login do dono do compromisso
	 * @param id
	 *            o id do compromisso
	 * @return frequency a frequencia do compromisso
	 * @throws CommitmentException
	 */
	public String getCommitmentFrequency(String login, long id)
			throws CommitmentException;

	/**
	 * Retorna o numero de compromissos de um usuario
	 * 
	 * @param login
	 *            o login do usuario
	 * 
	 * @return um inteiro que representa o numero de compromissos do usuario
	 */
	public int numberOfCommitments(String login);

	/**
	 * Metodo que remove um comentario
	 * 
	 * @param login
	 *            o login do usuario a que pertence o compromisso
	 * @param id
	 *            o id do compromisso
	 * 
	 * @throws CommitmentException
	 */
	public void removeCommitment(String login, long id)
			throws CommitmentException;

	/**
	 * Metodo que remove todos os comentario de um usuario
	 * 
	 * @param login
	 *            o login do usuario a que pertence o compromisso
	 * @param id
	 *            o id do compromisso
	 * 
	 * @throws CommitmentException
	 */
	public void removeCommitment(String login);

}
