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
        //Enter fields, select dropdown options and save new customer
        newCustomerPage.createNewCustomer(
                "Riot Games ",
                "123456789",
                "0123456789",
                "dgrey.com",
                "VIP",
                "USD",
                "Vietnamese",
                "123 VN",
                "HCM",
                "HCM",
                "700000",
                "Vietnam"
        );

        newCustomerPage.getSidebarNavigation().clickCustomersMenu();
        // Get total customers after adding new customer
         int totalCustomersAfterAdd = customerPage.getCurrentTotalCustomers();
         boolean isTotalCustomersIncreased = newCustomerPage.compareTotalCustomersAfterAdd(totalCustomersBeforeAdd, totalCustomersAfterAdd);
        Assert.assertTrue(isTotalCustomersIncreased, "Total customers is not increased after add new customer");
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
        //Enter fields, select dropdown options and save new customer
        String uniqueCompanyName = newCustomerPage.createNewCustomer(
                "Riot Games ",
                "123456789",
                "0123456789",
                "dgrey.com",
                "VIP",
                "USD",
                "Vietnamese",
                "123 VN",
                "HCM",
                "HCM",
                "700000",
                "Vietnam"
        );
        newCustomerPage.getSidebarNavigation().clickCustomersMenu();
        customerPage.searchCustomerSummary(uniqueCompanyName);
        String actualCompanyName = customerPage.getCompanyCustomerSummary(uniqueCompanyName);
        Assert.assertEquals(actualCompanyName, uniqueCompanyName, "New customer is not displayed in customer summary");

    }
}

