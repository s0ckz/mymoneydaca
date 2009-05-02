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
import mymoney.util.FileContentParser;

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

	public String getFileContent(String filePath) throws IOException {
		return FileContentParser.getFileContent(filePath);
	}

	// US-01

	public void register(String login, String password, String name,
			String gender, String eMail) throws MissingArgumentException,
			InvalidArgumentException, InvalidEmailException,
			DuplicatedLoginException {
		myMoney.registerUser(login, password, name, gender, eMail);
	}

	public String getUserName(String login) throws UserUnregisteredException {
		return myMoney.getUserName(login);
	}

	public String getUserGender(String login) throws UserUnregisteredException {
		return myMoney.getUserGender(login);
	}

	public String getUserEmail(String login) throws UserUnregisteredException {
		return myMoney.getUserEmail(login);
	}

	public void removeUser(String login) throws UserUnregisteredException,
			LoginUnregisteredException {
		myMoney.removeUser(login);
	}

	public void updateUser(String login, String name, String gender,
			String eMail) throws MissingArgumentException,
			InvalidEmailException, InvalidArgumentException,
			UserUnregisteredException {
		myMoney.updateUser(login, name, gender, eMail);
	}

	// US-02

	public void doLogin(String login, String password)
			throws PasswordMismatchException, InvalidArgumentException,
			LoginUnregisteredException, UserAlreadyLoggedException {
		myMoney.doLogin(login, password);
	}

	public boolean isLogged(String login) throws LoginUnregisteredException {
		return myMoney.isLogged(login);
	}

	// US-03

	/**
	 * Metodo para criar uma nova conta. Todos os campos sao obrigatórios e
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
	public long createAccount(String login, String label, String agency,
			String account) throws MissingArgumentException,
			DuplicatedAccountException {
		return myMoney.createAccount(login, label, agency, account);
	}

	// US-04

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
	public long addOperationIntoDefaultAccount(String login, String type,
			String way, double amount) throws BusinessException,
			PermissionDeniedException, AccountNotFoundException {
		return myMoney.addOperationIntoDefaultAccount(login, type, way, amount);
	}

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
	public long addOperation(String login, long accId, String type, String way,
			double amount) throws BusinessException, PermissionDeniedException,
			AccountNotFoundException {
		return myMoney.addOperation(login, accId, type, way, amount);
	}

	/**
	 * Metodo de acesso ao tipo da operacao.
	 * @param opId Identificador da conta.
	 * @return O tipo da operacao.
	 * @see AccountManager#addOperation(String, long, String, String, double)
	 * @see AccountManager#addOperationIntoDefaultAccount(String, String, String, double)
	 */
	public String getOperationType(long opId) {
		return myMoney.getOperationType(opId);
	}

	/**
	 * Metodo de acesso ao modo de pagamento da operacao.
	 * @param opId Identificador da conta.
	 * @return O modo de pagamento da operacao.
	 * @see AccountManager#addOperation(String, long, String, String, double)
	 * @see AccountManager#addOperationIntoDefaultAccount(String, String, String, double)
	 */
	public String getOperationWay(long opId) {
		return myMoney.getoperationWay(opId);
	}

	/**
	 * Metodo de acesso a quantia que foi movimentada por uma operacao.
	 * @param opId Identificador da operacao.
	 * @return Um numero maior do que zero.
	 */
	public double getOperationAmount(long opId) {
		return myMoney.getOperationAmount(opId);
	}

	/**
	 * Metodo de acesso ao numero total de operacoes que o usuário tem, ou seja, eh o somatorio
	 * de operacoes de todas as contas.
	 * @param login Login do usuario
	 * @return Numero maior ou igual a zero.
	 */
	public long getNumberOfOperations(String login) {
		return myMoney.getNumberOfOperations(login);
	}

	// US-05

	/**
	 * Retorna o valor total na conta padrao.
	 * @param login Login do usuario.
	 * @return Um valor maior ou igual a zero.
	 * @throws PermissionDeniedException Caso a conta nao pertence ao login passado.
	 * @throws AccountNotFoundException Caso a conta padrao nao exista.
	 */
	public double getDefAccOverallAmount(String login)
			throws PermissionDeniedException, AccountNotFoundException {
		return myMoney.getDefAccOverallAmount(login);
	}

	/**
	 * Retorna o valor total em uma dada conta.
	 * @param login Login do usuario.
	 * @param accId Identificador da conta.
	 * @return Um valor maior ou igual a zero.
	 * @throws PermissionDeniedException Caso a conta nao pertence ao login passado.
	 * @throws AccountNotFoundException Caso a conta padrao nao exista.
	 */
	public double getAccOverallAmount(String login, long accId)
			throws PermissionDeniedException, AccountNotFoundException {
		return myMoney.getAccOverallAmount(login, accId);
	}

	// US-06

	/**
	 * Remove uma operacao.
	 * @param login Login do usuario.
	 * @param idOperation Identificador da operacao a ser removida.
	 * @throws PermissionDeniedException Caso a operacao nao pertenca a esse usuario.
	 * @throws UnknownOperationException Caso a operacao nao exista.
	 */
	public void removeOperation(String login, long idOperation)
			throws PermissionDeniedException, UnknownOperationException {
		myMoney.removeOperation(login, idOperation);
	}

	// US-07

	/**
	 * Remove uma conta a partir do seu identificador e do login do usuário que
	 * é dono dessa conta.
	 * @param login Login do usuario.
	 * @param id Identificador dessa conta.
	 * @throws PermissionDeniedException Caso o usuario tente remover uma conta que nao pertence a ele.
	 * @throws AccountNotFoundException Caso a conta nao seja encontrada.
	 */
	public void removeAccount(String login, long id)
			throws PermissionDeniedException, AccountNotFoundException {
		myMoney.removeAccount(login, id);
	}

	// US-08

	public long addCommitment(String login, String label, String date,
			double amount, String type, String frequency)
			throws CommitmentException, MissingArgumentException {
		return myMoney.addCommitment(login, label, date, amount, type,
				frequency);
	}

	public String getCommitmentLabel(String login, long id)
			throws CommitmentException {
		return myMoney.getCommitmentLabel(login, id);
	}

	public String getCommitmentDate(String login, long id)
			throws CommitmentException {
		return myMoney.getCommitmentDate(login, id);
	}

	public double getCommitmentAmount(String login, long id)
			throws CommitmentException {
		return myMoney.getCommitmentAmount(login, id);
	}

	public String getCommitmentType(String login, long id)
			throws CommitmentException {
		return myMoney.getCommitmentType(login, id);
	}

	public String getCommitmentFrequency(String login, long id)
			throws CommitmentException {
		return myMoney.getCommitmentFrequency(login, id);
	}

	public int numberOfCommitments(String login) throws CommitmentException {
		return myMoney.numberOfCommitments(login);
	}

	public void removeCommitment(String login, long id)
			throws CommitmentException {
		myMoney.removeCommitment(login, id);
	}

	// US - 09

	public String submitBankOperationsCSV(String login, String fileContent)
			throws BusinessException, PermissionDeniedException,
			AccountNotFoundException, MisunderstandingFileContent {
		return Arrays.toString(myMoney.submitBankOperationsCSV(login,
				fileContent));
	}

	public String submitBankOperationsTXT(String login, String fileContent)
			throws BusinessException, PermissionDeniedException,
			AccountNotFoundException, MisunderstandingFileContent {
		return Arrays.toString(myMoney.submitBankOperationsTXT(login,
				fileContent));
	}

	public void exportBankOperationsCSV(String login, long accId,
			String pathToFile) throws IOException, PermissionDeniedException,
			AccountNotFoundException {
		myMoney.exportBankOperationsCSV(login, accId, pathToFile);
	}

	public void exportBankOperationsTXT(String login, long accId,
			String pathToFile) throws IOException, PermissionDeniedException,
			AccountNotFoundException {
		myMoney.exportBankOperationsTXT(login, accId, pathToFile);
	}

	// US-10

	public long generateReportDebitOperations(String login, String begin,
			String end, long idAccount) throws MissingArgumentException {
		return myMoney.generateReportDebtOperations(login, begin, end,
				idAccount);
	}

	public long generateReportCreditOperations(String login, String begin,
			String end, long idAccount) throws MissingArgumentException {
		return myMoney.generateReportCreditOperations(login, begin, end,
				idAccount);
	}

	public long generateReportsOperations(String login, String begin,
			String end, long idAccount, String typeOperation)
			throws MissingArgumentException {
		return myMoney.generateReportOperations(login, begin, end, idAccount,
				typeOperation);
	}

	public int getReports(String login) {
		return myMoney.getReports(login);
	}

	// US-11

	public void doLogoff(String login, String password)
			throws InvalidArgumentException, LoginUnregisteredException,
			PasswordMismatchException, UserNotLoggedException {
		myMoney.doLogoff(login, password);
	}

}
