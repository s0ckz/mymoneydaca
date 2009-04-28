package mymoney;

import java.util.Arrays;
import java.util.List;

import easyaccept.EasyAcceptFacade;

public class TestRunner {

	private static final String FILE_SEP = System.getProperty("file.separator");

	public static void main(String[] args) {
		String scriptsPath = "acceptanceTests" + FILE_SEP + "scripts"
				+ FILE_SEP;
		List<String> tests = Arrays.asList(scriptsPath + "US1.txt", scriptsPath
				+ "US2.txt", scriptsPath + "US3.txt",
//				scriptsPath + "US4.1.txt", scriptsPath + "US4.2.txt",
//				scriptsPath + "US4.3.txt", scriptsPath + "US4.4.txt",
//				scriptsPath + "US4.5.txt", scriptsPath + "US4.6.txt",
//				scriptsPath + "US4.7.txt", scriptsPath + "US5.1.txt",
//				scriptsPath + "US5.2.txt", scriptsPath + "US5.3.txt",
//				scriptsPath + "US6.1.txt", scriptsPath + "US6.2.txt",
				scriptsPath + "US7.txt", scriptsPath + "US8.txt", scriptsPath
						+ "US9.txt", scriptsPath + "US10.txt", scriptsPath
						+ "US11.txt");
		MyMoneyFacade myFacade = new MyMoneyFacade();
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(myFacade, tests);
		eaFacade.executeTests();
		System.out.println(eaFacade.getCompleteResults());
	}

}
