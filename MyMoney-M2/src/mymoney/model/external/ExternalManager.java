package mymoney.model.external;

import java.io.IOException;

import mymoney.model.exceptions.AccountNotFoundException;
import mymoney.model.exceptions.BusinessException;
import mymoney.model.exceptions.MisunderstandingFileContentException;
import mymoney.model.exceptions.PermissionDeniedException;

/**
 * Prove operacoes de comunicacao com o mundo externo ao MyMoney.
 * Atraves dessa interface, pode-se importar/exportar as operacoes financeiras
 * realizadas pelo usuario.
 */
public interface ExternalManager {

	/**
	 * Importa as operacoes financeiras em formato .csv para a conta
	 * de um dado usuario.
	 * <br><br>
	 * O arquivo .csv deve ter no seguinte padrao:
	 * <br>"$idDaConta","$tipo","$modo","$valor" 
	 *
	 * <br><br>Exemplo de arquivo:
	 * <br>"1","credit","cash","300.0
	 * <br>"1","debit","cash","2000.0
	 * 
	 * @param login O login do usuario.
	 * @param fileContent O conteudo do arquivo.
	 * @return Retorna um vetor com os identificadores das operacoes adicionadas.
	 * 
	 * @throws BusinessException Se alguma regra de negocio do MyMoney for violada.
	 * @throws PermissionDeniedException Se o usuario nao tiver a permissao necessaria para executar as operacoes financeiras.
	 * @throws AccountNotFoundException Se algum conta especificada no arquivo nao existir no sistema. 
	 * @throws MisunderstandingFileContentException Se houver erro no conteudo do arquivo.
	 */
	long[] submitBankOperationsCSV(String login, String fileContent)
			throws BusinessException, PermissionDeniedException,
			AccountNotFoundException, MisunderstandingFileContentException;

	/**
	 * Importa as operacoes financeiras em formato .txt para a conta
	 * de um dado usuario.
	 * <br><br>
	 * O arquivo .txt deve ter no seguinte padrao:
	 * <br>$idDaConta	$tipo	$modo	$valor 
	 *
	 * <br><br>Exemplo de arquivo:
	 * <br>1	credit	cash	300.0
	 * <br>1	debit	cash	2000.0
	 * 
	 * @param login O login do usuario.
	 * @param fileContent O conteudo do arquivo.
	 * @return Retorna um vetor com os identificadores das operacoes adicionadas.
	 * 
	 * @throws BusinessException Se alguma regra de negocio do MyMoney for violada.
	 * @throws PermissionDeniedException Se o usuario nao tiver a permissao necessaria para executar as operacoes financeiras.
	 * @throws AccountNotFoundException Se algum conta especificada no arquivo nao existir no sistema. 
	 * @throws MisunderstandingFileContentException Se houver erro no conteudo do arquivo.
	 */
	long[] submitBankOperationsTXT(String login, String fileContent)
			throws MisunderstandingFileContentException, BusinessException,
			PermissionDeniedException, AccountNotFoundException;

	/**
	 * Exporta as operacoes financeiras de uma conta de um usuario para um arquivo em formato .csv.
	 * <br><br>
	 * O arquivo .csv tera o seguinte padrao:
	 * <br>"$idDaConta","$tipo","$modo","$valor" 
	 *
	 * <br><br>Exemplo de arquivo:
	 * <br>"1","credit","cash","300.0
	 * <br>"1","debit","cash","2000.0
	 * 
	 * @param login O login do usuario.
	 * @param accId O id da conta.
	 * @param pathToFile O caminho para o arquivo onde sera gravado.
	 * @throws IOException Se ocorrer algum problema de I/O.
	 * @throws PermissionDeniedException Se o usuario nao tiver a permissao necessaria para executar as operacoes financeiras.
	 * @throws AccountNotFoundException Se algum conta especificada no arquivo nao existir no sistema. 
	 */
	void exportBankOperationsCSV(String login, long accId, String pathToFile)
			throws IOException, PermissionDeniedException, AccountNotFoundException;

	/**
	 * Exporta as operacoes financeiras de uma conta de um usuario para um arquivo em formato .txt.
	 * <br><br>
	 * O arquivo .txt deve ter no seguinte padrao:
	 * <br>$idDaConta	$tipo	$modo	$valor 
	 *
	 * <br><br>Exemplo de arquivo:
	 * <br>1	credit	cash	300.0
	 * <br>1	debit	cash	2000.0
	 * 
	 * @param login O login do usuario.
	 * @param accId O id da conta.
	 * @param pathToFile O caminho para o arquivo onde sera gravado.
	 * @throws IOException Se ocorrer algum problema de I/O.
	 * @throws PermissionDeniedException Se o usuario nao tiver a permissao necessaria para executar as operacoes financeiras.
	 * @throws AccountNotFoundException Se algum conta especificada no arquivo nao existir no sistema. 
	 */
	void exportBankOperationsTXT(String login, long accId, String pathToFile)
			throws IOException, PermissionDeniedException, AccountNotFoundException;

}
