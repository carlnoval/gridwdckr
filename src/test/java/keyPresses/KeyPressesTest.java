package keyPresses;

import baseTest.BaseTest;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import pages.KeyPressesPage;

import static org.testng.Assert.assertEquals;

public class KeyPressesTest extends BaseTest {
    @Test
    public void keyPressesTest() {
        KeyPressesPage keyPressesPage = basePage.clickKeyPressesPage();

        // send backspace key
        keyPressesPage.sendKeyPresses(String.format("%s", Keys.BACK_SPACE));
        assertEquals(keyPressesPage.getFeedbackResult(), "You entered: BACK_SPACE", "Error: result does not match sent key.");

        // send pi character plus other characters
        keyPressesPage.sendKeyPresses(Keys.chord(Keys.ALT, "p") + "=3.14");
        assertEquals(keyPressesPage.getFeedbackResult(), "You entered: 4", "Error: result does not match sent key.");
    }
}
