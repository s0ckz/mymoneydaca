package mymoney;

public class TransacaoTest extends MyMoneyTestCase {

	public void testCreateOperation() throws Exception {
		flexUITester.type("leandro").at("login");
		flexUITester.type("leandro").at("senha");
		flexUITester.click("loginButton");
		flexUITester.wait(4);
		assertEquals("Seja bem-vindo!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
		
		assertEquals("", flexUITester.call("getTransacoes"));
		
		flexUITester.call("addTransacao");
		
		flexUITester.click("okButtonAdicionarTransacao");
		flexUITester.wait(3);
		assertEquals("Digite o valor da transação!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
		
		flexUITester.type("abcd").at("valorAdicionarTransacao");
		flexUITester.click("okButtonAdicionarTransacao");
		flexUITester.wait(3);
		assertEquals("Digite o valor da transação!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
		
		flexUITester.type("300").at("valorAdicionarTransacao");
		flexUITester.click("okButtonAdicionarTransacao");
		flexUITester.wait(3);
		assertEquals("The date format is invalid", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");

		flexUITester.click("debitoAdicionarTransacao");
		flexUITester.click("chequeAdicionarTransacao");
		flexUITester.type("10/12/2008").at("dataAdicionarTransacao");
		flexUITester.click("okButtonAdicionarTransacao");
		flexUITester.wait(3);
		
		assertEquals("300 - 10/12/2008 - CHEQUE - DÉBITO", flexUITester.call("getTransacoes"));
		
		flexUITester.call("addTransacao");
		flexUITester.type("100").at("valorAdicionarTransacao");
		flexUITester.click("crebitoAdicionarTransacao");
		flexUITester.click("cartaoAdicionarTransacao");
		flexUITester.type("10/12/2007").at("dataAdicionarTransacao");
		flexUITester.click("okButtonAdicionarTransacao");
		flexUITester.wait(3);
		
		assertEquals("300 - 10/12/2008 - CHEQUE - DÉBITO,100 - 10/12/2007 - CARTÃO DE CRÉDITO - CRÉDITO", flexUITester.call("getTransacoes"));
		
		flexUITester.click("logoutButtonHome");
		flexUITester.wait(4);
		assertEquals("Até mais!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
	}

	public void testRemoveOperation() throws Exception {
		flexUITester.type("leandro").at("login");
		flexUITester.type("leandro").at("senha");
		flexUITester.click("loginButton");
		flexUITester.wait(4);
		assertEquals("Seja bem-vindo!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");

		assertEquals("300 - 10/12/2008 - CHEQUE - DÉBITO,100 - 10/12/2007 - CARTÃO DE CRÉDITO - CRÉDITO", flexUITester.call("getTransacoes"));
		
		flexUITester.call("selecionaTransacao", "1");
		flexUITester.call("removeTransacao");
		flexUITester.wait(3);
		assertEquals("300 - 10/12/2008 - CHEQUE - DÉBITO", flexUITester.call("getTransacoes"));
		
		flexUITester.call("selecionaTransacao", "0");
		flexUITester.call("removeTransacao");
		flexUITester.wait(3);
		assertEquals("", flexUITester.call("getTransacoes"));

		flexUITester.click("logoutButtonHome");
		flexUITester.wait(4);
		assertEquals("Até mais!", flexUITester.call("getLastMessage"));
		flexUITester.click("OK");
	}

}