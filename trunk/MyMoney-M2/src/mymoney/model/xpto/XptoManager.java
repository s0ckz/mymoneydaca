package mymoney.model.xpto;

import java.io.IOException;

import mymoney.model.exceptions.AccountNotFoundException;
import mymoney.model.exceptions.BusinessException;
import mymoney.model.exceptions.MisunderstandingFileContent;
import mymoney.model.exceptions.PermissionDeniedException;

public interface XptoManager {

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

}
