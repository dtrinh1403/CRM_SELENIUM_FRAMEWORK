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

    public boolean getWaitedCurrentUrl(String expectedCurrentUrl){
        try {
            return wait.until(ExpectedConditions.urlToBe(expectedCurrentUrl));
        } catch (TimeoutException e) {
            // If it times out, return false
            // display Assert error message.
            return false;
        }
    }

}
