package mymoney.model.user;

import mymoney.model.exceptions.DuplicatedLoginException;
import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.UserUnregisteredException;

public interface UserManager {

	public void register(String login, String name,
			String gender, String eMail) throws MissingArgumentException, InvalidArgumentException, InvalidEmailException, DuplicatedLoginException;

	public String getUserName(String login) throws UserUnregisteredException;

	public String getUserGender(String login) throws UserUnregisteredException;

	public String getUserEmail(String login) throws UserUnregisteredException;

	public void removeUser(String login) throws UserUnregisteredException;

	public void updateUser(String login, String name, String gender, String email) throws UserUnregisteredException, MissingArgumentException, InvalidEmailException, InvalidArgumentException;
	
}
