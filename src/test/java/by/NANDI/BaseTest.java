package by.NANDI;

import by.NANDI.helpers.Settings;
import by.NANDI.page.ArticlePage;
import by.NANDI.page.MainPage;
import by.NANDI.page.SearchPage;
import by.NANDI.helpers.Logger;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest extends Settings {
    protected static MainPage mainPage = new MainPage();
    protected static SearchPage searchPage;
    protected static ArticlePage articlePage;

    static {
        Settings.beforeTest();
        Runtime.getRuntime().addShutdownHook(new Thread(Settings::tearDown));
    }

    @BeforeEach
    void before() {
        Selenide.clearBrowserCookies(); //Вдруг не почистились после прохождения предыдущего теста

        open(url);
        Logger.debug("Страница открыта - " + url);
    }

    @AfterEach
    void clearCookies() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }
}
