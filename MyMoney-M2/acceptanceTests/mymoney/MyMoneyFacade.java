package mymoney;

import mymoney.model.MyMoney;
import mymoney.model.MyMoneyImpl;
import mymoney.model.exceptions.AccountNotFoundException;
import mymoney.model.exceptions.BusinessException;
import mymoney.model.exceptions.DuplicatedAccountException;
import mymoney.model.exceptions.DuplicatedLoginException;
import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.LoginUnregisteredException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.PasswordMismatchException;
import mymoney.model.exceptions.PermissionDeniedException;
import mymoney.model.exceptions.UnknownOperationException;
import mymoney.model.util.HibernateUtil;

public class MyMoneyFacade {
	
	private MyMoney myMoney;
	
	public MyMoneyFacade() {
		myMoney = new MyMoneyImpl();
	}

	// Utils
	
	public void quit() {
	}
	
	public void cleanAll() {
		HibernateUtil.cleanAll();
	}
	
	public String getFileContent(String filePath) {
		return null;
	}

	// US-01

	public void register(String login, String password, String name,
			String gender, String eMail) throws MissingArgumentException, InvalidArgumentException, InvalidEmailException, DuplicatedLoginException {
		myMoney.registerUser(login, password, name, gender, eMail);
	}
	
	public String getUserName(String login) {
		return myMoney.getUserName(login);
	}
	
	public String getUserGender(String login) {
		return myMoney.getUserGender(login);
	}
	
	public String getUserEmail(String login) {
		return myMoney.getUserEmail(login);
	}
	
	public void removeUser(String login) {
		myMoney.removeUser(login);
	}

	// US-02

	public void doLogin(String login, String password) throws PasswordMismatchException, InvalidArgumentException, LoginUnregisteredException {
		myMoney.doLogin(login, password);
	}
	
	public boolean isLogged(String login) throws LoginUnregisteredException {
		return myMoney.isLogged(login);
	}

	// US-03

	public long createAccount(String login, String label, String agency,
			String account) throws MissingArgumentException, DuplicatedAccountException {
		return myMoney.createAccount(login, label, agency, account);
	}

	// US-04

	public long addOperationIntoDefaultAccount(String login, String type,
			String way, double amount) throws BusinessException, PermissionDeniedException, AccountNotFoundException {
		return myMoney.addOperationIntoDefaultAccount(login, type, way, amount);
	}
	
	public long addOperation(String login, long accId, String type, String way, double amount) throws BusinessException, PermissionDeniedException, AccountNotFoundException {
		return myMoney.addOperation(login, accId, type, way, amount);
	}

	public String getOperationType(long opId) {
		return myMoney.getOperationType(opId);
	}

	public String getOperationWay(long opId) {
		return myMoney.getoperationWay(opId);
	}

	public double getOperationAmount(long opId) {
		return myMoney.getOperationAmount(opId);
	}
	
	public long getNumberOfOperations(String login) {
		return myMoney.getNumberOfOperations(login);
	}
	
	// US-05
	
	public double getDefAccOverallAmount(String login) throws PermissionDeniedException, AccountNotFoundException {
		return myMoney.getDefAccOverallAmount(login);
	}
	
	public double getAccOverallAmount(String login, long accId) throws PermissionDeniedException, AccountNotFoundException {
		return  myMoney.getAccOverallAmount(login, accId);
	}
	
	// US-06
	
	public void removeOperation(String login, long idOperation) throws PermissionDeniedException, UnknownOperationException {
		myMoney.removeOperation(login, idOperation);
	}
	
	// US-07
	
	public void removeAccount(String login, long id) throws PermissionDeniedException, AccountNotFoundException {
		myMoney.removeAccount(login, id);
	}
	
	// US-08
	
	public long addCommitment(String login, String label, String date, double amount, String type, String frequency) {
		return 0;
	}
	
	public String getCommitmentLabel(String login, long id) {
		return null;
	}
	
	public String getCommitmentDate(String login, long id) {
		return null;
	}
	
	public double getCommitmentAmount(String login, long id) {
		return 0.0;
	}
	
	public String getCommitmentType(String login, long id) {
		return null;
	}
	
	public String getCommitmentFrequency(String login, long id) {
		return null;
	}
	
	public long numberOfCommitments(String  login) {
		return 0;
	}
	
	public void removeCommitment(String login, long id) {
	}
	
	// US - 09
	
	public void submitBankOperations(String login, String fileContent) {
	}
	
	// US-10
	
	public void generateReport(String login, String begin, String end, String operationType, long idAccount, boolean commitments) {
	}
	
	public int getNumberOfOperationsReport() {
		return 0;
	}

	public int getNumberOfCreditOperationsReport() {
		return 0;
	}

	public int getNumberOfDebtOperationsReport() {
		return 0;
	}

	public int getNumberOfCommitmentsReport() {
		return 0;
	}
	
	// US-11
	
	public void doLogoff(String login, String password) {
	}

}
