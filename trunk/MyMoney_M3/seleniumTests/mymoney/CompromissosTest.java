package mymoney;

public class CompromissosTest extends MyMoneyTestCase {

	public void testCreateOperation() throws Exception {
		flexUITester.type("leandro").at("login");
		flexUITester.type("leandro").at("senha");
		flexUITester.click("loginButton");
		flexUITester.wait(4);
		assertEquals("Seja bem-vindo!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
		
		flexUITester.call("mostrarCompromissos");
		flexUITester.wait(1);
		assertEquals("", flexUITester.call("getCompromissos"));
		flexUITester.wait(1);
		flexUITester.click("sairButtonMostrarCompromissos");
		flexUITester.wait(1);
		
		flexUITester.call("addCompromisso");
		flexUITester.wait(1);
		
		flexUITester.click("okButtonAdicionarCompromisso");
		flexUITester.wait(3);
		assertEquals("Missing Argument", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
		
		flexUITester.type("300").at("valorAdicionarCompromisso");
		flexUITester.click("okButtonAdicionarCompromisso");
		flexUITester.wait(3);
		assertEquals("Missing Argument", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
		
		flexUITester.type("teste").at("descricaoAdicionarCompromisso");
		flexUITester.click("okButtonAdicionarCompromisso");
		flexUITester.wait(3);
		assertEquals("Missing Argument", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");

		flexUITester.type("01/01/2001").at("dataAdicionarCompromisso");
		flexUITester.click("okButtonAdicionarCompromisso");
		flexUITester.wait(3);
		assertEquals("Compromisso adicionado com sucesso!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
		
		flexUITester.call("mostrarCompromissos");
		flexUITester.wait(1);
		assertEquals("teste - 300 - 01/01/2001 - Debito - Uma vez apenas", flexUITester.call("getCompromissos"));
		flexUITester.wait(1);
		flexUITester.click("sairButtonMostrarCompromissos");
		flexUITester.wait(1);
		
		flexUITester.call("addCompromisso");
		flexUITester.wait(1);
		flexUITester.type("300").at("valorAdicionarCompromisso");
		flexUITester.type("teste").at("descricaoAdicionarCompromisso");
		flexUITester.type("01/01/2001").at("dataAdicionarCompromisso");
		flexUITester.click("semanalAdicionarCompromisso");
		flexUITester.click("creditoAdicionarCompromisso");
		flexUITester.click("okButtonAdicionarCompromisso");
		flexUITester.wait(3);
		assertEquals("Compromisso adicionado com sucesso!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");

		flexUITester.call("mostrarCompromissos");
		flexUITester.wait(1);
		assertEquals("teste - 300 - 01/01/2001 - Debito - Uma vez apenas,teste - 300 - 01/01/2001 - Credito - Semanal", flexUITester.call("getCompromissos"));
		flexUITester.wait(1);
		flexUITester.click("sairButtonMostrarCompromissos");
		flexUITester.wait(1);
		
		flexUITester.click("logoutButtonHome");
		flexUITester.wait(4);
		assertEquals("Ateh mais!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
	}

	public void testRemoveOperation() throws Exception {
		flexUITester.type("leandro").at("login");
		flexUITester.type("leandro").at("senha");
		flexUITester.click("loginButton");
		flexUITester.wait(4);
		assertEquals("Seja bem-vindo!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");

		flexUITester.call("mostrarCompromissos");
		flexUITester.wait(1);
		assertEquals("teste - 300 - 01/01/2001 - Debito - Uma vez apenas,teste - 300 - 01/01/2001 - Credito - Semanal", flexUITester.call("getCompromissos"));
		
		flexUITester.call("selecionaCompromisso", "1");
		flexUITester.call("removeCompromisso");
		flexUITester.wait(3);
		assertEquals("Compromisso removido com sucesso!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");

		assertEquals("teste - 300 - 01/01/2001 - Debito - Uma vez apenas", flexUITester.call("getCompromissos"));
		flexUITester.wait(1);
		
		flexUITester.call("selecionaCompromisso", "0");
		flexUITester.call("removeCompromisso");
		flexUITester.wait(3);
		assertEquals("Compromisso removido com sucesso!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");

		assertEquals("", flexUITester.call("getCompromissos"));
		flexUITester.wait(1);

		flexUITester.click("logoutButtonHome");
		flexUITester.wait(4);
		assertEquals("Ateh mais!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
	}

}