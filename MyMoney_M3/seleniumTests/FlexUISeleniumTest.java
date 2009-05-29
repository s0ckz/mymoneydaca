import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.FlexUISelenium;
import com.thoughtworks.selenium.Selenium;

public class FlexUISeleniumTest {
        private final static String URL = "http://localhost:8080/MyMoney/";
        private Selenium selenium;
        private FlexUISelenium flexUITester;
        
        @Before
        public void setUp() throws Exception {
                selenium = new DefaultSelenium("localhost", 4444, "*firefox",URL);
                selenium.start();
                selenium.open(URL);
                flexUITester = new FlexUISelenium(selenium, "MyMoneyId");
        }

        @After
        public void tearDown() throws Exception {
//                selenium.stop();
        }

        @Test
        public void verifyFlexAppSumIsCorrect() throws Exception {
                flexUITester.type("teste").at("login");
//                flexUITester.type("3").at("arg2");
//                flexUITester.click("submit");
//                assertEquals("5", flexUITester.readFrom("result"));             
        }
        
}