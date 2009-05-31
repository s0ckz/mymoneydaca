package mymoney;
import junit.framework.TestCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.FlexUISelenium;
import com.thoughtworks.selenium.Selenium;

public class LoginTest extends TestCase {
	private final static String URL = "http://localhost:8082/MyMoney/";
	private Selenium selenium;
	private FlexUISelenium flexUITester;

	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*firefox", URL);
		selenium.start();
		selenium.open(URL);
		flexUITester = new FlexUISelenium(selenium, "MyMoneyId");
		flexUITester.waitUntilLoaded();
		Thread.sleep(1000); // para garantir que realmente foi carregado.
	}

	public void tearDown() throws Exception {
//		 selenium.stop();
	}

	public void testInvalidLogin() throws Exception {
		flexUITester.click("loginButton");
		flexUITester.wait(1);
		flexUITester.click("OK");
		assertEquals("Invalid Argument", flexUITester.call("getLastErrorMessage"));
		
		flexUITester.type("wrongUser").at("login");
		flexUITester.click("loginButton");
		flexUITester.wait(1);
		flexUITester.click("OK");
		assertEquals("Invalid Argument", flexUITester.call("getLastErrorMessage"));
		
		flexUITester.type("wrongPassword").at("senha");
		flexUITester.click("loginButton");
		flexUITester.wait(4);
		flexUITester.click("OK");
		assertEquals("Login unregistered", flexUITester.call("getLastErrorMessage"));
	}

}