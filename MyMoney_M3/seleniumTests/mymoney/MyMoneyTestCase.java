package mymoney;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.FlexUISelenium;
import com.thoughtworks.selenium.Selenium;

import junit.framework.TestCase;

public class MyMoneyTestCase extends TestCase {
	
	private final static String URL = "http://localhost:8080/MyMoney/";
	protected Selenium selenium;
	protected FlexUISelenium flexUITester;

	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*firefox", URL);
		selenium.start();
		selenium.open(URL);
		flexUITester = new FlexUISelenium(selenium, "MyMoneyId");
		flexUITester.waitUntilLoaded();
		Thread.sleep(1000); // para garantir que realmente foi carregado.
	}

	public void tearDown() throws Exception {
		 selenium.stop();
	}

}
