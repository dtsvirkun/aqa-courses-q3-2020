package io.ctdev.tests.login;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.driver.WebDriverSingleton;
import io.ctdev.framework.listeners.TestListener;
import io.ctdev.framework.model.Customer;
import io.ctdev.framework.pages.login.LoginFluentPage;
import io.ctdev.framework.pages.login.LoginPage;
import io.ctdev.framework.pages.login.LoginPageFactoryPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;


import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

@Epic("Signin/SignUp")
@Story("Login")
public class LoginToJuiceShopTest {

    private Customer customer;
    private WebDriver driver = getDriver();
    private LoginPage loginPage;
//    private LoginFluentPage fluentPage;
//    private LoginPageFactoryPage loginPageFactoryPage;

    @BeforeClass
    public void setUp() {
        customer = Customer.newBuilder().withName("dima@ukr.net").withPassword("12345678").build();
        loginPage = new LoginPage(driver);
//        fluentPage = new LoginFluentPage(driver);
//        loginPageFactoryPage = new LoginPageFactoryPage(driver);

    }

    @BeforeMethod
    public void beforeMethod() {
        loginPage.openPage();
        loginPage.closeDialog();
    }


    @Test
    @Description("verify user is able to login to juice shop")
    @Link("JSH-1234")
    public void userIsAbleToLoginToShop() {
        loginPage.clickOnAccountButton();

        loginPage.clickOnLoginButton();

        loginPage.enterUserEmail(customer.getEmail());

        loginPage.enterUserPassword(customer.getPassword());

        loginPage.submitLoginForUser();

        String actualUserName = loginPage.getCurrentLoggedUserName();

//        Assert.assertEquals(actualUserName, customer.getEmail(), "User name does not match ");
        Assert.assertEquals(actualUserName, "somename", "User name does not match ");


    }






//    @Test
//    public void userIsAbleToLoginToShopWithFluentInterface() {
//        String actualUserName = fluentPage.clickOnAccountButton()
//                .clickOnLoginButton().enterUserEmail(customer.getEmail())
//                .enterUserPassword(customer.getPassword()).submitLoginForUser().getCurrentLoggedUserName();
//
//        Assert.assertEquals(actualUserName, customer.getEmail(), "User name does not match ");
//
//    }
//
//    @Test
//    public void userIsAbleToLoginToShopWithPAgeFactory() {
//        String actualUserName = loginPageFactoryPage.clickOnAccountButton()
//                .clickOnLoginButton().enterUserEmail(customer.getEmail())
//                .enterUserPassword(customer.getPassword()).submitLoginForUser().getCurrentLoggedUserName();
//
////        File file = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
//        Assert.assertEquals(actualUserName, customer.getEmail(), "User name does not match ");
//
//    }
//
//    @Test
//    public void userIsAbleToLoginToApp() {
//        loginPageFactoryPage.loginAs(customer);
//        String actualUserName = loginPageFactoryPage.getCurrentLoggedUserName();
//
//        Assert.assertEquals(actualUserName, customer.getEmail(), "User name does not match ");
//
//    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }


}
