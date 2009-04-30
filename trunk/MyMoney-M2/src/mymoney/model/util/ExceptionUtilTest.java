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
		} catch (MissingArgumentException e) {}

		try {
			ExceptionUtil.checkMissingArguments("emptyArg", emptyArg);
		} catch (MissingArgumentException e) {}
		try {
			ExceptionUtil.checkMissingArguments("nullArg", nullArg, "emptyArg", emptyArg);
		} catch (MissingArgumentException e) {}
		try {
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
		} catch (InvalidArgumentException e) {}
		try {
			ExceptionUtil.checkInvalidRequiredArguments("invalidChars", invalidChars);
		} catch (InvalidArgumentException e) {}
			for (String invalidArg : invalidArgs) {
				try {
					ExceptionUtil.checkInvalidRequiredArguments("invalidArg", invalidArg);
				} catch (InvalidArgumentException e) {}
			}
			try {
				ExceptionUtil.checkInvalidRequiredArguments("smallArg", smallArg, "invalidChars", invalidChars);
			} catch (InvalidArgumentException e) {}
			try {
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
		} catch (InvalidEmailException e) {}
		
		try {
			ExceptionUtil.checkEmail(noDomain);
		} catch (InvalidEmailException e) {}
		
		try {
			ExceptionUtil.checkEmail(noSufix);
		} catch (InvalidEmailException e) {}
		try {
			ExceptionUtil.checkEmail(noAt);
		} catch (InvalidEmailException e) {}
		try {
			ExceptionUtil.checkEmail(noDot);
		} catch (InvalidEmailException e) {}
		
		ExceptionUtil.checkEmail(validEmail);
		ExceptionUtil.checkEmail(emptyEmail);
	}

}
