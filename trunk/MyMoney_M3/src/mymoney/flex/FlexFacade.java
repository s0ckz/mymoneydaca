package mymoney.flex;

import mymoney.model.MyMoney;
import mymoney.model.MyMoneyImpl;
import mymoney.model.exceptions.DuplicatedLoginException;
import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.MissingArgumentException;

public class FlexFacade {
	
	private MyMoney fachada; 
	
	public FlexFacade() {
		fachada = new MyMoneyImpl();
	}

	public String teste() {
		return "teste";
	}
	
	public void registerUser(String login, String password, String name,
			String gender, String mail) throws MissingArgumentException, InvalidArgumentException, InvalidEmailException, DuplicatedLoginException {
		fachada.registerUser(login, password, name, gender, mail);
	}

}
