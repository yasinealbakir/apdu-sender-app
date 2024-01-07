package Srp.Pages;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class NavigationBar extends AbstractComponent {

    @FindBy(id = "hdtb")
    private WebElement navigateBar;
    @FindBy(linkText = "Görseller")
    private WebElement gorseller;

    @FindBy(linkText = "Haberler")
    private WebElement haberler;

    @FindBy(linkText = "Videolar")
    private WebElement videolar;

    public NavigationBar(WebDriver driver) {
        super(driver);
    }

    public void goToGorseller() {
        this.gorseller.click();
    }

    public void goToHaberler() {
        this.haberler.click();
    }

    public void goToVideolar(){
        this.videolar.click();
    }

    @Override
    public boolean isDisplayed() {
        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
        return this.wait.until((drv) -> this.navigateBar.isDisplayed());
    }
}
