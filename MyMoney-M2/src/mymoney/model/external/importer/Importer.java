package mymoney.model.external.importer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mymoney.model.account.AccountManager;
import mymoney.model.account.AccountManagerImpl;
import mymoney.model.exceptions.AccountNotFoundException;
import mymoney.model.exceptions.BusinessException;
import mymoney.model.exceptions.MisunderstandingFileContent;
import mymoney.model.exceptions.PermissionDeniedException;

public abstract class Importer {

	protected AccountManager accountManager;
	
	protected static final String NEW_LINE = System.getProperty("line.separator");
	
	protected static final int ACC_ID_GROUP = 1;

	protected static final int TYPE_GROUP = 2;

	protected static final int WAY_GROUP = 3;

	protected static final int AMOUNT_GROUP = 4;

	protected static final int NUM_GROUPS = 4;
	
	protected static final String TOKEN_TXT = "(\"[^\"]*\"|[^\\s]*)";


	protected Importer() {
		accountManager = new AccountManagerImpl();
	}

	public long[] submitBankOperations(String login, String fileContent) throws MisunderstandingFileContent, BusinessException,
			PermissionDeniedException, AccountNotFoundException {
		Pattern p = makeOperationPattern();
		String[] operations = fileContent.split(NEW_LINE);
		long[] ids = new long[operations.length];
		int index = 0;
		for (String op : operations) {
			Matcher m = p.matcher(op);
			m.matches();
			if (!m.matches() || m.groupCount() != NUM_GROUPS) {
				throw new MisunderstandingFileContent();
			}
			try {
				long accId = Long.parseLong(m.group(ACC_ID_GROUP).replace("\"",
						""));
				double amount = Double.parseDouble(m.group(AMOUNT_GROUP)
						.replace("\"", ""));
				ids[index++] = accountManager.addOperation(login, accId, m
						.group(TYPE_GROUP).replace("\"", ""), m
						.group(WAY_GROUP).replace("\"", ""), amount);
			} catch (NumberFormatException e) {
				throw new MisunderstandingFileContent();
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

	protected abstract Pattern makeOperationPattern();
	
}
