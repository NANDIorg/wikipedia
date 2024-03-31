package by.NANDI.helpers;

import by.NANDI.constant.Common;
import by.NANDI.helpers.providers.LocalDriverProvider;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.apache.commons.compress.utils.IOUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Settings {
    protected static WebDriver driver;
    private static Boolean local;
    private static final String profilePropsPath = "target/test-classes/application.properties";

    private static String browserName;
    private static String browserVersion;
    public static String url;

    // Прикреплены ли к отчёту данные об окружении?
    private static boolean wroteAllureEnv = false;

    private static void prepare() throws MalformedURLException {
        setProfile();
        setEnv();

        Logger.info("Using local browser (" + browserName + ")");
        prepareLocalDriver();
        Configuration.timeout = Common.Timeouts.MAX;
        WebDriverRunner.setWebDriver(driver);
    }

    private static void setEnv() {
        Properties props = PropsUtils.getProps(".env");

        if (props == null) {
            Logger.error("The file '.env' is missing!");

            System.exit(1);
        } else {
            if (browserName.isEmpty()) {
                browserName = props.getProperty("BROWSER_NAME");
            }
            if (browserVersion.isEmpty()) {
                browserVersion = props.getProperty("BROWSER_VERSION");
            }

            url = props.getProperty("URL");
        }
    }

    private static void setProfile() {
        Properties props = PropsUtils.getProps(profilePropsPath);

        if (props == null) {
            System.exit(1);
        } else {
            local = props.getProperty("mode").equals("local");

            browserName    = props.getProperty("browserName");
            browserVersion = props.getProperty("browserVersion");
        }
    }

    @Step("Настройки драйвера локального браузера")
    private static void prepareLocalDriver() {
        driver = new LocalDriverProvider(browserName, browserVersion).getDriver();

        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().maximize();
    }

    @Step("Закрыть браузер")
    protected static void tearDown() {
        driver.quit();

        if (!wroteAllureEnv) {
            writeEnv();
        }
    }

    protected static void beforeTest() {
        try {
            prepare();
        } catch (MalformedURLException e) {
            Logger.error("Remote driver URL was malformed!");
        }
    }

    /**
     * Makes an environment properties file for Allure
     * Based on:
     *  https://automated-testing.info/t/allure-peredacha-environment-v-papku-allure-result/7031/15
     */
    private static void writeEnv() {
        FileOutputStream fos = null;

        try {
            Properties props = new Properties();
            fos = new FileOutputStream("target/allure-results/environment.properties");

            props.setProperty("URL", url);
            props.setProperty("Browser name", browserName);
            if (!local) {
                props.setProperty("Browser version", browserVersion);
            }

            props.store(fos, "Selenium environment");
            fos.close();
            wroteAllureEnv = true;
        } catch (IOException e) {
            Logger.warn("Couldn't write env properties file for Allure. Usually happens when allure-results folder doesn't exist yet.");
        } finally {
            IOUtils.closeQuietly(fos);
        }
    }

    public static byte[] getScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
