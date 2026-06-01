package crm.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.security.PrivateKey;

public class NewCustomerPage extends BasePage {
    private final SidebarNavigation sidebarNavigation;
    private By headerCustomerDetails= By.xpath("//a[normalize-space()='Customer Details']");
    private By inputNewCustomerCompany = By.xpath("//input[@id='company']");
    private By inputNewCustomerVATNumber = By.xpath("//input[@id='vat']");
    private By inputNewCustomerPhone = By.xpath("//input[@id='phonenumber']");
    private By inoutNewCustomerWebsite = By.xpath("//input[@id='website']");
    private By inputNewCustomerAddress = By.xpath("//textarea[@id='address']");
    private By inputNewCustomerCity = By.xpath("//input[@id='city']");
    private By inputNewCustomerState = By.xpath("//input[@id='state']");
    private By inputNewCustomerZipCode = By.xpath("//input[@id='zip']");

    private By dropdownNewCustomerGroups = By.xpath("//button[@data-id = 'groups_in[]']");
    private By inputSearchNewCustomerGroups = By.xpath("//button[@data-id = 'groups_in[]']/following-sibling::div//input");
    private String optionSearchNewCustomerGroups = "//span[normalize-space()= '%s']";
    private By dropdownNewCustomerCurrency = By.xpath("//button[@data-id = 'default_currency']");
    private By inputSearchNewCustomerCurrency = By.xpath("//button[@data-id = 'default_currency']/following-sibling::div//input");
    private String optionSearchNewCustomerCurrency = "//span[contains(normalize-space(),'%s')]";
    private By dropdownNewCustomerLanguage = By.xpath("//button[@data-id='default_language']");
    private String optionsNewCustomerLanguage = "//span[normalize-space() = '%s']";

    private By dropdownNewCustomerCountry = By.xpath("//button[@data-id='country']");
    private By inputSearchNewCustomerCountry = By.xpath("//button[@data-id = 'country']/following-sibling::div//input");
    private String optionSearchNewCustomerCountry = "//span[normalize-space()='%s']";
    private By buttonSaveOnly = By.xpath("//button[contains(@class , 'only-save')]");



    public NewCustomerPage(WebDriver driver) {
        super(driver);
        this.sidebarNavigation = new SidebarNavigation(driver);
    }
    //getter sidebar
    public SidebarNavigation getSidebarNavigation() {
        return sidebarNavigation;
    }
    public boolean isHeaderNewCustomerDisplayed() {
        try {
            // wait for max 10s for the element to pop up
            return wait.until(driver -> driver.findElements(headerCustomerDetails).size() > 0);
        } catch (Exception e) {
            // If 10s runs out and no element found return false
            return false;
        }
    }
    public void enterNewCustomerCompany(String name) {
        enterText(inputNewCustomerCompany, name);
    }
    public void enterNewCustomerVATNumber(String vatNumber) {
        enterText(inputNewCustomerVATNumber, vatNumber);
    }
    public void enterNewCustomerPhone(String phone) {
        enterText(inputNewCustomerPhone, phone);
    }
    public void enterNewCustomerWebsite(String website) {
        enterText(inoutNewCustomerWebsite, website);
    }
    public void enterNewCustomerAddress(String address) {
        enterText(inputNewCustomerAddress, address);
    }
    public void enterNewCustomerCity(String city) {
        enterText(inputNewCustomerCity, city);
    }
    public void enterNewCustomerState(String state) {
        enterText(inputNewCustomerState, state);
    }
    public void enterNewCustomerZipCode(String zipCode) {
        enterText(inputNewCustomerZipCode, zipCode);
    }
    public void selectNewCustomerGroups(String groupName) {
        clickDropdownOptionWithInputSearch(dropdownNewCustomerGroups, inputSearchNewCustomerGroups, groupName, optionSearchNewCustomerGroups);
        clickElement(dropdownNewCustomerGroups);
    }
    public void selectNewCustomerCurrency(String currency) {
        clickDropdownOptionWithInputSearch(dropdownNewCustomerCurrency, inputSearchNewCustomerCurrency, currency, optionSearchNewCustomerCurrency);
    }
    public void selectNewCustomerLanguage(String language) {
       clickDropdownOption(dropdownNewCustomerLanguage, language, optionsNewCustomerLanguage);
    }
    public void selectNewCustomerCountry(String country) {
       clickDropdownOptionWithInputSearch(dropdownNewCustomerCountry, inputSearchNewCustomerCountry, country, optionSearchNewCustomerCountry);
    }
    public void clickButtonSaveOnly() {
        clickElement(buttonSaveOnly);
    }
    public boolean compareTotalCustomersAfterAdd(int totalBeforeAdd, int totalAfterAdd) {
        return totalAfterAdd == totalBeforeAdd + 1;
    }
    public String createNewCustomer(String companyName, String vatNumber, String phone, String website, String groupName, String currency, String language, String address, String city, String state, String zipCode, String country) {
        String uniqueCompanyName = companyName + System.currentTimeMillis();
        enterNewCustomerCompany(uniqueCompanyName);
        enterNewCustomerVATNumber(vatNumber);
        enterNewCustomerPhone(phone);
        enterNewCustomerWebsite(website);
        selectNewCustomerGroups(groupName);
        selectNewCustomerCurrency(currency);
        selectNewCustomerLanguage(language);
        enterNewCustomerAddress(address);
        enterNewCustomerCity(city);
        enterNewCustomerState(state);
        enterNewCustomerZipCode(zipCode);
        selectNewCustomerCountry(country);
        clickButtonSaveOnly();
        //return unique name to search,edit or delete later
        return uniqueCompanyName;
    }


}
