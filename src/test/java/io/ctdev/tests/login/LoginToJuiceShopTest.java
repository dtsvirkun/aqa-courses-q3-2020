package io.ctdev.tests.login;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.driver.WebDriverSingleton;
import io.ctdev.framework.model.Customer;
import io.ctdev.framework.pages.login.LoginFluentPage;
import io.ctdev.framework.pages.login.LoginPage;
import io.ctdev.framework.pages.login.LoginPageFactoryPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;

import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class LoginToJuiceShopTest {

    private Customer customer;
    private Customer customer2;
    private WebDriver driver = getDriver();
    private WebDriverWait wait;
    private LoginPage loginPage;
    private LoginFluentPage fluentPage;
    private LoginPageFactoryPage loginPageFactoryPage;

    @BeforeClass
    public void setUp() {
        getDriver().get(TestConfig.cfg.baseUrl());
        getDriver().findElement(By.cssSelector("[class*='close-dialog']")).click();
        wait = new WebDriverWait(driver, 5);
        customer = Customer.newBuilder().withName("dima@ukr.net").withPassword("12345678").build();
        customer2 = Customer.newBuilder().withName("asdasdasd").build();
        loginPage = new LoginPage(driver);
        fluentPage = new LoginFluentPage(driver);
        loginPageFactoryPage = new LoginPageFactoryPage(driver);

    }


    @Test
    public void userIsAbleToLoginToShop() {
        loginPage.clickOnAccountButton();

        loginPage.clickOnLoginButton();

        loginPage.enterUserEmail(customer.getEmail());

        loginPage.enterUserPassword(customer.getPassword());

        loginPage.submitLoginForUser();

        String actualUserName = loginPage.getCurrentLoggedUserName();

        Assert.assertEquals(actualUserName, customer.getEmail(), "User name does not match ");


    }


    @Test
    public void userIsAbleToLoginToShopWithFluentInterface() {
        String actualUserName = fluentPage.clickOnAccountButton()
                .clickOnLoginButton().enterUserEmail(customer.getEmail())
                .enterUserPassword(customer.getPassword()).submitLoginForUser().getCurrentLoggedUserName();

        Assert.assertEquals(actualUserName, customer.getEmail(), "User name does not match ");

    }

    @Test
    public void userIsAbleToLoginToShopWithPAgeFactory() {
        String actualUserName = loginPageFactoryPage.clickOnAccountButton()
                .clickOnLoginButton().enterUserEmail(customer.getEmail())
                .enterUserPassword(customer.getPassword()).submitLoginForUser().getCurrentLoggedUserName();

//        File file = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        Assert.assertEquals(actualUserName, customer.getEmail(), "User name does not match ");

    }

    @Test
    public void userIsAbleToLoginToApp() {
        loginPageFactoryPage.loginAs(customer);
        String actualUserName = loginPageFactoryPage.getCurrentLoggedUserName();

        Assert.assertEquals(actualUserName, customer.getEmail(), "User name does not match ");

    }

    @AfterClass
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }


}
