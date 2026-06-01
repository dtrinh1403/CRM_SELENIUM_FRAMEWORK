package crm.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CustomerPage  extends BasePage {
    private final SidebarNavigation sidebarNavigation;

    private By buttonAddNewCustomer = By.xpath("//a[normalize-space()='New Customer'and contains(@href, 'client')]");
    private By headerCustomerSummary = By.xpath("//span[normalize-space()='Customers Summary']");
    private By numberTotalCustomers = By.xpath("//span[normalize-space()='Total Customers']/preceding-sibling::span");
    private By numberActiveCustomers = By.xpath("//span[normalize-space()='Active Customers']/preceding-sibling::span");
    private By numberInactiveCustomers = By.xpath("//span[normalize-space()='Inactive Customers']/preceding-sibling::span");
    private By inputCustomerSearch= By.xpath("//div[@id='clients_filter']//input[@type = 'search']");
    private By companyCustomerSummary = By.xpath("//tbody/tr[1]/td[3]/a");
    private String deleteCustomerButton = "//a[normalize-space()='%s']/following-sibling::div/child::a[normalize-space() = 'Delete']";
    private By emptyStateMessageCustomerSummary = By.xpath("//td[@class='dataTables_empty' and normalize-space() = 'No matching records found']");
    private By tableBodyCustomerSummary = By.xpath("//tbody");

    public CustomerPage(WebDriver driver) {
        super(driver);
        this.sidebarNavigation = new SidebarNavigation(driver);
    }
    //getter sidebar
    public SidebarNavigation getSidebarNavigation() {
        return sidebarNavigation;
    }

    public void navigateToAddNewCustomerPage() {
        clickElement(buttonAddNewCustomer);
    }
    public int getCurrentTotalCustomers() {
        String totalCustomersText = getText(numberTotalCustomers);
        return Integer.parseInt(totalCustomersText);
    }
    public void searchCustomerSummary(String name){
        enterText(inputCustomerSearch, name);
    }

    public String getCompanyCustomerSummary(String expectedName) {
        try {

            wait.until(ExpectedConditions.textToBePresentInElementLocated(companyCustomerSummary, expectedName));
            return getText(companyCustomerSummary);
        } catch (Exception e) {
            //empty string is returned if no customer found, to avoid NoSuchElementException
            return "";
        }
    }
    public void deleteCustomerByCompanyName(String uniqueCompanyName){
        enterText(inputCustomerSearch, uniqueCompanyName);
        By deleteButtonLocator = By.xpath(String.format(deleteCustomerButton, uniqueCompanyName));

        // Hover over the company name to make the delete button visible
        Actions actions = new Actions(driver);
        actions.moveToElement(waitForVisibility(companyCustomerSummary )).perform();
        clickElement(deleteButtonLocator);
        //handle alert pop up
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
    private boolean isEmptyStateMessageDisplayed() {
        try {
            return waitForVisibility(emptyStateMessageCustomerSummary).isDisplayed();
        } catch (Exception e) {
            // If element not found, return false
            return false;
        }
    }
     public boolean isCustomerDeletedSuccessfully(String uniqueCompanyName) {
        searchCustomerSummary(uniqueCompanyName);
        return isEmptyStateMessageDisplayed();
    }
}
