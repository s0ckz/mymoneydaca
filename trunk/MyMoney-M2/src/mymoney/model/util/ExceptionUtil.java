package mymoney.model.util;

import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.MissingArgumentException;

/**
 * Prove metodos para a verificacao de regras de negocio do MyMoney. Em caso
 * de violacao de alguma regra serah lancada uma excecao correspondente.
 *
 */
public class ExceptionUtil {
	
	private static final String DOT = "\\.";
	private static final String HIFEN = "\\-";
	private static final String UNDERLINE = "_";
	private static final String ALPHA_NUMERIC_CHAR = "\\w";
	private static final String VALID_FIELD_REGEX = "[" + ALPHA_NUMERIC_CHAR + UNDERLINE + HIFEN + DOT + "]{4,}";
	
	private static final String EMAIL_REGEX = ".+@.+\\.[a-z]+";

	/**
	 * Teste se um dado parametro eh nulo ou vazio.
	 * @param param O valor do parametro a ser testado.
	 * @return <code>true</code> se o parametro for nulo ou vazio, caso contrario retorna <code>false</code>.
	 */
	public static boolean isNuloOuVazio(String param) {
		return param == null || param.equals(EMAIL_REGEX);
	}
	
	/**
	 * Uso: Para garantir que argumentos nao sao nulos nem vazios, chame esse metodo
	 * passando parametros (Varargs) que sao uma sequencia de pares: nome e valor de cada argumento. 
	 *  
	 * @throws MissingArgumentException Se algum argumento for nulo ou um string vazio. 
	 */
	public static void checkMissingArguments(String... args) throws MissingArgumentException {
		assert args.length % 2 == 0;
		for (int i = 0; i < args.length; i+=2) {
			if (args[i+1] == null || args[i+1].isEmpty()) {
				throw new MissingArgumentException();
			}
		}
	}
	
	/**
	 * Uso: Para garantir que argumentos obrigatorios sao validos, chame esse metodos passando 
	 * parametros (Varargs) que sao uma sequencia de pares: nome e valor de cada argumento.
	 *  
	 * @throws InvalidArgumentException Se algum argumento nao for valido. 
	 */
	public static void checkInvalidRequiredArguments(String... args) throws InvalidArgumentException {
		assert args.length % 2 == 0;
		for (int i = 0; i < args.length; i+=2) {
			if (args[i+1] != null && !args[i+1].matches(VALID_FIELD_REGEX)) {
				throw new InvalidArgumentException();
			}
		}
	}

	/**
	 * Verifica se um dado e-mail eh valido. Um e-mail eh considerado valido se 
	 * ele for vazio ou obedecer ao seguinte padrao: 
	 * <br>$usuario@$dominio.$extensao, em que $usuario e $dominio devem ser uma 
	 * cadeira de caracteres qualquer de tamanho maior ou igual a 1 e $extensao 
	 * deve ser uma cadeia de letras minusculas. 
	 * 
	 * @param email O e-mail a ser avaliado.
	 * @throws InvalidEmailException Se o e-mail for invalido.
	 */
	public static void checkEmail(String email) throws InvalidEmailException {
		if (email != null && !email.isEmpty() && !email.matches(EMAIL_REGEX)) {
			throw new InvalidEmailException();
		}
	}
}
