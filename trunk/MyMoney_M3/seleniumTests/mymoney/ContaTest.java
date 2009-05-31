package mymoney;
import junit.framework.TestCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.FlexUISelenium;
import com.thoughtworks.selenium.Selenium;

public class ContaTest extends TestCase {
	private final static String URL = "http://localhost:8080/MyMoney/";
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
		 selenium.stop();
	}

	public void testCreateAccount() throws Exception {
		flexUITester.type("leandro").at("login");
		flexUITester.type("leandro").at("senha");
		flexUITester.click("loginButton");
		flexUITester.wait(4);
		assertEquals("Seja bem-vindo!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
		
		assertEquals("", flexUITester.call("getNomeContas"));
		
		flexUITester.call("addConta");
		
		flexUITester.click("okButtonCadastrarConta");
		flexUITester.wait(3);
		assertEquals("Missing Argument", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
		
		flexUITester.type("teste").at("rotuloCadastrarConta");
		flexUITester.click("okButtonCadastrarConta");
		flexUITester.wait(3);
		assertEquals("Missing Argument", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
		
		flexUITester.type("12345").at("agenciaCadastrarConta");
		flexUITester.click("okButtonCadastrarConta");
		flexUITester.wait(3);
		assertEquals("Missing Argument", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
		
		flexUITester.type("678910").at("contaCadastrarConta");
		flexUITester.click("okButtonCadastrarConta");
		flexUITester.wait(3);
		assertEquals("Conta cadastrada com sucesso!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
		
		assertEquals("teste", flexUITester.call("getNomeContas"));

		flexUITester.click("logoutButtonHome");
		flexUITester.wait(4);
		assertEquals("Até mais!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
	}

}