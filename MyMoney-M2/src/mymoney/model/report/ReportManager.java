/**
 * 
 */
package mymoney.model.report;

import java.util.Collection;

import mymoney.model.account.Operation;
import mymoney.model.exceptions.AccountNotFoundException;
import mymoney.model.exceptions.InvalidDateException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.PermissionDeniedException;

/**
 * @author Danilo de Sa
 * @author Rodrigo Bruno
 * @author Leandro Jose
 * 
 */
public interface ReportManager {

	

	
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
	 * @throws InvalidDateException 
	 * @throws AccountNotFoundException 
	 * @throws PermissionDeniedException 
	 */
	Collection<Long> generateReport(String login, String begin, String end,
			long idAccount, String typeOperation, Collection<Operation> operacoes)
			throws MissingArgumentException, PermissionDeniedException, AccountNotFoundException, InvalidDateException;

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
