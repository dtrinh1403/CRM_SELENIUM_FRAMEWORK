package crm.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
    private String urlLoginPage = "https://crm.anhtester.com/admin/authentication";
    private By logoLoginPage = By.xpath("//a[contains(@class,'logo')]");
    private By headerLoginPage = By.xpath("//h1[normalize-space()='Login'] ");
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By loginButton = By.xpath("//button[normalize-space()='Login']");
    private By checkboxRememberMe = By.xpath("//input[@id='remember']");
    private By linkForgotPassword = By.xpath("//a[normalize-space()='Forgot Password?']");
    private By errorMessageInvalidEmailOrPassword = By.xpath("//div[contains(@class,'alert-danger') and contains(.,'Invalid')]");
    private By errorMessageNullEmail = By.xpath("//div[contains(@class,'alert-danger') and normalize-space() = 'The Email Address field is required.']");
    private By errorMessageNullPassword = By.xpath("//div[contains(@class,'alert-danger') and normalize-space() = 'The Password field is required.']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    //Actions
    public void navigateToLoginPage() {
        driver.get(urlLoginPage);
    }

    public void enterEmail(String email) {
        enterText(inputEmail, email);
    }
    public void enterPassword(String password) {
        enterText(inputPassword, password);
    }
    public void clickLoginButton() {
        clickElement(loginButton);
    }

    //Verify helpers
    public String getErrorMessageInvalidEmailAndPasswordDisplayed() {

        try {
            return getText(errorMessageInvalidEmailOrPassword);
        } catch (Exception e) {
            //empty string is returned if error message is not found, to avoid NoSuchElementException
            return "";
        }
    }
    public String getErrorMessageNullEmailDisplayed() {

        try {
            return waitForVisibility(errorMessageNullEmail).getText();
        } catch (Exception e) {
            return "";
        }
    }
    public String getErrorMessageNullPasswordDisplayed() {

        try {
            return getText(errorMessageNullPassword);
        } catch (Exception e) {
            return "";
        }
    }
    public boolean isLoginPageDisplayed() {
        return driver.findElement(logoLoginPage).isDisplayed() && driver.findElement(headerLoginPage).isDisplayed();
    }

    //Flow
    public void loginCRM(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

}
