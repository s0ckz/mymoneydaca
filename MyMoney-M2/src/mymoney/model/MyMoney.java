package mymoney.model;

import java.io.IOException;

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

public interface MyMoney {

	void registerUser(String login, String password, String name,
			String gender, String mail) throws MissingArgumentException,
			InvalidArgumentException, InvalidEmailException,
			DuplicatedLoginException;

	void removeUser(String login) throws UserUnregisteredException,
			LoginUnregisteredException;

	String getUserEmail(String login) throws UserUnregisteredException;

	String getUserGender(String login) throws UserUnregisteredException;

	String getUserName(String login) throws UserUnregisteredException;

	void doLogin(String login, String password)
			throws PasswordMismatchException, InvalidArgumentException,
			LoginUnregisteredException, UserAlreadyLoggedException;

	long createAccount(String login, String label, String agency, String account)
			throws MissingArgumentException, DuplicatedAccountException;

	boolean isLogged(String login) throws LoginUnregisteredException;

	String getOperationType(long opId);

	String getoperationWay(long opId);

	double getOperationAmount(long opId);

	long getNumberOfOperations(String login);

	long addOperationIntoDefaultAccount(String login, String type, String way,
			double amount) throws BusinessException, PermissionDeniedException,
			AccountNotFoundException;

	long addOperation(String login, long accId, String type, String way,
			double amount) throws BusinessException, PermissionDeniedException,
			AccountNotFoundException;

	double getDefAccOverallAmount(String login)
			throws PermissionDeniedException, AccountNotFoundException;

	double getAccOverallAmount(String login, long accId)
			throws PermissionDeniedException, AccountNotFoundException;

	void removeOperation(String login, long opId)
			throws PermissionDeniedException, UnknownOperationException;

	void removeAccount(String login, long accId)
			throws PermissionDeniedException, AccountNotFoundException;

	void doLogoff(String login, String password)
			throws InvalidArgumentException, LoginUnregisteredException,
			PasswordMismatchException, UserNotLoggedException;

	void updateUser(String login, String name, String gender, String mail)
			throws MissingArgumentException, InvalidEmailException,
			InvalidArgumentException, UserUnregisteredException;

	long addCommitment(String login, String label, String date, double amount,
			String type, String frequency) throws MissingArgumentException;

	String getCommitmentLabel(String login, long id) throws CommitmentException;

	String getCommitmentDate(String login, long id) throws CommitmentException;

	double getCommitmentAmount(String login, long id)
			throws CommitmentException;

	String getCommitmentType(String login, long id) throws CommitmentException;

	String getCommitmentFrequency(String login, long id)
			throws CommitmentException;

	int numberOfCommitments(String login);

	void removeCommitment(String login, long id) throws CommitmentException;

	long[] submitBankOperationsCSV(String login, String fileContent)
			throws BusinessException, PermissionDeniedException,
			AccountNotFoundException, MisunderstandingFileContent;

	long[] submitBankOperationsTXT(String login, String fileContent)
			throws MisunderstandingFileContent, BusinessException,
			PermissionDeniedException, AccountNotFoundException;

	void exportBankOperationsCSV(String login, long accId, String pathToFile)
			throws IOException, PermissionDeniedException, AccountNotFoundException;

	void exportBankOperationsTXT(String login, long accId, String pathToFile)
			throws IOException, PermissionDeniedException, AccountNotFoundException;

	void generateReport(String login, String begin, String end,
			String operationType, long idAccount, boolean commitments);

	int getNumberOfOperationsReport();

	int getNumberOfCreditOperationsReport();

	int getNumberOfDebtOperationsReport();

	int getNumberOfCommitmentsReport();

}
