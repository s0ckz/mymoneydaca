package mymoney;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Tests for MyMoney");
		
		suite.addTestSuite(LoginTest.class);
		
		suite.addTestSuite(ContaTest.class);
		
		suite.addTestSuite(TransacaoTest.class);
		
		suite.addTestSuite(CompromissosTest.class);
		
		return suite;
	}

}
