package by.NANDI.search;

import by.NANDI.BaseTest;
import by.NANDI.constant.Allure;
import by.NANDI.constant.Common;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag(Allure.Tag.SEARCH)
public class SearchPositiveTests extends BaseTest {

    @Test
    public void get() {
        mainPage.setSearchField(Common.Data.SEARCH_WORD_1);
        mainPage.waitForVisibleSuggestions();
        System.out.println(mainPage.getSuggestionsListCount());
    }
}
