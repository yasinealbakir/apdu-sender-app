package Srp.Pages;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchSuggestion extends AbstractComponent {
    @FindBy(xpath = "//div[@class='OBMEnb']/ul/li")
    private List<WebElement> suggestionList;

    public SearchSuggestion(final WebDriver driver) {
        super(driver);
    }

    public void clickSuggestion(int index) {
        this.suggestionList.get(index - 1).click();
    }

    @Override
    public boolean isDisplayed() {
        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
        return this.wait.until((drv) -> this.suggestionList.size() > 5);
    }
}
