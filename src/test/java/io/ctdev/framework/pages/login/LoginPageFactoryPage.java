package io.ctdev.framework.pages.login;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.model.Customer;
import io.ctdev.framework.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class LoginPageFactoryPage extends AbstractPage {

    private WebDriverWait wait;

    @FindBy(id = "navbarAccount")
    private WebElement navBarAccountElement;

    private By goToUserProfileElement = By.cssSelector("[aria-label='Go to user profile'] span");

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "email")
    private WebElement elemailInput;

    @FindBy(id ="navbarLoginButton" )
    private WebElement loginSubmitButton;

    public LoginPageFactoryPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, TIME_OUT);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void openPage() {
        driver.get(TestConfig.cfg.baseUrl() + "#/login");
    }

    public String getCurrentLoggedUserName() {
        navBarAccountElement.click();
        WebElement userNameElement = wait.until(ExpectedConditions.presenceOfElementLocated(goToUserProfileElement));
        return userNameElement.getAttribute("innerText").trim();


    }

    public LoginPageFactoryPage submitLoginForUser() {
        System.out.println("Clicking on Login button");
        loginButton.click();
        return this;
    }

    public LoginPageFactoryPage enterUserPassword(String password) {
        System.out.println("Typing user password " + password);
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPageFactoryPage enterUserEmail(String email) {
        System.out.println("Typing user email " + email);
        elemailInput.sendKeys(email);
        return this;
    }

    public LoginPageFactoryPage clickOnLoginButton() {
        System.out.println("Clicking on login button");
        loginSubmitButton.click();
        return this;
    }

    public LoginPageFactoryPage clickOnAccountButton() {
        System.out.println("Clicking on Account button");
        navBarAccountElement.click();
        return this;
    }


    public LoginPageFactoryPage loginAs(Customer customer) {
        openPage();
        clickOnAccountButton();
        clickOnLoginButton();
        enterUserEmail(customer.getEmail());
        enterUserPassword(customer.getPassword());
        submitLoginForUser();
        return this;
    }
}
