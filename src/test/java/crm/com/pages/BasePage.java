package crm.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));

    }
    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void clickElement(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    public void enterText(By locator, String text){
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }
    public String getText(By locator) {
        return waitForVisibility(locator).getText();
    }

    public boolean getWaitedCurrentUrl(String expectedCurrentUrl){
        try {
            return wait.until(ExpectedConditions.urlToBe(expectedCurrentUrl));
        } catch (TimeoutException e) {
            // If it times out, return false
            // display Assert error message.
            return false;
        }
    }
    public void clickDropdownOptionWithInputSearch(By dropdownLocator, By searchInputLocator, String optionText, String optionLocatorTemplate) {
        clickElement(dropdownLocator);
        enterText(searchInputLocator, optionText);
        By optionLocator = By.xpath(String.format(optionLocatorTemplate, optionText));
        clickElement(optionLocator);
    }
    public void clickDropdownOption(By dropdownLocator, String optionText, String optionLocatorTemplate) {
        clickElement(dropdownLocator);
        By optionLocator = By.xpath(String.format(optionLocatorTemplate, optionText));
        clickElement(optionLocator);
    }

}
