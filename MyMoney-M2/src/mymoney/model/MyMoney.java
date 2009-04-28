package mymoney.model;

public interface MyMoney {

	void registerUser(String login, String password, String name,
			String gender, String mail);

	void removeUser(String login);

	String getUserEmail(String login);

	String getUserGender(String login);

	String getUserName(String login);

}
