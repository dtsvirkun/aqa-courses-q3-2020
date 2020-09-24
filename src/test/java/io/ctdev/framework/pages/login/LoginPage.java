package io.ctdev.framework.pages.login;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class LoginPage extends AbstractPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIME_OUT);
    }

    @Override
    public void openPage() {
        driver.get(TestConfig.cfg.baseUrl() + "#/login");
    }

    public String getCurrentLoggedUserName() {
        getDriver().findElement(By.id("navbarAccount")).click();
        WebElement userNameElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label='Go to user profile'] span")));
        return userNameElement.getAttribute("innerText").trim();
    }

    public void submitLoginForUser() {
        System.out.println("Clicking on Login button");
        getDriver().findElement(By.id("loginButton")).click();
    }

    public void enterUserPassword(String password) {
        System.out.println("Typing user password " + password);
        getDriver().findElement(By.id("password")).sendKeys(password);
    }

    public void enterUserEmail(String email) {
        System.out.println("Typing user email " + email);
        getDriver().findElement(By.id("email")).sendKeys(email);
    }

    public void clickOnLoginButton() {
        System.out.println("Clicking on login button");
        getDriver().findElement(By.id("navbarLoginButton")).click();
    }

    public void clickOnAccountButton() {
        System.out.println("Clicking on Account button");
        WebElement element = getDriver().findElement(By.id("navbarAccount"));
        element.click();
    }


}
