package crm.com.testcases;

import crm.com.common.BaseTest;
import crm.com.pages.CustomerPage;
import crm.com.pages.DashBoardPage;
import crm.com.pages.LoginPage;
import crm.com.pages.NewCustomerPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomerPageTest extends BaseTest {
    @Test
    public void verifyDeleteCustomerSuccess_ByEmptyStateMessage(){
        LoginPage loginPage = new LoginPage(driver);
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        CustomerPage customerPage = new CustomerPage(driver);
        NewCustomerPage newCustomerPage = new NewCustomerPage(driver);

        // Login to CRM
        loginPage.navigateToLoginPage();
        loginPage.loginCRM("admin@example.com", "123456");
        dashBoardPage.getSidebarNavigation().clickCustomersMenu();
        customerPage.navigateToAddNewCustomerPage();

        //Add new customer with unique company name
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
        //Delete customer just created
        customerPage.deleteCustomerByCompanyName(uniqueCompanyName);
        boolean isCustomerDeleted = customerPage.isCustomerDeletedSuccessfully(uniqueCompanyName);
        Assert.assertTrue(isCustomerDeleted, "Delete customer failed: Customer still exists in the summary after deletion");

    }
}
