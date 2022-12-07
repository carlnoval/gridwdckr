package login;

import baseTest.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SecureAreaPage;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {
    @Test
    public void successfulLoginTest() {
        String USERNAME = "tomsmith";
        String PASSWORD = "SuperSecretPassword!";

        LoginPage loginPage = basePage.clickFormAuthentication();       // inherited from BaseTest, change page
        loginPage.setUsername(USERNAME);                                // enter credentials
        loginPage.setPassword(PASSWORD);
        SecureAreaPage secureAreaPage = loginPage.clickLoginButton();   // another change page

        assertEquals(secureAreaPage.getAlertText(),"You logged into a secure area!", "Error: After login banner message does not match.");
    }
}
