package mymoney.model.xpto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mymoney.model.account.AccountManager;
import mymoney.model.account.AccountManagerImpl;
import mymoney.model.exceptions.AccountNotFoundException;
import mymoney.model.exceptions.BusinessException;
import mymoney.model.exceptions.MisunderstandingFileContent;
import mymoney.model.exceptions.PermissionDeniedException;

public class XptoManagerImpl implements XptoManager {

	private static final String TOKEN_CSV = "\"([^\"]*)\"";

	private static final String NEW_LINE = System.getProperty("line.separator");

	private static final int ACC_ID_GROUP = 1;

	private static final int TYPE_GROUP = 2;

	private static final int WAY_GROUP = 3;

	private static final int AMOUNT_GROUP = 4;

	private static final int NUM_GROUPS = 4;

	private static final String TOKEN_TXT = "(\"[^\"]*\"|[^\\s]*)";
	
	private AccountManager accountManager;
	
	public XptoManagerImpl() {
		accountManager = new AccountManagerImpl();
	}
	
	@Override
	public long[] submitBankOperationsCSV(String login, String fileContent) throws BusinessException, PermissionDeniedException, AccountNotFoundException, MisunderstandingFileContent {
		return submitBankOpsGeneric(login, fileContent, makePatternCSV());
		
	}

	private long[] submitBankOpsGeneric(String login, String fileContent, Pattern p)
			throws MisunderstandingFileContent, BusinessException,
			PermissionDeniedException, AccountNotFoundException {
		String[] operations = fileContent.split(NEW_LINE);
		long[] ids = new long[operations.length];
		int index = 0;
		for (String op : operations ) {
			Matcher m = p.matcher(op);
			m.matches();
			if (!m.matches() || m.groupCount() != NUM_GROUPS) {
				throw new MisunderstandingFileContent();
			}
			try {
				long accId = Long.parseLong(m.group(ACC_ID_GROUP).replace("\"", ""));
				double amount = Double.parseDouble(m.group(AMOUNT_GROUP).replace("\"",""));
				ids[index++] = accountManager.addOperation(login, accId, m.group(TYPE_GROUP).replace("\"",""), m.group(WAY_GROUP).replace("\"",""), amount);
			} catch (NumberFormatException e) {
				e.printStackTrace();
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

	@Override
	public long[] submitBankOperationsTXT(String login, String fileContent) throws MisunderstandingFileContent, BusinessException, PermissionDeniedException, AccountNotFoundException {
		return submitBankOpsGeneric(login, fileContent, makePatternTXT());
	}

	private Pattern makePatternTXT() {
		String regex = TOKEN_TXT;
		for (int i = 1; i < NUM_GROUPS; i++) {
			regex += "\\s+" + TOKEN_TXT;
		}
		Pattern p = Pattern.compile(regex);
		return p;
	}

	private Pattern makePatternCSV() {
		String regex = TOKEN_CSV;
		for (int i = 1; i < NUM_GROUPS; i++) {
			regex += "," + TOKEN_CSV;
		}
		Pattern p = Pattern.compile(regex);
		return p;
	}
	
	public static void main(String[] args) throws BusinessException, PermissionDeniedException, AccountNotFoundException, MisunderstandingFileContent {
		XptoManagerImpl xpto = new XptoManagerImpl();
//		xpto.submitBankOperations("danilo", "\"xptoaqui\",\"outro\"");
//		xpto.submitBankOperationsTXT("danilo", "1234	\"debit\"	\"cash\"	\"200.00\"");
		xpto.submitBankOperationsTXT("danilo", "1234	\"debit\"	\"cash\"	200.00\r\n1234	\"credit\"	\"cash\"	1000.00");
	}

}
