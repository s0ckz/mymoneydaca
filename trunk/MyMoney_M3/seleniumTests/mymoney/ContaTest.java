package mymoney;

public class ContaTest extends MyMoneyTestCase {

	public void testCreateAccount() throws Exception {
		flexUITester.type("leandro").at("login");
		flexUITester.type("leandro").at("senha");
		flexUITester.click("loginButton");
		flexUITester.wait(4);
		assertEquals("Seja bem-vindo!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
		
		assertEquals("default", flexUITester.call("getNomeContas"));
		
		flexUITester.call("addConta");
		flexUITester.wait(1);
		
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
		
		assertEquals("default,teste", flexUITester.call("getNomeContas"));

		flexUITester.click("logoutButtonHome");
		flexUITester.wait(4);
		assertEquals("Ateh mais!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
	}

	public void testRemoveAccount() throws Exception {
		flexUITester.type("leandro").at("login");
		flexUITester.type("leandro").at("senha");
		flexUITester.click("loginButton");
		flexUITester.wait(4);
		assertEquals("Seja bem-vindo!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");

		assertEquals("default,teste", flexUITester.call("getNomeContas"));
		
		flexUITester.call("selecionaConta", "1"); // indice comecando de zero.
		flexUITester.call("removeConta");
		flexUITester.wait(3);
		assertEquals("default", flexUITester.call("getNomeContas"));

		flexUITester.click("logoutButtonHome");
		flexUITester.wait(4);
		assertEquals("Ateh mais!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
	}

}