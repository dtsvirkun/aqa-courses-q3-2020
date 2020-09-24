package io.ctdev.framework.pages.login;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class LoginFluentPage extends AbstractPage {

    private WebDriverWait wait;

    private By navBarAccountElement = By.id("navbarAccount");
    private By goToUserProfileElement = By.cssSelector("[aria-label='Go to user profile'] span");
    private By loginButton = By.id("loginButton");
    private By passwordInput = By.id("password");
    private By elemailInput = By.id("email");
    private By loginSubmitButton = By.id("navbarLoginButton");
    private String elementByText = "//*[@text=%s]";

    public LoginFluentPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, TIME_OUT);
    }

    @Override
    public void openPage() {
        driver.get(TestConfig.cfg.baseUrl() + "#/login");
    }

    public String getCurrentLoggedUserName() {
        getDriver().findElement(navBarAccountElement).click();
        WebElement userNameElement = wait.until(ExpectedConditions.presenceOfElementLocated(goToUserProfileElement));
        return userNameElement.getAttribute("innerText").trim();
    }

    public LoginFluentPage submitLoginForUser() {
        System.out.println("Clicking on Login button");
        getDriver().findElement(loginButton).click();
        return this;
    }

    public LoginFluentPage enterUserPassword(String password) {
        System.out.println("Typing user password " + password);
        getDriver().findElement(passwordInput).sendKeys(password);
        return this;
    }

    public LoginFluentPage enterUserEmail(String email) {
        System.out.println("Typing user email " + email);
        getDriver().findElement(elemailInput).sendKeys(email);
        return this;
    }

    public LoginFluentPage clickOnLoginButton() {
        System.out.println("Clicking on login button");
        getDriver().findElement(loginSubmitButton).click();
        return this;
    }

    public LoginFluentPage clickOnAccountButton() {
        System.out.println("Clicking on Account button");
        WebElement element = getDriver().findElement(navBarAccountElement);
        element.click();
        return this;
    }


}
