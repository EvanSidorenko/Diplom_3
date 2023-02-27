package owner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.function.Supplier;

public class WebDriverProvider implements Supplier<WebDriver> {
    private WebConfig config;

    public WebDriverProvider() {
        this.config = ConfigFactory.create(WebConfig.class, System.getProperties());
    }

    @Override
    public WebDriver get() {
        WebDriver driver = createWebDriver();
        driver.get(config.getBaseUrl());
        return driver;
    }

    public WebDriver createWebDriver() {
        if (config.getBrowser().equals(Browsers.CHROME.toString())) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
        if (config.getBrowser().equals(Browsers.FIREFOX.toString())) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
        else if (config.getBrowser().equals(Browsers.YANDEX.toString())) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            String YBpath = "C:\\Users\\sidor\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";
            options.setBinary(YBpath);
            return new ChromeDriver(options);
        }
        throw new RuntimeException("No such browser");
    }
}
