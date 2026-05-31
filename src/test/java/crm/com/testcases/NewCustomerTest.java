package crm.com.testcases;

import crm.com.common.BaseTest;
import crm.com.pages.CustomerPage;
import crm.com.pages.DashBoardPage;
import crm.com.pages.LoginPage;
import crm.com.pages.NewCustomerPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewCustomerTest extends BaseTest {


    @Test(priority = 1)
    public void verifyAddNewCustomerSuccess_ByTotalCustomers(){
        LoginPage loginPage = new LoginPage(driver);
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        CustomerPage customerPage = new CustomerPage(driver);
        NewCustomerPage newCustomerPage = new NewCustomerPage(driver);

        // Login to CRM
        loginPage.navigateToLoginPage();
        loginPage.loginCRM("admin@example.com", "123456");
        dashBoardPage.getSidebarNavigation().clickCustomersMenu();
        // Get total customers before adding new customer
        int totalCustomersBeforeAdd = customerPage.getCurrentTotalCustomers();
        customerPage.navigateToAddNewCustomerPage();
        //Enter fields
        newCustomerPage.enterNewCustomerCompany("Riot Games " + System.currentTimeMillis());
        newCustomerPage.enterNewCustomerVATNumber("123456789");
        newCustomerPage.enterNewCustomerPhone("0123456789");
        newCustomerPage.enterNewCustomerWebsite("dgrey.com");
        //Select options
        newCustomerPage.selectNewCustomerGroups("VIP");
        newCustomerPage.selectNewCustomerCurrency("USD");
        newCustomerPage.selectNewCustomerLanguage("Vietnamese");

        newCustomerPage.enterNewCustomerAddress("123 VN");
        newCustomerPage.enterNewCustomerCity("HCM");
        newCustomerPage.enterNewCustomerState("HCM");
        newCustomerPage.enterNewCustomerZipCode("700000");
        newCustomerPage.selectNewCustomerCountry("Vietnam");
        newCustomerPage.clickButtonSaveOnly();

        newCustomerPage.getSidebarNavigation().clickCustomersMenu();
        // Get total customers after adding new customer
         int totalCustomersAfterAdd = customerPage.getCurrentTotalCustomers();
         boolean isTotalCustomersIncreased = newCustomerPage.compareTotalCustomersAfterAdd(totalCustomersBeforeAdd, totalCustomersAfterAdd);
        Assert.assertTrue(isTotalCustomersIncreased, "Total customers is not increased after add new customer");// Verify login successfully by checking dashboard page header
    }
    @Test(priority = 2)
    public void verifyAddNewCustomerSuccess_BySearchingCustomerName(){
        LoginPage loginPage = new LoginPage(driver);
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        CustomerPage customerPage = new CustomerPage(driver);
        NewCustomerPage newCustomerPage = new NewCustomerPage(driver);

        // Login to CRM
        loginPage.navigateToLoginPage();
        loginPage.loginCRM("admin@example.com", "123456");
        dashBoardPage.getSidebarNavigation().clickCustomersMenu();
        customerPage.navigateToAddNewCustomerPage();
        //Enter fields
        String uniqueCompanyName = "Riot Games " + System.currentTimeMillis();
        newCustomerPage.enterNewCustomerCompany(uniqueCompanyName);
        newCustomerPage.enterNewCustomerVATNumber("123456789");
        newCustomerPage.enterNewCustomerPhone("0123456789");
        newCustomerPage.enterNewCustomerWebsite("dgrey.com");
        //Select options
        newCustomerPage.selectNewCustomerGroups("VIP");
        newCustomerPage.selectNewCustomerCurrency("USD");
        newCustomerPage.selectNewCustomerLanguage("Vietnamese");

        newCustomerPage.enterNewCustomerAddress("123 VN");
        newCustomerPage.enterNewCustomerCity("HCM");
        newCustomerPage.enterNewCustomerState("HCM");
        newCustomerPage.enterNewCustomerZipCode("700000");
        newCustomerPage.selectNewCustomerCountry("Vietnam");
        newCustomerPage.clickButtonSaveOnly();

        newCustomerPage.getSidebarNavigation().clickCustomersMenu();
        customerPage.searchCustomerSummary(uniqueCompanyName);
        String actualCompanyName = customerPage.getCompanyCustomerSummary(uniqueCompanyName);
        Assert.assertEquals(actualCompanyName, uniqueCompanyName, "New customer is not displayed in customer summary");
    }
}

