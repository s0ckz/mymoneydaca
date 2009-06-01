package mymoney;

public class LoginTest extends MyMoneyTestCase {
	public void testInvalidLogin() throws Exception {
		flexUITester.click("loginButton");
		flexUITester.wait(3);
		flexUITester.click("OK");
		assertEquals("Invalid Argument", flexUITester.call("getLastMessage"));
		
		flexUITester.type("wrongUser").at("login");
		flexUITester.click("loginButton");
		flexUITester.wait(3);
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
		assertEquals("A senha nao confere!", flexUITester.call("getLastMessage"));

		flexUITester.type("leandro").at("confirmarSenha");
		flexUITester.click("okButton");
		flexUITester.wait(3);
		flexUITester.click("OK");
		assertEquals("Invalid e-mail", flexUITester.call("getLastMessage"));

		flexUITester.type("leandro@lrl.com.br").at("email");
		flexUITester.click("okButton");
		flexUITester.wait(3);
		flexUITester.click("OK");
		assertEquals("Usuario cadastrado com sucesso!", flexUITester.call("getLastMessage"));

		flexUITester.type("leandro").at("login");
		flexUITester.type("leandro").at("senha");
		flexUITester.click("loginButton");
		flexUITester.wait(4);
		assertEquals("Seja bem-vindo!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");

		flexUITester.click("logoutButtonHome");
		flexUITester.wait(4);
		assertEquals("Ateh mais!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
	}

}