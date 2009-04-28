package mymoney.model.user;

import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.MissingArgumentException;

public interface UserManager {

	public void register(String login, String password, String name,
			String gender, String eMail) throws MissingArgumentException, InvalidArgumentException, InvalidEmailException;

	public String getUserName(String login);

	public String getUserGender(String login);

	public String getUserEmail(String login);

	public void removeUser(String login);
	
}
