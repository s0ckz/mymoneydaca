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
		 selenium.stop();
	}

	public void testInvalidLogin() throws Exception {
		flexUITester.click("loginButton");
		flexUITester.wait(1);
		flexUITester.click("OK");
		assertEquals("Invalid Argument", flexUITester.call("getLastMessage"));
		
		flexUITester.type("wrongUser").at("login");
		flexUITester.click("loginButton");
		flexUITester.wait(1);
		flexUITester.click("OK");
		assertEquals("Invalid Argument", flexUITester.call("getLastMessage"));
		
		flexUITester.type("wrongPassword").at("senha");
		flexUITester.click("loginButton");
		flexUITester.wait(4);
		flexUITester.click("OK");
		assertEquals("Login unregistered", flexUITester.call("getLastMessage"));
	}

	public void testCreateUser() throws Exception {
		flexUITester.call("cleanAll");
		flexUITester.wait(3);
		
		flexUITester.click("cadastrarButton");
		flexUITester.wait(1);
		
		flexUITester.click("okButton");
		flexUITester.wait(3);
		flexUITester.click("OK");
		assertEquals("Missing Argument", flexUITester.call("getLastMessage"));

		flexUITester.type("leandro").at("nome");
		flexUITester.type("leandro").at("loginCadastro");
		flexUITester.type("leandro").at("senhaCadastro");
		flexUITester.type("leandroErrado").at("confirmarSenha");
		flexUITester.type("leandro@").at("email");
		flexUITester.click("okButton");
		flexUITester.wait(1);
		flexUITester.click("OK");
		assertEquals("A senha não confere!", flexUITester.call("getLastMessage"));

		flexUITester.type("leandro").at("confirmarSenha");
		flexUITester.click("okButton");
		flexUITester.wait(3);
		flexUITester.click("OK");
		assertEquals("Invalid e-mail", flexUITester.call("getLastMessage"));

		flexUITester.type("leandro@lrl.com.br").at("email");
		flexUITester.click("okButton");
		flexUITester.wait(3);
		flexUITester.click("OK");
		assertEquals("Usuário cadastrado com sucesso!", flexUITester.call("getLastMessage"));

		flexUITester.type("leandro").at("login");
		flexUITester.type("leandro").at("senha");
		flexUITester.click("loginButton");
		flexUITester.wait(4);
		assertEquals("Seja bem-vindo!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");

		flexUITester.click("logoutButtonHome");
		flexUITester.wait(4);
		assertEquals("Até mais!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
	}

}