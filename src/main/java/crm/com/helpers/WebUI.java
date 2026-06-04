package crm.com.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebUI {
    private static int WAIT_TIMEOUT_SECONDS = 10;

    public static WebElement waitForVisibility(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static void clickElement(WebDriver driver, By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    public static void enterText(WebDriver driver, By locator, String text){
        waitForVisibility(driver, locator);
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }
    public static String getText(WebDriver driver, By locator) {
        waitForVisibility(driver, locator);
        return driver.findElement(locator).getText();
    }
    public static  boolean getWaitedCurrentUrl(WebDriver driver, String expectedCurrentUrl){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
        try {
            return wait.until(ExpectedConditions.urlToBe(expectedCurrentUrl));
        } catch (TimeoutException e) {
            // If it times out, return false
            // display Assert error message.
            return false;
        }
    }
    public static void clickDropdownOptionWithInputSearch(WebDriver driver, By dropdownLocator, By searchInputLocator, String optionText, String optionLocatorTemplate) {
        clickElement(driver, dropdownLocator);
        enterText(driver,searchInputLocator, optionText);
        By optionLocator = By.xpath(String.format(optionLocatorTemplate, optionText));
        clickElement(driver,optionLocator);
    }
    public static void clickDropdownOption(WebDriver driver, By dropdownLocator, String optionText, String optionLocatorTemplate) {
        clickElement(driver, dropdownLocator);
        By optionLocator = By.xpath(String.format(optionLocatorTemplate, optionText));
        clickElement(driver,optionLocator);
    }
}
