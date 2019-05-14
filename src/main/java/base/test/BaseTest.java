package base.test;

import base.enums.Browsers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    protected WebDriver driver;
    private Browsers browser;

    public BaseTest() {
        browser = Browsers.CHROME;
    }

    public BaseTest(Browsers browser) {
        this.browser = browser;
    }

    @BeforeSuite
    public void setUpTest() {
        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                return;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                return;
            case OPERA:
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
                return;
            default:
                throw new IllegalArgumentException("Driver not implemented yet.");
        }

    }

    @AfterSuite
    public void teardown() {
        driver.quit();
    }
}
