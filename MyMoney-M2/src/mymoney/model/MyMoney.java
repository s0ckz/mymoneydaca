package mymoney.model;

import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.MissingArgumentException;

public interface MyMoney {

	void registerUser(String login, String password, String name,
			String gender, String mail) throws MissingArgumentException, InvalidArgumentException, InvalidEmailException;

	void removeUser(String login);

	String getUserEmail(String login);

	String getUserGender(String login);

	String getUserName(String login);

}
