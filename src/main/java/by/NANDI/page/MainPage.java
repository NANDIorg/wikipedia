package by.NANDI.page;

import by.NANDI.constant.Common;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    By
        suggestionsXPath = By.xpath("//div[@class=\"suggestions\"]");

    SelenideElement
        searchField          = $(By.name("search")),
        suggestions          = $(suggestionsXPath),
        searchPageContainBtn = $(suggestionsXPath).$$(By.xpath("./a")).first();
    ;

    ElementsCollection
        suggestionsList = $(suggestionsXPath).$$(By.xpath("./div/a"));

    @Step("Вставка в поле поиска слово {0}")
    public void setSearchField(String searchWord) {
        searchField.shouldBe(Condition.visible, Common.DurationTimeout.MID).setValue(searchWord);
    }

    @Step("Ожидание появления подсказок")
    public void waitForVisibleSuggestions() {
        suggestions.shouldBe(Condition.visible, Common.DurationTimeout.MID);
    }

    @Step("Получения колличества подсказок")
    public Integer getSuggestionsListCount() {
        return suggestionsList.size();
    }

//    @Step("Переход на страницу поиска")
//    public SearchPage searchPageContainClick() {
//
//    }
}
