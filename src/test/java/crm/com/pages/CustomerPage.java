package crm.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
    private String uniqueCustomerCompanyName;

    public CustomerPage(WebDriver driver) {
        super(driver);
        this.sidebarNavigation = new SidebarNavigation(driver);
    }
    //getter sidebar
    public SidebarNavigation getSidebarNavigation() {
        return sidebarNavigation;
    }
    public void setUniqueCustomerCompanyName(String companyName) {
        this.uniqueCustomerCompanyName = companyName;
    }
    public String getUniqueCustomerCompanyName() {
       return this.uniqueCustomerCompanyName;
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
}
