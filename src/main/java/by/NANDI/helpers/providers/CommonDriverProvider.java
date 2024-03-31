package by.NANDI.helpers.providers;

import org.openqa.selenium.WebDriver;

public abstract class CommonDriverProvider {
    final String browserName;
    final String browserVersion;

    CommonDriverProvider(String browserName, String browserVersion) {
        this.browserName = browserName;
        this.browserVersion = browserVersion;
    }

    public WebDriver getDriver() {
        return getDriverInternal();
    }

    abstract WebDriver getDriverInternal();
}
