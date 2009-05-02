package mymoney.model.exceptions;

/**
 * Excecao lancada quando um login nao esta registrado no sistema
 * 
 * @author Danilo Resende
 * @author Leandro Jose
 * @author Rodrigo Bruno
 */
public class LoginUnregisteredException extends MyMoneyException {

	private static final long serialVersionUID = -2297487823651798288L;

	public LoginUnregisteredException() {
		super("Login unregistered");
	}

}
