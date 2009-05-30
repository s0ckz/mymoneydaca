

import junit.framework.TestCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.FlashSelenium;
import com.thoughtworks.selenium.Selenium;

public class ColorsTest extends TestCase {

        private FlashSelenium flashApp;
        private Selenium selenium;

        private final static String GREEN = "GREEN";
        private final static String BLUE = "BLUE";
        private final static String RED = "RED";
        private final static String URL = "http://www.geocities.com/paulocaroli/flash/colors.html";
        
        public void setUp() {
                selenium = new DefaultSelenium("localhost", 4444, "*firefox",URL);
                selenium.start();
                flashApp = new FlashSelenium(selenium, "coloredSquare");
                selenium.open(URL);
                try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                assertEquals(100, flashApp.PercentLoaded());
        }

        public void tearDown() {
                selenium.stop();
        }
        
        public void testColorTransition() {
                assertEquals("Clicking Colors", selenium.getTitle());
                assertEquals(GREEN, flashApp.call("getColor"));
                assertEquals("(Click here)", flashApp.call("getSquareLabel"));
                flashApp.call("click");
                assertEquals(BLUE, flashApp.call("getColor"));
                assertEquals(BLUE, flashApp.call("getSquareLabel"));
                flashApp.call("click");
                assertEquals(RED, flashApp.call("getColor"));
                assertEquals(RED, flashApp.call("getSquareLabel"));
                flashApp.call("click");
                assertEquals(GREEN, flashApp.call("getColor"));
                assertEquals(GREEN, flashApp.call("getSquareLabel"));
        }
        
        public void testRectangleLabel() {
                assertEquals("(Click here)", flashApp.call("getSquareLabel"));
                flashApp.call("setSquareLabel", "Dummy Label");
                assertEquals("Dummy Label", flashApp.call("getSquareLabel"));
        }
}