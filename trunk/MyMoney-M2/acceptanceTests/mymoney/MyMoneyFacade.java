package mymoney;

import java.io.IOException;
import java.util.Arrays;

import mymoney.model.MyMoney;
import mymoney.model.MyMoneyImpl;
import mymoney.model.account.AccountManager;
import mymoney.model.exceptions.AccountNotFoundException;
import mymoney.model.exceptions.BusinessException;
import mymoney.model.exceptions.CommitmentException;
import mymoney.model.exceptions.DuplicatedAccountException;
import mymoney.model.exceptions.DuplicatedLoginException;
import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.LoginUnregisteredException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.MisunderstandingFileContent;
import mymoney.model.exceptions.PasswordMismatchException;
import mymoney.model.exceptions.PermissionDeniedException;
import mymoney.model.exceptions.UnknownOperationException;
import mymoney.model.exceptions.UserAlreadyLoggedException;
import mymoney.model.exceptions.UserNotLoggedException;
import mymoney.model.exceptions.UserUnregisteredException;
import mymoney.model.util.HibernateUtil;

/**
 * Fachada utilizada pelo EasyAccept.
 */
public class MyMoneyFacade {

	private MyMoney myMoney;

	/**
	 * Cria uma nova fachada.
	 */
	public MyMoneyFacade() {
		myMoney = new MyMoneyImpl();
	}

	// Utils

	/**
	 * Limpa o banco de dados.
	 */
	public void cleanAll() {
		HibernateUtil.cleanAll();
	}

	// US-01

	/**
	 * Efetua o registro de um usuario
	 */
	public void register(String login, String password, String name,
			String gender, String eMail) throws MissingArgumentException,
			InvalidArgumentException, InvalidEmailException,
			DuplicatedLoginException {
		myMoney.registerUser(login, password, name, gender, eMail);
	}

	/**
	 * Retorna o nome do usuario
	 * 
	 * @param login
	 *            o login do usuario
	 * @return nome o nome do usuario
	 * @throws UserUnregisteredException
	 *             Caso o usuario nao esteja registrado
	 */
	public String getUserName(String login) throws UserUnregisteredException {
		return myMoney.getUserName(login);
	}

	/**
	 * Retorna o genero de um usuario
	 * 
	 * @param login
	 *            o login do usuario
	 * @return o genero do usuario (M ou F)
	 * @throws UserUnregisteredException
	 *             Caso o usuario nao esteja registrado
	 */
	public String getUserGender(String login) throws UserUnregisteredException {
		return myMoney.getUserGender(login);
	}

	/**
	 * Retorna o email do usuario
	 * 
	 * @param login
	 *            o login do usuario
	 * @return mail o email do usuario
	 * @throws UserUnregisteredException
	 *             Caso o usuario nao esteja registrado
	 */
	public String getUserEmail(String login) throws UserUnregisteredException {
		return myMoney.getUserEmail(login);
	}

	/**
	 * Remove um usuario
	 * 
	 * @param login
	 *            o login do usuario
	 * @throws UserUnregisteredException
	 *             Caso o usuario nao esteja registrado
	 * @throws LoginUnregisteredException
	 *             Caso o login nao esteja registrado
	 */
	public void removeUser(String login) throws UserUnregisteredException,
			LoginUnregisteredException {
		myMoney.removeUser(login);
	}

	/**
	 * Metodo que atualiza um usuario
	 * 
	 * @param login
	 *            o login do usuario
	 * @param name
	 *            o nome do usuario
	 * @param gender
	 *            o sexo do usuario
	 * @param eMail
	 *            o email do usuario
	 * @throws MissingArgumentException
	 *             Caso haja falta de argumentos
	 * @throws InvalidEmailException
	 *             Caso o email seja invalido
	 * @throws InvalidArgumentException
	 *             Caso haja argumentos invalidos
	 * @throws UserUnregisteredException
	 *             Caso o usuario nao esteja registrado
	 */
	public void updateUser(String login, String name, String gender,
			String eMail) throws MissingArgumentException,
			InvalidEmailException, InvalidArgumentException,
			UserUnregisteredException {
		myMoney.updateUser(login, name, gender, eMail);
	}

	// US-02

	/**
	 * Metodo que realiza login
	 */
	public void doLogin(String login, String password)
			throws PasswordMismatchException, InvalidArgumentException,
			LoginUnregisteredException, UserAlreadyLoggedException {
		myMoney.doLogin(login, password);
	}

	/**
	 * Metodo que verifica se um dado usuario esta logado no sistema
	 * 
	 * @param login
	 *            O login do usuario
	 * @return true se o usuario esta logado, false caso contrario
	 * @throws LoginUnregisteredException
	 *             Caso o login nao esteja registrado
	 */
	public boolean isLogged(String login) throws LoginUnregisteredException {
		return myMoney.isLogged(login);
	}

	// US-03

	/**
	 * Metodo para criar uma nova conta. Todos os campos sao obrigatorios e
	 * nenhum usuario pode ter duas contas com os mesmos atributos.
	 * 
	 * @param login
	 *            Login do usuario que eh dono dessa conta.
	 * @param label
	 *            Descricao dessa conta.
	 * @param agency
	 *            Agencia dessa conta.
	 * @param account
	 *            Numero dessa conta.
	 * @return Um numero maior ou igual a um que identifica essa conta.
	 * @throws MissingArgumentException
	 *             Caso algum dos quatro parametros que sao passados para esse
	 *             metodo estejam faltando.
	 * @throws DuplicatedAccountException
	 *             Caso jah exista uma conta com os mesmos <code>login</code>,
	 *             <code>label</code>, <code>agency</code> e
	 *             <code>account</code>.
	 */
	public long createAccount(String login, String label, String agency,
			String account) throws MissingArgumentException,
			DuplicatedAccountException {
		return myMoney.createAccount(login, label, agency, account);
	}

	// US-04

	/**
	 * Adiciona uma nova operacao a conta padrao.
	 * 
	 * @param login
	 *            Login do usuario.
	 * @param type
	 *            Tipo da operacao, podendo ser <code>debit</code> ou
	 *            <code>credit</code>.
	 * @param way
	 *            Modo de pagamento, podendo ser <code>cash</code>,
	 *            <code>creditcard</code> ou <code>check</code>.
	 * @param amount
	 *            Quantia de dinheiro que essa operacao movimentou. Deve ser um
	 *            valor positivo.
	 * @return Um identificador para essa operacao.
	 * @throws BusinessException
	 *             Caso a quantia seja menor ou igual a zero.
	 * @throws PermissionDeniedException
	 *             Caso o usuario tente adicionar a uma conta que nao o
	 *             pertence.
	 * @throws AccountNotFoundException
	 *             Caso a conta padrao nao exista.
	 */
	public long addOperationIntoDefaultAccount(String login, String type,
			String way, double amount) throws BusinessException,
			PermissionDeniedException, AccountNotFoundException {
		return myMoney.addOperationIntoDefaultAccount(login, type, way, amount);
	}

	/**
	 * Adiciona uma nova operacao a uma dada conta.
	 * 
	 * @param login
	 *            Login do usuario.
	 * @param accId
	 *            Identificador da conta.
	 * @param type
	 *            Tipo da operacao, podendo ser <code>debit</code> ou
	 *            <code>credit</code>.
	 * @param way
	 *            Modo de pagamento, podendo ser <code>cash</code>,
	 *            <code>creditcard</code> ou <code>check</code>.
	 * @param amount
	 *            Quantia de dinheiro que essa operacao movimentou. Deve ser um
	 *            valor positivo.
	 * @return Um identificador para essa operacao.
	 * @throws BusinessException
	 *             Caso a quantia seja menor ou igual a zero.
	 * @throws PermissionDeniedException
	 *             Caso o usuario tente adicionar a uma conta que nao o
	 *             pertence.
	 * @throws AccountNotFoundException
	 *             Caso a conta padrao nao exista.
	 */
	public long addOperation(String login, long accId, String type, String way,
			double amount) throws BusinessException, PermissionDeniedException,
			AccountNotFoundException {
		return myMoney.addOperation(login, accId, type, way, amount);
	}

	/**
	 * Metodo de acesso ao tipo da operacao.
	 * 
	 * @param opId
	 *            Identificador da conta.
	 * @return O tipo da operacao.
	 * @see AccountManager#addOperation(String, long, String, String, double)
	 * @see AccountManager#addOperationIntoDefaultAccount(String, String,
	 *      String, double)
	 */
	public String getOperationType(long opId) {
		return myMoney.getOperationType(opId);
	}

	/**
	 * Metodo de acesso ao modo de pagamento da operacao.
	 * 
	 * @param opId
	 *            Identificador da conta.
	 * @return O modo de pagamento da operacao.
	 * @see AccountManager#addOperation(String, long, String, String, double)
	 * @see AccountManager#addOperationIntoDefaultAccount(String, String,
	 *      String, double)
	 */
	public String getOperationWay(long opId) {
		return myMoney.getoperationWay(opId);
	}

	/**
	 * Metodo de acesso a quantia que foi movimentada por uma operacao.
	 * 
	 * @param opId
	 *            Identificador da operacao.
	 * @return Um numero maior do que zero.
	 */
	public double getOperationAmount(long opId) {
		return myMoney.getOperationAmount(opId);
	}

	/**
	 * Metodo de acesso ao numero total de operacoes que o usuario tem, ou seja,
	 * eh o somatorio de operacoes de todas as contas.
	 * 
	 * @param login
	 *            Login do usuario
	 * @return Numero maior ou igual a zero.
	 */
	public long getNumberOfOperations(String login) {
		return myMoney.getNumberOfOperations(login);
	}

	// US-05

	/**
	 * Retorna o valor total na conta padrao.
	 * 
	 * @param login
	 *            Login do usuario.
	 * @return Um valor maior ou igual a zero.
	 * @throws PermissionDeniedException
	 *             Caso a conta nao pertence ao login passado.
	 * @throws AccountNotFoundException
	 *             Caso a conta padrao nao exista.
	 */
	public double getDefAccOverallAmount(String login)
			throws PermissionDeniedException, AccountNotFoundException {
		return myMoney.getDefAccOverallAmount(login);
	}

	/**
	 * Retorna o valor total em uma dada conta.
	 * 
	 * @param login
	 *            Login do usuario.
	 * @param accId
	 *            Identificador da conta.
	 * @return Um valor maior ou igual a zero.
	 * @throws PermissionDeniedException
	 *             Caso a conta nao pertence ao login passado.
	 * @throws AccountNotFoundException
	 *             Caso a conta padrao nao exista.
	 */
	public double getAccOverallAmount(String login, long accId)
			throws PermissionDeniedException, AccountNotFoundException {
		return myMoney.getAccOverallAmount(login, accId);
	}

	// US-06

	/**
	 * Remove uma operacao.
	 * 
	 * @param login
	 *            Login do usuario.
	 * @param idOperation
	 *            Identificador da operacao a ser removida.
	 * @throws PermissionDeniedException
	 *             Caso a operacao nao pertenca a esse usuario.
	 * @throws UnknownOperationException
	 *             Caso a operacao nao exista.
	 */
	public void removeOperation(String login, long idOperation)
			throws PermissionDeniedException, UnknownOperationException {
		myMoney.removeOperation(login, idOperation);
	}

	// US-07

	/**
	 * Remove uma conta a partir do seu identificador e do login do usuario que
	 * e dono dessa conta.
	 * 
	 * @param login
	 *            Login do usuario.
	 * @param id
	 *            Identificador dessa conta.
	 * @throws PermissionDeniedException
	 *             Caso o usuario tente remover uma conta que nao pertence a
	 *             ele.
	 * @throws AccountNotFoundException
	 *             Caso a conta nao seja encontrada.
	 */
	public void removeAccount(String login, long id)
			throws PermissionDeniedException, AccountNotFoundException {
		myMoney.removeAccount(login, id);
	}

	// US-08

	/**
	 * Metodo que adiciona um compromisso
	 */
	public long addCommitment(String login, String label, String date,
			double amount, String type, String frequency)
			throws CommitmentException, MissingArgumentException {
		return myMoney.addCommitment(login, label, date, amount, type,
				frequency);
	}

	/**
	 * Metodo que retorna o label de um compromisso
	 * 
	 * @param login
	 *            O login do usuario
	 * @param id
	 *            o id do compromisso
	 * @return
	 * @throws CommitmentException
	 *             Caso haja algum evento inesperado com o compromisso
	 */
	public String getCommitmentLabel(String login, long id)
			throws CommitmentException {
		return myMoney.getCommitmentLabel(login, id);
	}

	/**
	 * Retorna a data do comprimisso
	 * 
	 * @param login
	 *            O login do usuario
	 * @param id
	 *            o id do compromisso
	 * @return date A data do compromisso
	 * @throws CommitmentException
	 *             Caso haja algum evento inesperado com o compromisso
	 */
	public String getCommitmentDate(String login, long id)
			throws CommitmentException {
		return myMoney.getCommitmentDate(login, id);
	}

	/**
	 * Retorna o montante do compromisso
	 * 
	 * @param login
	 *            O login do usuario
	 * @param id
	 *            o id do compromisso
	 * @return amount O montante do compromisso
	 * @throws CommitmentException
	 *             Caso haja algum evento inesperado com o compromisso
	 */
	public double getCommitmentAmount(String login, long id)
			throws CommitmentException {
		return myMoney.getCommitmentAmount(login, id);
	}

	/**
	 * Retorna o tipo do compromisso
	 * 
	 * @param login
	 *            O login do usuario
	 * @param id
	 *            O id do compromisso
	 * @return type O tipo do compromisso
	 * @throws CommitmentException
	 *             Caso haja algum evento inesperado com o compromisso
	 */
	public String getCommitmentType(String login, long id)
			throws CommitmentException {
		return myMoney.getCommitmentType(login, id);
	}

	/**
	 * Retorna a frequencia do compromisso
	 * 
	 * @param login
	 *            O login do usuario
	 * @param id
	 *            O id do compromisso
	 * @return frequency A frequencia do compromisso
	 * @throws CommitmentException
	 *             Caso haja algum evento inesperado com o compromisso
	 */
	public String getCommitmentFrequency(String login, long id)
			throws CommitmentException {
		return myMoney.getCommitmentFrequency(login, id);
	}

	/**
	 * Retorna o numero de compromissos de um usuario
	 * 
	 * @param login
	 *            O login do usuario
	 * @return inteiro que representa o numero de compromissos
	 * @throws CommitmentException
	 *             Caso haja algum evento inesperado com o compromisso
	 */
	public int numberOfCommitments(String login) throws CommitmentException {
		return myMoney.numberOfCommitments(login);
	}

	/**
	 * Metodo que remove o compromisso de um usuario
	 * 
	 * @param login
	 *            O login do usuario
	 * @param id
	 *            o id do compromisso
	 * @throws CommitmentException
	 *             Caso haja algum evento inesperado com o compromisso
	 */
	public void removeCommitment(String login, long id)
			throws CommitmentException {
		myMoney.removeCommitment(login, id);
	}

	// US - 09

	/**
	 * Metodo que submete um arquivo em formato .CSV com operacoes bancarias
	 * para o sistema.
	 * 
	 * @param login
	 *            O login do usuario
	 * @param fileContent
	 *            O conteudo do arquivo
	 * @return
	 * @throws BusinessException
	 * @throws PermissionDeniedException
	 * @throws AccountNotFoundException
	 * @throws MisunderstandingFileContent
	 */
	public String submitBankOperationsCSV(String login, String fileContent)
			throws BusinessException, PermissionDeniedException,
			AccountNotFoundException, MisunderstandingFileContent {
		return Arrays.toString(myMoney.submitBankOperationsCSV(login,
				fileContent));
	}

	/**
	 * Metodo que submete um arquivo em formato .TXT com operacoes bancarias
	 * para o sistema
	 * 
	 * @param login
	 *            O login do usuario
	 * @param fileContent
	 *            O conteudo do arquivo
	 * @return
	 * @throws BusinessException
	 * @throws PermissionDeniedException
	 * @throws AccountNotFoundException
	 * @throws MisunderstandingFileContent
	 */
	public String submitBankOperationsTXT(String login, String fileContent)
			throws BusinessException, PermissionDeniedException,
			AccountNotFoundException, MisunderstandingFileContent {
		return Arrays.toString(myMoney.submitBankOperationsTXT(login,
				fileContent));
	}

	/**
	 * Metodo que exporta operacoes bancarias para um arquivo em formato .CSV
	 * 
	 * @param login
	 *            O login do usuario
	 * @param accId
	 *            O id da conta
	 * @param pathToFile
	 *            O caminho do arquivo
	 * @throws IOException
	 * @throws PermissionDeniedException
	 * @throws AccountNotFoundException
	 */
	public void exportBankOperationsCSV(String login, long accId,
			String pathToFile) throws IOException, PermissionDeniedException,
			AccountNotFoundException {
		myMoney.exportBankOperationsCSV(login, accId, pathToFile);
	}

	/**
	 * 
	 * Metodo que exporta operacoes bancarias para um arquivo em formato .TXT
	 * 
	 * @param login
	 *            O login do usuario
	 * @param accId
	 *            O id da conta
	 * @param pathToFile
	 *            O caminho do arquivo
	 * @throws IOException
	 * @throws PermissionDeniedException
	 * @throws AccountNotFoundException
	 */
	public void exportBankOperationsTXT(String login, long accId,
			String pathToFile) throws IOException, PermissionDeniedException,
			AccountNotFoundException {
		myMoney.exportBankOperationsTXT(login, accId, pathToFile);
	}

	// US-10

	/**
	 * Gera um relatorio de debitos * @param login O login do usuario
	 * 
	 * @param begin
	 *            A data de inicio do relatorio
	 * @param end
	 *            A data de fim do relatorio
	 * @param idAccount
	 *            O id da conta
	 * @return long que representa o codigo do relatorio gerado
	 * @throws MissingArgumentException
	 *             Caso algum argumento nao tenha sido passado
	 */
	public long generateReportDebitOperations(String login, String begin,
			String end, long idAccount) throws MissingArgumentException {
		return myMoney.generateReportDebtOperations(login, begin, end,
				idAccount);
	}

	/**
	 * Gera um relatorio de creditos
	 * 
	 * @param login
	 *            O login do usuario
	 * @param begin
	 *            A data de inicio do relatorio
	 * @param end
	 *            A data de fim do relatorio
	 * @param idAccount
	 *            O id da conta
	 * @return long que representa o codigo do relatorio gerado
	 * @throws MissingArgumentException
	 *             Caso algum argumento nao tenha sido passado
	 */
	public long generateReportCreditOperations(String login, String begin,
			String end, long idAccount) throws MissingArgumentException {
		return myMoney.generateReportCreditOperations(login, begin, end,
				idAccount);
	}

	/**
	 * Metodo que gera um relatorio
	 * 
	 * @param login
	 *            O login do usuario
	 * @param begin
	 *            A data de inicio do relatorio
	 * @param end
	 *            A data de fim do relatorio
	 * @param idAccount
	 *            O id da conta
	 * @return long que representa o codigo do relatorio gerado
	 * @throws MissingArgumentException
	 *             Caso algum argumento nao tenha sido passado
	 */
	public long generateReportsOperations(String login, String begin,
			String end, long idAccount, String typeOperation)
			throws MissingArgumentException {
		return myMoney.generateReportOperations(login, begin, end, idAccount,
				typeOperation);
	}

	/**
	 * Metodo que retorna o numero de relatorios de um usuario
	 * 
	 * @param login
	 *            O login do usuario
	 * @return inteiro que representa o numero de relatorios de um usuario
	 */
	public int getReports(String login) {
		return myMoney.getReports(login);
	}

	// US-11

	/**
	 * Metodo que faz o logoff de um usuario
	 */
	public void doLogoff(String login, String password)
			throws InvalidArgumentException, LoginUnregisteredException,
			PasswordMismatchException, UserNotLoggedException {
		myMoney.doLogoff(login, password);
	}

}
