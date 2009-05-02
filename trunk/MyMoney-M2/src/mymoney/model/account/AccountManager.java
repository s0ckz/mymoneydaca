package mymoney.model.account;

import java.util.Collection;

import mymoney.model.exceptions.AccountNotFoundException;
import mymoney.model.exceptions.BusinessException;
import mymoney.model.exceptions.DuplicatedAccountException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.PermissionDeniedException;
import mymoney.model.exceptions.UnknownOperationException;

/**
 * Interface que representa um gerenciador de contas, podendo criar novas contas, remover existentes,
 * criar novas operacoes relacionadas a contas, etc.
 * @see Account
 * @see Operation
 */
public interface AccountManager {
	
	/**
	 * Metodo para criar uma nova conta. Todos os campos sao obrigatorios e
	 * nenhum usuario pode ter duas contas com os mesmos atributos.
	 * @param login Login do usuario que eh dono dessa conta.
	 * @param label Descricao dessa conta.
	 * @param agency Agencia dessa conta.
	 * @param account Numero dessa conta.
	 * @return Um numero maior ou igual a um que identifica essa conta.
	 * @throws MissingArgumentException Caso algum dos quatro parametros que sao
	 * passados para esse metodo estejam faltando.
	 * @throws DuplicatedAccountException Caso jah exista uma conta com os mesmos
	 * <code>login</code>, <code>label</code>, <code>agency</code> e <code>account</code>.
	 */
	long createAccount(String login, String label, String agency,
			String account) throws MissingArgumentException, DuplicatedAccountException;
	
	/**
	 * Remove uma conta a partir do seu identificador e do login do usuario que
	 * e dono dessa conta.
	 * @param login Login do usuario.
	 * @param id Identificador dessa conta.
	 * @throws PermissionDeniedException Caso o usuario tente remover uma conta que nao pertence a ele.
	 * @throws AccountNotFoundException Caso a conta nao seja encontrada.
	 */
	void removeAccount(String login, long id) throws PermissionDeniedException, AccountNotFoundException;
	
	/**
	 * Adiciona uma nova operacao a conta padrao.
	 * @param login Login do usuario.
	 * @param type Tipo da operacao, podendo ser <code>debit</code> ou <code>credit</code>.
	 * @param way Modo de pagamento, podendo ser <code>cash</code>, <code>creditcard</code> ou
	 * <code>check</code>.
	 * @param amount Quantia de dinheiro que essa operacao movimentou. Deve ser um valor positivo.
	 * @return Um identificador para essa operacao.
	 * @throws BusinessException Caso a quantia seja menor ou igual a zero.
	 * @throws PermissionDeniedException Caso o usuario tente adicionar a uma conta que nao o pertence.
	 * @throws AccountNotFoundException Caso a conta padrao nao exista.
	 */
	long addOperationIntoDefaultAccount(String login, String type,
			String way, double amount) throws BusinessException, PermissionDeniedException, AccountNotFoundException;
	
	/**
	 * Adiciona uma nova operacao a uma dada conta.
	 * @param login Login do usuario.
	 * @param accId Identificador da conta.
	 * @param type Tipo da operacao, podendo ser <code>debit</code> ou <code>credit</code>.
	 * @param way Modo de pagamento, podendo ser <code>cash</code>, <code>creditcard</code> ou
	 * <code>check</code>.
	 * @param amount Quantia de dinheiro que essa operacao movimentou. Deve ser um valor positivo.
	 * @return Um identificador para essa operacao.
	 * @throws BusinessException Caso a quantia seja menor ou igual a zero.
	 * @throws PermissionDeniedException Caso o usuario tente adicionar a uma conta que nao o pertence.
	 * @throws AccountNotFoundException Caso a conta padrao nao exista.
	 */
	long addOperation(String login, long accId, String type, String way, double amount) throws BusinessException, PermissionDeniedException, AccountNotFoundException;
	
	/**
	 * Metodo de acesso ao tipo da operacao.
	 * @param opId Identificador da conta.
	 * @return O tipo da operacao.
	 * @see AccountManager#addOperation(String, long, String, String, double)
	 * @see AccountManager#addOperationIntoDefaultAccount(String, String, String, double)
	 */
	String getOperationType(long opId);
	
	/**
	 * Metodo de acesso ao modo de pagamento da operacao.
	 * @param opId Identificador da conta.
	 * @return O modo de pagamento da operacao.
	 * @see AccountManager#addOperation(String, long, String, String, double)
	 * @see AccountManager#addOperationIntoDefaultAccount(String, String, String, double)
	 */
	String getOperationWay(long opId);

	/**
	 * Metodo de acesso ao numero total de operacoes que o usuario tem, ou seja, eh o somatorio
	 * de operacoes de todas as contas.
	 * @param login Login do usuario
	 * @return Numero maior ou igual a zero.
	 */
	long getNumberOfOperations(String login);

	/**
	 * Metodo de acesso a quantia que foi movimentada por uma operacao.
	 * @param opId Identificador da operacao.
	 * @return Um numero maior do que zero.
	 */
	double getOperationAmount(long opId);

	/**
	 * Retorna o valor total na conta padrao.
	 * @param login Login do usuario.
	 * @return Um valor maior ou igual a zero.
	 * @throws PermissionDeniedException Caso a conta nao pertence ao login passado.
	 * @throws AccountNotFoundException Caso a conta padrao nao exista.
	 */
	double getDefAccOverallAmount(String login) throws PermissionDeniedException, AccountNotFoundException;

	/**
	 * Retorna o valor total em uma dada conta.
	 * @param login Login do usuario.
	 * @param accId Identificador da conta.
	 * @return Um valor maior ou igual a zero.
	 * @throws PermissionDeniedException Caso a conta nao pertence ao login passado.
	 * @throws AccountNotFoundException Caso a conta padrao nao exista.
	 */
	double getAccOverallAmount(String login, long accId) throws PermissionDeniedException, AccountNotFoundException;

	/**
	 * Remove uma operacao.
	 * @param login Login do usuario.
	 * @param opId Identificador da operacao a ser removida.
	 * @throws PermissionDeniedException Caso a operacao nao pertenca a esse usuario.
	 * @throws UnknownOperationException Caso a operacao nao exista.
	 */
	void removeOperation(String login, long opId) throws PermissionDeniedException, UnknownOperationException;

	/**
	 * Metodo de acesso a uma colecao que contempla o identificador de todas as operacoes de uma certa conta.
	 * @param login Login dojusuario.
	 * @param accId Identificador da conta.
	 * @return Uma colecao de numeros inteiros positivos.
	 * @throws PermissionDeniedException Caso a conta nao pertenca ao dado usuario.
	 * @throws AccountNotFoundException Caso a conta nao exista.
	 */
	Collection<Long> getAllOperations(String login, long accId) throws PermissionDeniedException, AccountNotFoundException;

}
