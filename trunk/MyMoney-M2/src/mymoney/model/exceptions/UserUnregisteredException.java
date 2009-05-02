package mymoney.model.exceptions;

/**
 * Excecao lancada quando o usuario tentando fazer login nao estao cadastrado no
 * sistema
 * 
 * @author Danilo Resende
 * @author Leandro Jose
 * @author Rodrigo Bruno
 */
public class UserUnregisteredException extends Exception {

	private static final long serialVersionUID = -1332936091956433477L;

	public UserUnregisteredException() {
		super("User unregistered");
	}

}
