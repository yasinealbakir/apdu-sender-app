package Srp.Tests;

import Srp.Pages.GoogleMainPage;
import Srp.Pages.GoogleResultPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GoogleSearchTest extends BaseTest {
    private GoogleMainPage googleMainPage;
    private GoogleResultPage googleResultPage;

    @Test(dataProvider = "getAllSearchData")
    public void searchTest(String keyword, int index) {
        //Sayfa nesnelerinin kurulması lazım
        this.googleMainPage = new GoogleMainPage(driver);
        this.googleResultPage = new GoogleResultPage(driver);

        googleMainPage.goTo();

        // Sayfayı açınca arama widgetı kontrol et.
        Assert.assertTrue(googleMainPage.getSearchWidget().isDisplayed());

        googleMainPage.getSearchWidget().enter(keyword);

        // Anahtar kelime girildikten sonra öneri listesinin 5 itemdan fazla olup olmadığını kontrol et
        Assert.assertTrue(googleMainPage.getSearchSuggestion().isDisplayed());

        googleMainPage.getSearchSuggestion().clickSuggestion(index);

        // Navigation Bar görünüyor mu kontrol et
        Assert.assertTrue(googleResultPage.getNavigationBar().isDisplayed());

        googleResultPage.getNavigationBar().goToVideolar();

    }

    @DataProvider
    public Object[][] getAllSearchData() {
        return new Object[][]{
                {"selenium", 2},
                {"jmeter", 1},
                {"cucumber", 3}
        };
    }
}
