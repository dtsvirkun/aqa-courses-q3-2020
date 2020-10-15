package io.ctdev.framework.pages.login;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.pages.AbstractPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIME_OUT);
    }

    private By closeDialog = By.cssSelector("[class*='close-dialog']");

    @Override
    public void openPage() {
        driver.get(TestConfig.cfg.baseUrl() + "#/login");

    }

    @Step
    public String getCurrentLoggedUserName() {
        driver.findElement(By.id("navbarAccount")).click();
        WebElement userNameElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label='Go to user profile'] span")));
        return userNameElement.getAttribute("innerText").trim();
    }

    @Step
    public void submitLoginForUser() {
        System.out.println("Clicking on Login button");
        driver.findElement(By.id("loginButton")).click();
    }

    @Step("Enter user password")
    public void enterUserPassword(String password) {
        System.out.println("Typing user password " + password);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @Step("Enter user email")
    public void enterUserEmail(String email) {
        System.out.println("Typing user email " + email);
        driver.findElement(By.id("email")).sendKeys(email);
    }

    @Step("Click on login button")
    public void clickOnLoginButton() {
        System.out.println("Clicking on login button");
        driver.findElement(By.id("navbarLoginButton")).click();
    }

    @Step
    public void clickOnAccountButton() {
        System.out.println("Clicking on Account button");
        WebElement element = driver.findElement(By.id("navbarAccount"));
        element.click();
    }

    public boolean isElementVisible(By by) {
        try {
            driver.findElement(by);
        } catch (NoSuchElementException ex) {
            return false;
        }

        return true;
    }

    public void closeDialog() {
        wait.until(ExpectedConditions.elementToBeClickable(closeDialog)).click();
    }


}
