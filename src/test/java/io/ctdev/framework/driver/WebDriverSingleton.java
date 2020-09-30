package io.ctdev.framework.driver;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.listeners.EventListener;
import io.ctdev.framework.listeners.Highlighter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverSingleton {

//    private static ThreadLocal<EventFiringWebDriver>  driver = new ThreadLocal<>();
    private static ThreadLocal<WebDriver>  driver = new ThreadLocal<>();

    private WebDriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {

            switch (TestConfig.cfg.browser()) {
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new EventFiringWebDriver(new FirefoxDriver()));
                    break;
                }
                default: {
                    if (TestConfig.cfg.remote()) {
                        try {
                            driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome()));
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        WebDriverManager.chromedriver().setup();
                        driver.set(new EventFiringWebDriver(new ChromeDriver()));
                    }
                }
            }
//            driver.get().register(new EventListener());
//            driver.get().register(new Highlighter());
            driver.get().manage().window().maximize();
        }
        return driver.get();

    }

    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().close();
            driver.remove();
        }
    }
}
