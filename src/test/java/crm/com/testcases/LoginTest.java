package crm.com.testcases;

import crm.com.common.BaseTest;
import crm.com.pages.DashBoardPage;
import crm.com.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test(priority = 1)
    public void verifyLoginPageIsDisplayed() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed");
    }

    @Test(priority = 2)
    public void verifyLoginSuccessWithValidEmailAndPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.loginCRM("admin@example.com", "123456");


        //verify login successfully by checking dashboard page url
        DashBoardPage dashBoardPage = new DashBoardPage(driver);;
        boolean isUrlExposed = dashBoardPage.getWaitedCurrentUrl("https://crm.anhtester.com/admin/");
        Assert.assertTrue(isUrlExposed, "Login CRM failed: Expect dashboard url but not found");


        //verify login successfully by checking dashboard page header
        Assert.assertTrue(dashBoardPage.isDashboardOptionsDisplayed(),"Login CRM failed: Expect dashboard options button but not found");

    }
    @Test(priority = 3)
    public void verifyLoginFailedWithInvalidEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.loginCRM("admin@example.com123", "123456");
        String actualErrorMessage = loginPage.getErrorMessageInvalidEmailAndPasswordDisplayed();

        String expectedErrorMessage = "Invalid email or password";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Invalid email error message is not displayed");
    }

    @Test(priority = 4)
    public void verifyLoginFailedWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.loginCRM("admin@example.com", "1234567");
        String actualErrorMessage = loginPage.getErrorMessageInvalidEmailAndPasswordDisplayed();

        String expectedErrorMessage = "Invalid email or password";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Invalid password error message is not displayed");
    }
    @Test(priority = 5)
    public void verifyLoginFailedWithNullEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.loginCRM("", "123456");
        String actualErrorMessage = loginPage.getErrorMessageNullEmailDisplayed();
        String expectedErrorMessage = "The Email Address field is required.";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Required email error message is not displayed");

    }
    @Test(priority = 6)
    public void verifyLoginFailedWithNullPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.loginCRM("admin@example.com", "");
        String actualErrorMessage = loginPage.getErrorMessageNullPasswordDisplayed();
        String expectedErrorMessage = "The Password field is required.";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Required password error message is not displayed");

    }
}
