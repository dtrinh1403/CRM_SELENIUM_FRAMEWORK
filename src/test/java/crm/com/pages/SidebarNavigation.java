package crm.com.pages;

import crm.com.helpers.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SidebarNavigation extends BasePage {

private final By dashboardMenu    = By.xpath("//span[normalize-space()='Dashboard' and @class = 'menu-text']");
private final By customersMenu    = By.xpath("//span[normalize-space()='Customers' and @class = 'menu-text']");
private final By projectsMenu     = By.xpath("//span[normalize-space()='Projects' and @class = 'menu-text']");
private final By tasksMenu        = By.xpath("//span[normalize-space()='Tasks' and @class = 'menu-text']");


public SidebarNavigation(WebDriver driver) {
    super(driver);
}

// Navigation actions
public void clickCustomersMenu() {
    WebUI.clickElement(driver, customersMenu);
}

public void clickProjects() {
    WebUI.clickElement(driver, projectsMenu);
}

}
