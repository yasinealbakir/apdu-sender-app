package Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleTurkish extends GoogleEnglish {

    public GoogleTurkish(WebDriver driver) {
        super(driver);
    }


    @Override
    public void launchSite() {
        this.driver.get("https://www.google.com.tr");
    }

}
