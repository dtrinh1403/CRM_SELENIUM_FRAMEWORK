package crm.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashBoardPage extends BasePage {
    private By dashboardOptionsHeader = By.xpath("//div[normalize-space()='Dashboard Options']");
    private final SidebarNavigation sidebarNavigation;

    public DashBoardPage(WebDriver driver) {
        super(driver);
        this.sidebarNavigation = new SidebarNavigation(driver);
    }
    //getter sidebar
    public SidebarNavigation getSidebarNavigation() {
        return sidebarNavigation;
    }
    public boolean isDashboardOptionsDisplayed() {
        try {
            // wait for max 10s for the element to pop up
            return wait.until(driver -> driver.findElements(dashboardOptionsHeader).size() > 0);
        } catch (Exception e) {
            // If 10s runs out and no element found return false
            return false;
        }
    }
}
