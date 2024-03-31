package by.NANDI.helpers.providers;

import by.NANDI.helpers.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LocalDriverProvider extends CommonDriverProvider {

    public LocalDriverProvider(String browserName, String browserVersion) {
        super(browserName, browserVersion);
    }

    @Override
    WebDriver getDriverInternal() {
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            default:
                Logger.error("Unsupported local driver '" + browserName.toLowerCase() + "'!");
                return null;
        }

    }
}
