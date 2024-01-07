package Srp.Tests;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;

    @BeforeTest
    public void setup() {
        this.driver = new ChromeDriver();
    }

    @AfterTest
    public void tearDown() {
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        this.driver.quit();
    }
}
