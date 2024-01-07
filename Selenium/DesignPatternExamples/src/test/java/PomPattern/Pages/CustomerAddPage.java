package PomPattern.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomerAddPage {
    private WebDriver driver;
    WebElement firstNameInput = driver.findElement(By.xpath("//input[@placeholder='First Name']"));
    WebElement lastNameInput = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
    WebElement postCodeInput = driver.findElement(By.xpath("//input[@placeholder='Post Code']"));
    WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));

    public CustomerAddPage(WebDriver driver) {
        this.driver = driver;
    }

    public void customerAdd(String fname, String lname, String pcode) {
        firstNameInput.sendKeys(fname);
        lastNameInput.sendKeys(lname);
        postCodeInput.sendKeys(pcode);
        submitButton.click();

    }


}
