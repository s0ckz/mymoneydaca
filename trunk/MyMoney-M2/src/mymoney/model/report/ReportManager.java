/**
 * 
 */
package mymoney.model.report;

import mymoney.model.exceptions.MissingArgumentException;

/**
 * @author Danilo de Sa
 * @author Rodrigo Bruno
 * @author Leandro Jose
 * 
 */
public interface ReportManager {

	/**
	 * Metodo que gera um relatorio de creditos
	 * 
	 * @param login
	 *            o login do usuario
	 * @param begin
	 *            a data de inicio do relatorio
	 * @param end
	 *            a data de fim do relatorio
	 * @param idAccount
	 *            o id da conta
	 * @return long que representa o codigo do relatorio
	 * @throws MissingArgumentException
	 */
	long generateReportCreditOperations(String login, String begin, String end,
			long idAccount) throws MissingArgumentException;

	/**
	 * Metodo que gera um relatorio de debitos
	 * 
	 * @param login
	 *            o login do usuario
	 * @param begin
	 *            a data de inicio do relatorio
	 * @param end
	 *            a data de fim do relatorio
	 * @param idAccount
	 *            o id da conta
	 * @return long que representa o codigo do relatorio
	 * @throws MissingArgumentException
	 */
	long generateReportDebtOperations(String login, String begin, String end,
			long idAccount) throws MissingArgumentException;

	/**
	 * Metodo que gera um relatorio de debitos
	 * 
	 * @param login
	 *            o login do usuario
	 * @param begin
	 *            a data de inicio do relatorio
	 * @param end
	 *            a data de fim do relatorio
	 * @param idAccount
	 *            o id da conta
	 * @param typeOperation
	 *            o tipo da oparacao
	 * @return long que representa o codigo do relatorio
	 * @throws MissingArgumentException
	 */
	long generateReportOperations(String login, String begin, String end,
			long idAccount, String typeOperation)
			throws MissingArgumentException;

	/**
	 * Metodo que retorna os relatorios gerados por um usuario
	 * 
	 * @param login
	 *            o login do usuario
	 * @return inteiro que representa o numero de relatorios gerados
	 */
	int getReports(String login);

	/**
	 * Metodo que remove todos os relatorio gerados por um usuario
	 * 
	 * @param login
	 *            o login do usuario
	 */
	void removeReports(String login);
}
