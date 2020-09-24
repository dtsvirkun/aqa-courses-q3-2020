package io.ctdev.tests.dataprovider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class DataProviderTest {
    private String login;
    private String password;

    @Factory(dataProvider = "dataMethod")
    public DataProviderTest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @DataProvider
    public static Object[][] dataMethod() {
        return new Object[][]{{"allan", "pass12345"}, {"bill", "clinton2345"}, {"andrey", "shevchecnko5"}};
    }

    @Test
    public void verifyUserCanLoginFromTheLoginPage() {
        System.out.println("Login to app from Login Page with username: " + login + " and password: " + password);
    }


    @Test()
    public void verifyUserCanLoginFromWelcomePage() {
        System.out.println("Login to app from Welcome page with username: " + login + " and password: " + password);
    }
}