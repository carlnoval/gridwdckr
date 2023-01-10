package login;

import baseTest.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SecureAreaPage;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {
    @Test
    @Description("Test valid login")
    public void successfulLoginTest() {
        String USERNAME = "tomsmith";
        String PASSWORD = "SuperSecretPassword!";

        Allure.step("clickFormAuthentication");
        LoginPage loginPage = basePage.clickFormAuthentication();

        Allure.step("Enter credentials and click on login");
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);

        Allure.step("Click on login");
        SecureAreaPage secureAreaPage = loginPage.clickLoginButton();

        Allure.step("Check if user is able to login");
        assertEquals(secureAreaPage.getAlertText(),"You logged into a secure area!", "Error: After login banner message does not match.");
    }
}
