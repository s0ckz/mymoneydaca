package mymoney.model;

import mymoney.MyMoneyFacade;



public class Main {
	
	public static void main(String[] args) {
		MyMoneyFacade facade = new MyMoneyFacade();
		String login = "user";
		// tah fechando a sessao antes de fazer get, dai dah pau oO
		System.out.println(facade.getUserEmail(login));
		System.out.println(facade.getUserGender(login));
		System.out.println(facade.getUserName(login));
//		facade.removeUser("user");
//		facade.register("user", "123", "Joao Baitola", "female", "user@user.com");
	}

}
