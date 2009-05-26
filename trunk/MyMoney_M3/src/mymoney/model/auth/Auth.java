package mymoney.model.auth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

/**
 * Entidade que representa um par (login,senha) no sistema a fim de fazer autenticacao de usuarios.
 * <br>
 * Essa classe obedece ao padrao JavaBean.
 * 
 *
 */
@Entity
@Table(name="Auth")
public class Auth implements Serializable {
	
	private static final long serialVersionUID = 8449558455878716745L;

	private String login;
	
	private String password;
	
	private boolean isLoggedIn;
	
	/**
	 * Construtor default.
	 */
	public Auth() {
	}

	/**
	 * Instancia uma entidade de autenticacao com todos os seus parametros.
	 * 
	 * @param login Login do usuario.
	 * @param password Senha do usuario.
	 * @param isLoggedIn Indica se o usuario estah logado no sistema.
	 */
	public Auth(String login, String password, boolean isLoggedIn) {
		this.login = login;
		this.password = password;
	}

	/**
	 * Recupera login do usuario.
	 * 
	 * @return O login do usuario.
	 */
	@Id
	@ForeignKey(name = "login", inverseName="login")
	@Column(name = "login")
	public String getLogin() {
		return login;
	}

	/**
	 * Atualiza o login do usuario.
	 * 
	 * @param login O novo login.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Recupera a senha do usuario.
	 * 
	 * @return A senha do usuario.
	 */
	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	/**
	 * Atualiza a senha do usuario.
	 * 
	 * @param password A nova senha.
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return <code>true</code> se o usuario estiver logado no sistema no instante da chamada desse metodo. Caso
	 * contrario, retorna </false>
	 * 
	 */
	@Column(name = "isLoggedIn")
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	/**
	 * Atualiza estado do login do usuario no sistema. 
	 *  
	 * @param isLoggedIn Se o usuario estiver logado. 
	 */
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

}
