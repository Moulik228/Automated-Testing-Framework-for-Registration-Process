package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AccountSuccessPage {

    private final WebDriver driver;

    public AccountSuccessPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement mainHeading() {
        return driver.findElement(By.tagName("h1"));
    }

    public String accountCreated() {
        return mainHeading().getText().trim();
    }

    public void assertAccountCreatedSuccessfully() {
        Assert.assertEquals(accountCreated(), "Your Account Has Been Created!");
    }
}
