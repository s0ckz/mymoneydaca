package model.user;

public interface IUserManager {

	public void register(String login, String password, String name,
			String gender, String eMail);

	public String getUserName(String login);

	public String getUserGender(String login);

	public String getUserEmail(String login);

	public void removeUser(String login);
	
}
