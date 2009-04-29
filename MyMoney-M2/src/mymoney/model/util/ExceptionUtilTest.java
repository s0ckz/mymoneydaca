package mymoney.model.util;

import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.MissingArgumentException;

import org.junit.Test;

import junit.framework.TestCase;

public class ExceptionUtilTest extends TestCase {
	
	@Test
	public void testMissingArgs() throws MissingArgumentException {
		String nullArg = null;
		String emptyArg = "";
		String nonEmptyArg = "blah";
		
		try {
			ExceptionUtil.checkMissingArguments("nullArg", nullArg);
			ExceptionUtil.checkMissingArguments("emptyArg", emptyArg);
			ExceptionUtil.checkMissingArguments("nullArg", nullArg, "emptyArg", emptyArg);
			ExceptionUtil.checkMissingArguments("nullArg", nullArg, "emptyArg", emptyArg, "nonEmptyArg", nonEmptyArg);
		} catch (MissingArgumentException e) {}
		
		ExceptionUtil.checkMissingArguments("nonEmptyArg", nonEmptyArg);
	}
	
	@Test
	public void testInvalidArgs() throws InvalidArgumentException {
		
		String validArg = "abc-def.ghi_jkl";
		
		String smallArg = "abc";
		String invalidChars = "!@#$%&";
		String[] invalidArgs = new String[]{"abc!", "abc ", "abc@", "abc#", "abc\"", "abc$", "abc%", "abc&", "abc=", "abc/", "abc?", "abc\\", "abc+", "abc-", "abc(", "abc)", "abc*"};
		
		
		try {
			ExceptionUtil.checkInvalidRequiredArguments("smallArg", smallArg);
			ExceptionUtil.checkInvalidRequiredArguments("invalidChars", invalidChars);
			for (String invalidArg : invalidArgs) {
				ExceptionUtil.checkInvalidRequiredArguments("invalidArg", invalidArg);	
			}
			ExceptionUtil.checkInvalidRequiredArguments("smallArg", smallArg, "invalidChars", invalidChars);
			ExceptionUtil.checkInvalidRequiredArguments("smallArg", smallArg, "invalidChars", invalidChars, "validArg", validArg);
		} catch (InvalidArgumentException e) {}
		
		
		ExceptionUtil.checkInvalidRequiredArguments("validArg", validArg);
		
	}
	
	@Test
	public void testInvalidEmail() throws InvalidEmailException {
		String emptyEmail = "";
		String validEmail = "leandro@lrl.com";
		String noPrefix = "@domain.com";
		String noDomain = "user@.com";
		String noSufix = "user@domain.";
		String noAt = "userdomain.com";
		String noDot = "user@domaincom";
		try {
			ExceptionUtil.checkEmail(noPrefix);
			ExceptionUtil.checkEmail(noDomain);
			ExceptionUtil.checkEmail(noSufix);
			ExceptionUtil.checkEmail(noAt);
			ExceptionUtil.checkEmail(noDot);
		} catch (InvalidEmailException e) {}
		ExceptionUtil.checkEmail(validEmail);
		ExceptionUtil.checkEmail(emptyEmail);
	}

}
