package Registration;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegistrationPage;

import java.time.Duration;

@Listeners(TestListener.class)
public class PresenceOfElementsTests {

    private WebDriver driver;
    private WebDriverWait wait;
    private RegistrationPage registrationPage;

    @BeforeClass
    public static void setUpClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeMethod
    public void setUp() {

        driver = new FirefoxDriver();
        registrationPage = new RegistrationPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Testing The Presence Of Elements :

    @Test
    public void presenceOf_mainHeading() {

        registrationPage.open();
        String actual_mainHeading = registrationPage.mainHeading().getText();
        Assert.assertTrue(actual_mainHeading.contains("Register"));
        System.out.println("The Main Heading " + actual_mainHeading + " is present");
    }

    @Test
    public void presenceOf_loginPageLink() {

        registrationPage.open();
        String actual_loginPageLink = registrationPage.loginPageLink().getText();
        Assert.assertEquals(actual_loginPageLink, "login page");
        System.out.println("The Login Page Link " + actual_loginPageLink + " is present");
    }

    @Test
    public void presenceOf_firstNameLabel() {

        registrationPage.open();
        String actual_firstNameLabel = registrationPage.firstNameLabel().getText();
        Assert.assertEquals(actual_firstNameLabel, "First Name");
        System.out.println("The First Name Label " + actual_firstNameLabel + " is present");
    }

    @Test
    public void presenceOf_lastNameLabel() {

        registrationPage.open();
        String actual_lastNameLabel = registrationPage.lastNameLabel().getText();
        Assert.assertEquals(actual_lastNameLabel, "Last Name");
        System.out.println("The Last Name Label " + actual_lastNameLabel + " is present");
    }

    @Test
    public void presenceOf_emailLabel() {

        registrationPage.open();
        String actual_emailLabel = registrationPage.emailLabel().getText();
        Assert.assertEquals(actual_emailLabel, "E-Mail");
        System.out.println("The Email Label " + actual_emailLabel + " is present");
    }

    @Test
    public void presenceOf_telephoneLabel() {

        registrationPage.open();
        String actual_telephoneLabel = registrationPage.telephoneLabel().getText();
        Assert.assertEquals(actual_telephoneLabel, "Telephone");
        System.out.println("The Telephone Label" + actual_telephoneLabel + " is present");
    }

    @Test
    public void presenceOf_passwordLabel() {

        registrationPage.open();
        String actual_passwordLabel = registrationPage.passwordLabel().getText();
        Assert.assertEquals(actual_passwordLabel, "Password");
        System.out.println("The Password Label " + actual_passwordLabel + " is present");
    }

    @Test
    public void presenceOf_passwordConfirmLabel() {

        registrationPage.open();
        String actual_passwordConfirmLabel = registrationPage.passwordConfirmLabel().getText();
        Assert.assertEquals(actual_passwordConfirmLabel, "Password Confirm");
        System.out.println("The Password Confirm Label " + actual_passwordConfirmLabel + " is present");
    }

    @Test
    public void presenceOf_subscribeLabel() {

        registrationPage.open();
        String actual_subscribeLabel = registrationPage.subscribeLabel().getText();
        Assert.assertEquals(actual_subscribeLabel, "Subscribe");
        System.out.println("The Subscribe Label " + actual_subscribeLabel + " is present");
    }

    // Test The Privacy Policy PopUp Button:

    @Test
    public void presenceOf_privacyPolicy() {

        registrationPage.open();
        registrationPage.openPrivacyPolicy();
        String actual_headerPrivacyPolicyPopUp = wait.until
                (ExpectedConditions.presenceOfElementLocated(By.tagName("h4"))).getText();
        Assert.assertEquals(actual_headerPrivacyPolicyPopUp, "Privacy Policy");
        System.out.println("The Privacy Policy PopUp Window" + actual_headerPrivacyPolicyPopUp + " is present");
    }

    // Test Only The First Name Placeholder

    @Test
    public void presenceOf_placeholder() {

        registrationPage.open();
        WebElement element = driver.findElement(By.id("input-firstname"));
        registrationPage.assertPlaceholder("First Name", element);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            System.out.println();
            driver.quit();
        }
    }

}















