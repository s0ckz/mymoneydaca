package mymoney.model.external.exporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import mymoney.model.account.AccountManager;
import mymoney.model.account.AccountManagerImpl;
import mymoney.model.exceptions.AccountNotFoundException;
import mymoney.model.exceptions.PermissionDeniedException;

/**
 * Prove funcionalidade de exportar as operacoes financeiras de uma conta 
 * do MyMoney.
 * <br>
 * Essa classe eh independente de formato de arquivo. Para adicionar um novo
 * exportador para um novo tipo de arquivo basta estender essa classe e 
 * implementar o metodo: operationToString.
 */
public abstract class Exporter {

	protected AccountManager accountManager;
	
	protected static final String NEW_LINE = System.getProperty("line.separator");


	/**
	 * Construtor default.
	 */
	protected Exporter() {
		accountManager = new AccountManagerImpl();
	}
	
	/**
	 * Exporta as operacoes financeiras de uma conta para um dado arquivo.
	 * 
	 * @param login O login do usuario dono da conta.
	 * @param accId O id da conta.
	 * @param pathToFile O caminho para o arquivo.
	 * @throws IOException Se houver algum erro de I/O.
	 * @throws PermissionDeniedException Se o usuario nao for dono da conta requisitada.
	 * @throws AccountNotFoundException Se a conta nao tiver sido cadastrada no sistema.
	 */
	public void export(String login, long accId, String pathToFile) throws IOException, PermissionDeniedException, AccountNotFoundException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile));
		
		Collection<Long> ids = accountManager.getAllOperations(login,accId);
		StringBuilder content = new StringBuilder();
		for (long opId : ids) {
			String type = accountManager.getOperationType(opId);
			String way = accountManager.getOperationWay(opId);
			double amount = accountManager.getOperationAmount(opId);
			content.append(operationToString(accId, type, way, amount));
		}
		
		writer.write(content.toString());
		writer.close();

	}

	/**
	 * Produz um <code>String</code> que representa uma operacao financeira.
	 * 
	 * Cada formato de arquivo tem uma forma de gerar esse string.
	 * 
	 * @param accId O id da conta.
	 * @param type O tipo da operacao. Pode ser <i>debit</i> ou <i>credit</i>.
	 * @param way O modo de pagamento.
	 * @param amount O valor da operacao.
	 * @return Retorna um string que representa a operacao financeira em questao. 
	 */
	protected abstract String operationToString(long accId, String type, String way, double amount);	

}
