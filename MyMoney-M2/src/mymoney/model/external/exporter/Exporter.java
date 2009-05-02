package mymoney.model.external.exporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import mymoney.model.account.AccountManager;
import mymoney.model.account.AccountManagerImpl;
import mymoney.model.exceptions.AccountNotFoundException;
import mymoney.model.exceptions.PermissionDeniedException;

public abstract class Exporter {

	protected AccountManager accountManager;
	
	protected static final String NEW_LINE = System.getProperty("line.separator");


	protected Exporter() {
		accountManager = new AccountManagerImpl();
	}
	
	public void export(String login, long accId, String pathToFile) throws IOException, PermissionDeniedException, AccountNotFoundException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile));
		
		Collection<Long> ids = accountManager.getAllOperations(login,accId);
		StringBuilder content = new StringBuilder();
		for (long opId : ids) {
			String type = accountManager.getOperationType(opId);
			String way = accountManager.getOperationWay(opId);
			double amount = accountManager.getOperationAmount(opId);
			content.append(makeOperationString(accId, type, way, amount));
		}
		
		writer.write(content.toString());
		writer.close();

	}

	protected abstract String makeOperationString(long accId, String type, String way, double amount);	

}
