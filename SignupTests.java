package Registration;

import factories.UserFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.AccountSuccessPage;
import pages.RegistrationPage;

import static Registration.UtilityClass.*;

@Listeners(TestListener.class)
public class SignupTests {

    private WebDriver driver;
    private RegistrationPage registrationPage;
    private AccountSuccessPage accountSuccessPage;

    // Set Up The Firefox Driver (geckodriver)

    @BeforeClass
    public static void setUpClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    // Set Up The Registration And The Account Success Pages

    @BeforeMethod
    public void setUp() {

        driver = new FirefoxDriver();
        registrationPage = new RegistrationPage(driver);
        accountSuccessPage = new AccountSuccessPage(driver);
    }

    // Testing The Happy Path :

    // Registration With Click

    @Test
    public void userCreatedSuccessfully_allRequiredFields_clickContinue() {
        var user = UserFactory.createDefault();

        registrationPage.open();
        registrationPage.register(user, false);
        accountSuccessPage.assertAccountCreatedSuccessfully();
        System.out.println(accountSuccessPage.accountCreated() + "--> Registration With Click");
    }

    // Registration With Press (Enter)

    @Test
    public void userCreatedSuccessfully_allRequiredFields_pressContinue() {
        var user = UserFactory.createDefault();

        registrationPage.open();
        registrationPage.register(user, true);

        // Need To Wait To Get The Assertion Correct

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }

        accountSuccessPage.assertAccountCreatedSuccessfully();
        System.out.println(accountSuccessPage.accountCreated() + "--> Registration With Press (Enter)");
    }

    // Testing The Boundary Values :


    @DataProvider(name = "NameProvider")
    public static Object[][] NameProvider() {
        return new Object[][] {
                {generateRandomName(1)},
                {generateRandomName(32)}
        };
    }

    // First Name With 1 and 32 Random Characters

    @Test(dataProvider = "NameProvider")
    public void userCreatedSuccessfully_firstName_1_and_32_characters(String firstName) {
        var user = UserFactory.createDefault();
        user.setFirstName(firstName);

        registrationPage.open();
        registrationPage.register(user, false);
        accountSuccessPage.assertAccountCreatedSuccessfully();
        System.out.println(accountSuccessPage.accountCreated() + "--> First Name With 1 and 32 Random Characters");
    }

    // Last Name With 1 and 32 Random Characters

    @Test(dataProvider = "NameProvider")
    public void userCreatedSuccessfully_lastName_1_and_32_characters(String lastName) {
        var user = UserFactory.createDefault();
        user.setLastName(lastName);

        registrationPage.open();
        registrationPage.register(user, false);
        accountSuccessPage.assertAccountCreatedSuccessfully();
        System.out.println(accountSuccessPage.accountCreated() + "--> Last Name With 1 and 32 Random Characters");
    }

    @DataProvider(name = "EmailProvider")
    public static Object[][] EmailProvider() {
        return new Object[][] {
                {generateRandomEmail(4)},
                {generateRandomEmail(32)}
        };
    }

    // Email With Valid Form (x@x.x) With 4 and 32 Random Characters

    @Test(dataProvider = "EmailProvider")
    public void userCreatedSuccessfully_email_4_and_32_characters(String email) {
        var user = UserFactory.createDefault();
        user.setEmail(email);

        registrationPage.open();
        registrationPage.register(user, false);
        accountSuccessPage.assertAccountCreatedSuccessfully();
        System.out.println(accountSuccessPage.accountCreated() + "--> Email With Valid Form (x@x.x) With 4 and 32 Random Characters");
    }


    @DataProvider(name = "TelephoneProvider")
    public static Object[][] TelephoneProvider() {
        return new Object[][] {
                {generateRandomPhoneNumber(3)},
                {generateRandomPhoneNumber(32)}
        };
    }

    // Phone Number With 3 and 32 Random Numbers

    @Test(dataProvider = "TelephoneProvider")
    public void userCreatedSuccessfully_telephone_3_and_32_numbers(String telephone) {
        var user = UserFactory.createDefault();
        user.setTelephone(telephone);

        registrationPage.open();
        registrationPage.register(user, false);
        accountSuccessPage.assertAccountCreatedSuccessfully();
        System.out.println(accountSuccessPage.accountCreated() + "--> Phone Number With 3 and 32 Random Numbers");
    }

    @DataProvider(name = "PasswordProvider")
    public static Object[][] PasswordProvider() {
        return new Object[][] {
                {generateRandomPassword(4)},
                {generateRandomPassword(20)}
        };
    }

    // Password With 4 and 20 Random Characters

    @Test(dataProvider = "PasswordProvider")
    public void userCreatedSuccessfully_4_and_20_characters(String password) {
        var user = UserFactory.createDefault();
        user.setPassword(password);
        user.setPasswordConfirm(password);

        registrationPage.open();
        registrationPage.register(user, false);
        accountSuccessPage.assertAccountCreatedSuccessfully();
        System.out.println(accountSuccessPage.accountCreated() + "--> Password With 4 and 20 Random Characters");

    }

    // Subscribe Checked

    @Test
    public void userCreatedSuccessfully_newsletterSubscribeTrue() {
        var user = UserFactory.createDefault();
        user.setSubscribe(true);

        registrationPage.open();
        registrationPage.register(user, false);
        accountSuccessPage.assertAccountCreatedSuccessfully();
        System.out.println(accountSuccessPage.accountCreated() + "--> Subscribe Checked");
    }

    // Close The Driver After Every Test

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            System.out.println();
            driver.quit();
        }
    }
}


























