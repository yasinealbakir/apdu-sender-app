package PomPattern.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;
    WebElement btnAddCustomer = driver.findElement(By.xpath("//button[normalize-space()='Add Customer']"));
    WebElement btnOpenAccount = driver.findElement(By.xpath("//button[normalize-space()='Open Account']"));

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAddCustomerBtn() {
        btnAddCustomer.click();
    }

    public void clickOpenAccountBtn() {
        btnOpenAccount.click();
    }

}
