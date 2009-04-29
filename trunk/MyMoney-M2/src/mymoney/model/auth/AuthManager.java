package mymoney.model.auth;

public interface AuthManager {

	void register(String login, String password);

	void doLogin(String login, String password);

}
