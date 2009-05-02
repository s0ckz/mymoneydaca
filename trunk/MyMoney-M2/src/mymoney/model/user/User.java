package mymoney.model.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidade que representa um usuario no sistema. 
 * Um usuario contem os seguintes atributos: <i>login</i>, <i>nome</i>, <i>genero</i> e <i>e-mail</i>.
 * <br>
 * Essa classe obedece ao padrao JavaBean.
 *
 */
@Entity
@Table(name="Users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 2177626984202740302L;

	private String login;
	
	private String name;
	
	private String gender;
	
	private String email;
	
	public User() {
	}

	/**
	 * Instancia um usuario com todos os seus atributos.
	 * 
	 * @param login O login do usuario.
	 * @param name O nome do usuario.
	 * @param gender O genero do usuario.
	 * @param email O e-mail do usuario.
	 */
	public User(String login, String name, String gender, String email) {
		this.login = login;
		this.name = name;
		this.gender = gender;
		this.email = email;
	}

	/**
	 * Recupera o login do usuario.
	 * @return O login do usuario.
	 */
	@Id
	@Column(name = "login")
	public String getLogin() {
		return login;
	}

	/**
	 * Atualiza o login do usuario.
	 * @return O novo login.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Recupera o nome do usuario.
	 * @return O nome do usuario.
	 */
	@Column(name = "name")
	public String getName() {
		return name;
	}

	/**
	 * Atualiza o nome do usuario.
	 * @param name O novo nome.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Recupera o sexo do usuario.
	 * @return O sexo desse usuario.
	 */
	@Column(name = "gender")
	public String getGender() {
		return gender;
	}

	/**
	 * Atualiza o sexo do usuario.
	 * Lembre-se que o usuario pode errar na hora do cadastro e querer modificar depois =). 
	 * @param gender O novo sexo.
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Recupera o e-mail do usuario.
	 * @return O e-mail do usuario.
	 */
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	/**
	 * Atualiza o e-mail do usuario.
	 * @param email O novo e-mail.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString() {
		return "login: " + login + ", name: " + name + ", email: " + email + ", gender: " + gender;
	}
	
}
