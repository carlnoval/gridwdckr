package keyPresses;

import baseTest.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import pages.KeyPressesPage;

import static org.testng.Assert.assertEquals;

public class KeyPressesTest extends BaseTest {
    @Test
    @Description("Test last key pressed")
    public void keyPressesTest() {
        Allure.step("clickKeyPressesPage");
        KeyPressesPage keyPressesPage = basePage.clickKeyPressesPage();

        Allure.step("Send backspace key");
        keyPressesPage.sendKeyPresses(String.format("%s", Keys.BACK_SPACE));
        Allure.step("Check that BACK_SPACE was entered");
        assertEquals(keyPressesPage.getFeedbackResult(), "You entered: BACK_SPACE", "Error: result does not match sent key.");

        Allure.step("Send these characters: '=3.14'");
        keyPressesPage.sendKeyPresses(Keys.chord(Keys.ALT, "p") + "=3.14");
        Allure.step("Check that '4' was the last entered character");
        assertEquals(keyPressesPage.getFeedbackResult(), "You entered: 4", "Error: result does not match sent key.");
    }
}
