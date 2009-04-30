package mymoney.model.xpto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mymoney.model.account.AccountManager;
import mymoney.model.account.AccountManagerImpl;

public class XptoManagerImpl implements XptoManager {

	private static final String TOKEN = "(\".*\")";

	private static final String NEW_LINE = System.getProperty("line.separator");
	
	private AccountManager accountManager;
	
	public XptoManagerImpl() {
		accountManager = new AccountManagerImpl();
	}
	
	@Override
	public void submitBankOperations(String login, String fileContent) {
		
		String[] operations = fileContent.split(NEW_LINE);
		
		for (String op : operations ) {
//			Pattern p = Pattern.compile(TOKEN + "," + TOKEN + "," + TOKEN + ","+ TOKEN + "," + TOKEN);
			Pattern p = Pattern.compile("(.*),(.*)");
			System.out.println("op = " + op);
			Matcher m = p.matcher(op);
			System.out.println(m.groupCount());
			if (!m.matches() || m.groupCount() != 5) {
				System.out.println(m.matches());
				// excecao
			}
			String date = m.group(0);
			String accId = m.group(1);
			String type = m.group(2);
//			String way = m.group(3);
//			String amount = m.group(4);
			System.out.println("date = " + date);
			System.out.println("accId = " + accId);
			System.out.println("type = " + date);
//			System.out.println("way = " + date);
//			System.out.println("amount = " + date);
		}
		
		// TODO Auto-generated method stub
	}
	
	public static void main(String[] args) {
		XptoManagerImpl xpto = new XptoManagerImpl();
		xpto.submitBankOperations("danilo", "xpto,aqui");
//		xpto.submitBankOperations("danilo", "\"20/04/2009\",\"1234\",\"debit\",\"cash\",\"200.00\"\n\"30/04/2009\",\"1234\",\"credit\",\"cash\",\"1000.00\"");
	}

}
