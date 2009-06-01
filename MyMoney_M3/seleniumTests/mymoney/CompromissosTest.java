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
		assertEquals("teste - 300 - 01/01/2001 - Débito - Uma vez apenas", flexUITester.call("getCompromissos"));
		flexUITester.wait(1);
		flexUITester.click("sairButtonMostrarCompromissos");
		flexUITester.wait(1);
		
		flexUITester.click("logoutButtonHome");
		flexUITester.wait(4);
		assertEquals("Até mais!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
	}

//	public void testRemoveOperation() throws Exception {
//		flexUITester.type("leandro").at("login");
//		flexUITester.type("leandro").at("senha");
//		flexUITester.click("loginButton");
//		flexUITester.wait(4);
//		assertEquals("Seja bem-vindo!", flexUITester.call("getLastMessage"));
//		flexUITester.click("OK");
//
//		assertEquals("300 - 10/12/2008 - CHEQUE - DÉBITO,100 - 10/12/2007 - CARTÃO DE CRÉDITO - CRÉDITO", flexUITester.call("getTransacoes"));
//		
//		flexUITester.call("selecionaTransacao", "1");
//		flexUITester.call("removeTransacao");
//		flexUITester.wait(3);
//		assertEquals("300 - 10/12/2008 - CHEQUE - DÉBITO", flexUITester.call("getTransacoes"));
//		
//		flexUITester.call("selecionaTransacao", "0");
//		flexUITester.call("removeTransacao");
//		flexUITester.wait(3);
//		assertEquals("", flexUITester.call("getTransacoes"));
//
//		flexUITester.click("logoutButtonHome");
//		flexUITester.wait(4);
//		assertEquals("Até mais!", flexUITester.call("getLastMessage"));
//		flexUITester.click("OK");
//	}

}