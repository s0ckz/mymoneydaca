package mymoney.model.external;

import java.io.IOException;

import mymoney.model.exceptions.AccountNotFoundException;
import mymoney.model.exceptions.BusinessException;
import mymoney.model.exceptions.MisunderstandingFileContent;
import mymoney.model.exceptions.PermissionDeniedException;
import mymoney.model.external.exporter.CSVExporter;
import mymoney.model.external.exporter.TXTExporter;
import mymoney.model.external.importer.CSVImporter;
import mymoney.model.external.importer.TXTImporter;

/**
 * Prove implementacao para a interface <code>ExternalManager</code>.
 * @see <code>ExternalManager</code>
 *
 */
public class ExternalManagerImpl implements ExternalManager {

	@Override
	public long[] submitBankOperationsCSV(String login, String fileContent) throws BusinessException, PermissionDeniedException, AccountNotFoundException, MisunderstandingFileContent {
		return new CSVImporter().submitBankOperations(login,fileContent);
	}

	public long[] submitBankOperationsTXT(String login, String fileContent) throws MisunderstandingFileContent, BusinessException, PermissionDeniedException, AccountNotFoundException {
		return new TXTImporter().submitBankOperations(login,fileContent);
	}

	@Override
	public void exportBankOperationsCSV(String login, long accId,
			String pathToFile) throws IOException, PermissionDeniedException, AccountNotFoundException {
		new CSVExporter().export(login, accId, pathToFile);
	}

	public void exportBankOperationsTXT(String login, long accId,
			String pathToFile) throws IOException, PermissionDeniedException, AccountNotFoundException {
		new TXTExporter().export(login, accId, pathToFile);
	}

}
