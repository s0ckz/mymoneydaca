package mymoney.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import mymoney.model.account.AccountManager;
import mymoney.model.account.AccountManagerImpl;
import mymoney.model.account.Operation;
import mymoney.model.auth.AuthManager;
import mymoney.model.auth.AuthManagerImpl;
import mymoney.model.commitment.CommitmentManager;
import mymoney.model.commitment.CommitmentManagerImpl;
import mymoney.model.exceptions.AccountNotFoundException;
import mymoney.model.exceptions.BusinessException;
import mymoney.model.exceptions.CommitmentException;
import mymoney.model.exceptions.DuplicatedAccountException;
import mymoney.model.exceptions.DuplicatedLoginException;
import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidDateException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.LoginUnregisteredException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.MisunderstandingFileContentException;
import mymoney.model.exceptions.MyMoneyException;
import mymoney.model.exceptions.PasswordMismatchException;
import mymoney.model.exceptions.PermissionDeniedException;
import mymoney.model.exceptions.UnknownOperationException;
import mymoney.model.exceptions.UserAlreadyLoggedException;
import mymoney.model.exceptions.UserNotLoggedException;
import mymoney.model.exceptions.UserUnregisteredException;
import mymoney.model.external.ExternalManager;
import mymoney.model.external.ExternalManagerImpl;
import mymoney.model.report.ReportManager;
import mymoney.model.report.ReportManagerImpl;
import mymoney.model.user.UserManager;
import mymoney.model.user.UserManagerImpl;
import mymoney.model.util.DateUtils;
import mymoney.model.util.HibernateUtil;

/**
 * Classe que implementa o sistema MyMoney.
 */
public class MyMoneyImpl implements MyMoney {

	private UserManager userManager = new UserManagerImpl();
	private AuthManager authManager = new AuthManagerImpl();
	private AccountManager accountManager = new AccountManagerImpl();
	private ExternalManager xptoManager = new ExternalManagerImpl();
	private CommitmentManager commitmentManager = new CommitmentManagerImpl();
	private ReportManager reportManager = new ReportManagerImpl();

	public MyMoneyImpl() {
	}

	public String getUserEmail(String login) throws UserUnregisteredException {
		return userManager.getUserEmail(login);
	}

	public String getUserGender(String login) throws UserUnregisteredException {
		return userManager.getUserGender(login);
	}

	public String getUserName(String login) throws UserUnregisteredException {
		return userManager.getUserName(login);
	}

	public void registerUser(String login, String password, String name,
			String gender, String mail) throws MissingArgumentException,
			InvalidArgumentException, InvalidEmailException,
			DuplicatedLoginException {
		userManager.register(login, name, gender, mail);
		try {
			authManager.register(login, password);
		} catch (InvalidArgumentException e) {
			rollbackUserRegister(login, e);
			throw e;
		} catch (MissingArgumentException e) {
			rollbackUserRegister(login, e);
			throw e;
		} catch (DuplicatedLoginException e) {
			rollbackUserRegister(login, e);
			throw e;
		}
	}

	private void rollbackUserRegister(String login, MyMoneyException e) {
		try {
			userManager.removeUser(login);
		} catch (UserUnregisteredException e1) {
		}
	}

	public void removeUser(String login) throws UserUnregisteredException,
			LoginUnregisteredException {
		userManager.removeUser(login);
		authManager.remove(login);
		commitmentManager.removeCommitment(login);
		reportManager.removeReports(login);
	}

	public void doLogin(String login, String password)
			throws PasswordMismatchException, InvalidArgumentException,
			LoginUnregisteredException, UserAlreadyLoggedException {
		authManager.doLogin(login, password);
	}

	public long createAccount(String login, String label, String agency,
			String account) throws MissingArgumentException,
			DuplicatedAccountException {
		return accountManager.createAccount(login, label, agency, account);
	}
	
	public String getAccountLabel(String login, Long accId) throws PermissionDeniedException, AccountNotFoundException {
		return accountManager.getAccountLabel(login, accId);
	}
	
	public Collection<Long> getAllAccountsIds(String login) {
		return accountManager.getAllAccountsIds(login);
	}

	public boolean isLogged(String login) throws LoginUnregisteredException {
		return authManager.isLogged(login);
	}

	public String getOperationType(long opId) {
		return accountManager.getOperationType(opId);
	}

	public String getoperationWay(long opId) {
		return accountManager.getOperationWay(opId);
	}

	public String getOperationDate(long opId) {
		return DateUtils.toString(accountManager.getOperationDate(opId));
	}

	public long getNumberOfOperations(String login) {
		return accountManager.getNumberOfOperations(login);
	}

	public double getOperationAmount(long opId) {
		return accountManager.getOperationAmount(opId);
	}

	public long addOperationIntoDefaultAccount(String login, String type,
			String way, double amount, String date) throws BusinessException,
			PermissionDeniedException, AccountNotFoundException,
			InvalidDateException {
		return accountManager.addOperationIntoDefaultAccount(login, type, way,
				amount, DateUtils.createDate(date));
	}

	public long addOperation(String login, long accId, String type, String way,
			double amount, String date) throws BusinessException,
			PermissionDeniedException, AccountNotFoundException,
			InvalidDateException {
		return accountManager.addOperation(login, accId, type, way, amount,
				DateUtils.createDate(date));
	}

	public double getDefAccOverallAmount(String login)
			throws PermissionDeniedException, AccountNotFoundException {
		return accountManager.getDefAccOverallAmount(login);
	}

	public double getAccOverallAmount(String login, long accId)
			throws PermissionDeniedException, AccountNotFoundException {
		return accountManager.getAccOverallAmount(login, accId);
	}

	public void removeOperation(String login, long opId)
			throws PermissionDeniedException, UnknownOperationException {
		accountManager.removeOperation(login, opId);
	}

	public void removeAccount(String login, long accId)
			throws PermissionDeniedException, AccountNotFoundException {
		accountManager.removeAccount(login, accId);
	}

	@Override
	public void doLogout(String login, String password)
			throws InvalidArgumentException, LoginUnregisteredException,
			PasswordMismatchException, UserNotLoggedException {
		authManager.doLogout(login, password);
	}

	@Override
	public void updateUser(String login, String name, String gender, String mail)
			throws MissingArgumentException, InvalidEmailException,
			InvalidArgumentException, UserUnregisteredException {
		userManager.updateUser(login, name, gender, mail);
	}

	@Override
	public long[] submitBankOperationsCSV(String login, String fileContent)
			throws BusinessException, PermissionDeniedException,
			AccountNotFoundException, MisunderstandingFileContentException {
		return xptoManager.submitBankOperationsCSV(login, fileContent);
	}

	@Override
	public long[] submitBankOperationsTXT(String login, String fileContent)
			throws MisunderstandingFileContentException, BusinessException,
			PermissionDeniedException, AccountNotFoundException {
		return xptoManager.submitBankOperationsTXT(login, fileContent);
	}

	@Override
	public void exportBankOperationsCSV(String login, long accId,
			String pathToFile) throws IOException, PermissionDeniedException,
			AccountNotFoundException {
		xptoManager.exportBankOperationsCSV(login, accId, pathToFile);
	}

	@Override
	public void exportBankOperationsTXT(String login, long accId,
			String pathToFile) throws IOException, PermissionDeniedException,
			AccountNotFoundException {
		xptoManager.exportBankOperationsTXT(login, accId, pathToFile);
	}

	@Override
	public long addCommitment(String login, String label, String date,
			double amount, String type, String frequency)
			throws MissingArgumentException {

		return commitmentManager.addCommitment(login, label, date, amount,
				type, frequency);
	}

	@Override
	public double getCommitmentAmount(String login, long id)
			throws CommitmentException {

		return commitmentManager.getCommitmentAmount(login, id);
	}

	@Override
	public String getCommitmentDate(String login, long id)
			throws CommitmentException {

		return commitmentManager.getCommitmentDate(login, id);
	}

	@Override
	public String getCommitmentFrequency(String login, long id)
			throws CommitmentException {

		return commitmentManager.getCommitmentFrequency(login, id);
	}

	@Override
	public String getCommitmentLabel(String login, long id)
			throws CommitmentException {

		return commitmentManager.getCommitmentLabel(login, id);
	}

	@Override
	public String getCommitmentType(String login, long id)
			throws CommitmentException {

		return commitmentManager.getCommitmentType(login, id);
	}

	@Override
	public int numberOfCommitments(String login) {

		return commitmentManager.numberOfCommitments(login);
	}

	@Override
	public void removeCommitment(String login, long id)
			throws CommitmentException {
		commitmentManager.removeCommitment(login, id);

	}

	@Override
	public Collection<Long> generateReport(String login, String begin,
			String end, long idAccount, String typeOperation)
			throws MissingArgumentException, PermissionDeniedException,
			AccountNotFoundException, InvalidDateException {

		Collection<Operation> operacoes = new ArrayList<Operation>();
		for (Long opId : accountManager.getAllOperations(login, idAccount)) {
			Operation operation = (Operation) HibernateUtil.load(
					Operation.class, opId);
			operacoes.add(operation);
		}
		return reportManager.generateReport(login, begin, end, idAccount,
				typeOperation, operacoes);
	}

	public void removeReports(String login) {
		reportManager.removeReports(login);
	}

}
