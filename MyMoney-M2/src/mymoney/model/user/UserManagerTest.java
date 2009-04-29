package mymoney.model.user;

import junit.framework.Assert;
import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.MissingArgumentException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserManagerTest {
	
	private static final String EMAIL = "user@domain.com";
	private static final String GENDER = "M";
	private static final String PASSWORD = "1234";
	private static final String LOGIN = "user";
	private static final String NAME = "name";
	private UserManager manager;
	
	@Before
	public void setUp() {
		manager = new UserManagerImpl();
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testCreate() throws MissingArgumentException, InvalidArgumentException, InvalidEmailException {
		manager.register(LOGIN, PASSWORD,NAME, GENDER, EMAIL);
		Assert.assertEquals(NAME, manager.getUserEmail(LOGIN));
		Assert.assertEquals(EMAIL, manager.getUserEmail(LOGIN));
		Assert.assertEquals(GENDER, manager.getUserGender(LOGIN));
	}
	
}
