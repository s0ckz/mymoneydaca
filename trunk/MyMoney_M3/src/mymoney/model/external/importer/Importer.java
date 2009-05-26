package mymoney.model.external.importer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mymoney.model.account.AccountManager;
import mymoney.model.account.AccountManagerImpl;
import mymoney.model.exceptions.AccountNotFoundException;
import mymoney.model.exceptions.BusinessException;
import mymoney.model.exceptions.InvalidDateException;
import mymoney.model.exceptions.MisunderstandingFileContentException;
import mymoney.model.exceptions.PermissionDeniedException;
import mymoney.model.util.DateUtils;

/**
 * Prove funcionalidade de importar as operacoes financeiras de uma conta 
 * do MyMoney.
 * <br>
 * Essa classe eh independente de formato de arquivo. Para adicionar um novo
 * importador para um novo tipo de arquivo basta estender essa classe e 
 * implementar o metodo: makeOperationPattern.
 */
public abstract class Importer {

	protected AccountManager accountManager;
	
	protected static final String NEW_LINE = System.getProperty("line.separator");
	
	protected static final int ACC_ID_GROUP = 1;

	protected static final int TYPE_GROUP = 2;

	protected static final int WAY_GROUP = 3;

	protected static final int AMOUNT_GROUP = 4;

	protected static final int DATE_GROUP = 5;

	protected static final int NUM_GROUPS = 5;
	
	/**
	 * Construtor Default.
	 */
	protected Importer() {
		accountManager = new AccountManagerImpl();
	}

	/**
	 * Importa um arquivo contendo operacoes financeiras do MyMoney.
	 * 
	 * @param login O login do usuario dono da conta.
	 * @param fileContent O conteudo do arquivo a ser importado.
	 * @return Um vetor contendo os ids das operacoes importadas.
	 * 
	 * @throws MisunderstandingFileContentException Se o conteudo do arquivo nao puder ser entendido pelo importador.
	 * @throws BusinessException Se alguma regra de negocio do MyMoney for violada.
	 * @throws PermissionDeniedException Se o usuario nao for o dono da conta requisitada.
	 * @throws AccountNotFoundException Se a conta nao tiver sido cadastrada no sistema.
	 */
	public long[] submitBankOperations(String login, String fileContent) throws MisunderstandingFileContentException, BusinessException,
			PermissionDeniedException, AccountNotFoundException {
		Pattern p = makeOperationPattern();
		String[] operations = fileContent.split(NEW_LINE);
		long[] ids = new long[operations.length];
		int index = 0;
		for (String op : operations) {
			Matcher m = p.matcher(op);
			m.matches();
			if (!m.matches() || m.groupCount() != NUM_GROUPS) {
				throw new MisunderstandingFileContentException();
			}
			try {
				long accId = Long.parseLong(m.group(ACC_ID_GROUP).replace("\"",
						""));
				double amount = Double.parseDouble(m.group(AMOUNT_GROUP)
						.replace("\"", ""));
				ids[index++] = accountManager.addOperation(login, accId, m
						.group(TYPE_GROUP).replace("\"", ""), m
						.group(WAY_GROUP).replace("\"", ""), amount,
						DateUtils.createDate(m.group(DATE_GROUP).replace("\"", "")));
			} catch (InvalidDateException e) {
				throw new MisunderstandingFileContentException();
			} catch (NumberFormatException e) {
				throw new MisunderstandingFileContentException();
			} catch (BusinessException e) {
				throw e;
			} catch (PermissionDeniedException e) {
				throw e;
			} catch (AccountNotFoundException e) {
				throw e;
			}
		}
		return ids;
	}

	/**
	 * Produz uma expressao regular que corresponde a uma operacao financeira, no formato de arquivo
	 * do importador que implementa esse metodo. 
	 * <br><br>
	 * Essa expressao regular sera usada para a leitura e interpretacao do arquivo. 
	 * Ela deve conter 4 grupos de capturacao que serao lidos na seguinte ordem:
	 * <br>idDaConta, tipoDaOperacao, modoDePagamento, valor. 
	 * 
	 * @return um objeto <code>Pattern</code> contendo a expressao regular gerada.
	 */
	protected abstract Pattern makeOperationPattern();
	
}
