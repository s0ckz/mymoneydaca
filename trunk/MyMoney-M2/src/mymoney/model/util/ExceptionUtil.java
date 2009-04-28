package mymoney.model.util;

import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.MissingArgumentException;

public class ExceptionUtil {
	
	private static final String DOT = "\\.";
	private static final String HIFEN = "\\-";
	private static final String UNDERLINE = "_";
	private static final String ALPHA_NUMERIC_CHAR = "\\w";
	private static final String VALID_FIELD_REGEX = "[" + ALPHA_NUMERIC_CHAR + UNDERLINE + HIFEN + DOT + "]{4,}";
	
	private static final String EMAIL_REGEX = ".+@.+\\.[a-z]+";

	public static boolean isNuloOuVazio(String param) {
		return param == null || param.equals(EMAIL_REGEX);
	}
	
	/**
	 * Usage: To ensure arguments are not null nor empty, call this method passing a 
	 * Varargs parameter which is composed by a sequence of pairs: argument name and argument value.
	 *  
	 * @throws MissingArgumentException If any argument is null or an empty string. 
	 */
	public static void checkMissingArguments(String... args) throws MissingArgumentException {
		assert args.length % 2 == 0;
		for (int i = 0; i < args.length; i+=2) {
			if (args[i+1] == null || args[i+1].equals(EMAIL_REGEX)) {
				throw new MissingArgumentException(args[i]);
			}
		}
	}
	
	/**
	 * Usage: To ensure arguments are valid, call this method passing a 
	 * Varargs parameter which is composed by a sequence of pairs: argument name and argument value.
	 *  
	 * @throws InvalidArgumentException If any argument is not valid. 
	 */
	public static void checkInvalidArguments(String... args) throws InvalidArgumentException {
		assert args.length % 2 == 0;
		for (int i = 0; i < args.length; i+=2) {
			if (args[i+1] != null && args[i+1].matches(VALID_FIELD_REGEX)) {
				throw new InvalidArgumentException(args[i]);
			}
		}
	}

	public static void checkEmail(String email) throws InvalidEmailException {
		if (email != null && !email.matches(EMAIL_REGEX)) {
			throw new InvalidEmailException();
		}
	}

}
