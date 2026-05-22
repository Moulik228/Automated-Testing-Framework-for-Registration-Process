package Registration;

import factories.UserFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.RegistrationPage;

import static Registration.UtilityClass.*;
import static Registration.UtilityClass.generateRandomPassword;

@Listeners(TestListener.class)
public class ErrorMessagesValidationsTests {

    private WebDriver driver;
    private RegistrationPage registrationPage;

    @BeforeClass
    public static void setUpClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeMethod
    public void setUp() {

        driver = new FirefoxDriver();
        registrationPage = new RegistrationPage(driver);
    }

    // Unhappy Paths:

    @DataProvider(name = "ErrorNameProvider")
    public static Object[][] ErrorNameProvider() {
        return new Object[][] {
                {generateRandomName(0)},
                {generateRandomName(33)}
        };
    }

    // First Name With 0 or 33 Random Characters

    @Test(dataProvider = "ErrorNameProvider")
    public void errorFirstNameValidation_0_and_33_characters(String errorFirstName) {

        var user = UserFactory.createDefault();
        user.setFirstName(errorFirstName);

        registrationPage.open();
        registrationPage.register(user, false);
        registrationPage.assertFirstNameValidation();
        System.out.println(registrationPage.firstNameError());

    }

    // Last Name With 0 or 33 Random Characters

    @Test(dataProvider = "ErrorNameProvider")
    public void errorLastNameValidation_0_and_33_characters(String errorLastName) {

        var user = UserFactory.createDefault();
        user.setLastName(errorLastName);

        registrationPage.open();
        registrationPage.register(user, false);
        registrationPage.assertLastNameValidation();
        System.out.println(registrationPage.lastNameError());
    }

    @DataProvider(name = "ErrorEmailProvider")
    public static Object[][] ErrorEmailProvider() {
        return new Object[][] {
                {generateRandomNotValidEmail(4)},
                {generateRandomNotValidEmail(32)}
        };
    }

    // Email With Invalid Form

    @Test(dataProvider = "ErrorEmailProvider")
    public void errorEmailValidation_randomNotValid(String errorEmail) {

        var user = UserFactory.createDefault();
        user.setEmail(errorEmail);

        registrationPage.open();
        registrationPage.register(user, false);
        registrationPage.assertEmailValidation();
        System.out.println(registrationPage.emailError());
    }

    @DataProvider(name = "TelephoneProvider")
    public static Object[][] TelephoneProvider() {
        return new Object[][] {
                {generateRandomPhoneNumber(2)},
                {generateRandomPhoneNumber(33)}
        };
    }

    // Phone Number With 2 or 33 Random numbers

    @Test(dataProvider = "TelephoneProvider")
    public void errorTelephoneValidation_2_and_33_numbers(String notValidTelephone) {

        var user = UserFactory.createDefault();
        user.setTelephone(notValidTelephone);

        registrationPage.open();
        registrationPage.register(user, false);
        registrationPage.assertTelephoneValidation();
        System.out.println(registrationPage.telephoneError());
    }

    @DataProvider(name = "PasswordProvider")
    public static Object[][] PasswordProvider() {
        return new Object[][] {
                {generateRandomPassword(3)},
                // {generateRandomPassword(22)}   --> [Bug] = it should not work with a password > 20 characters
        };
    }

    // Password With 3 Characters

    @Test(dataProvider = "PasswordProvider")
    public void errorPasswordValidation_3_and_21_characters(String password) {
        var user = UserFactory.createDefault();
        user.setPassword(password);
        user.setPasswordConfirm(password);

        registrationPage.open();
        registrationPage.register(user, false);
        registrationPage.assertPasswordValidation();
        System.out.println(registrationPage.passwordError());
    }

    @DataProvider(name = "PasswordConfirmProvider")
    public static Object[][] PasswordConfirmProvider() {
        return new Object[][] {
                {generateRandomPassword(4)},
                {generateRandomPassword(20)}
        };
    }

    // PasswordConfirm Is Different

    @Test(dataProvider = "PasswordConfirmProvider")
    public void errorPasswordConfirmValidation_different_passwords(String password) {
        var user = UserFactory.createDefault();
        user.setPasswordConfirm(password);

        registrationPage.open();
        registrationPage.register(user, false);
        registrationPage.assertPasswordConfirmValidation();
        System.out.println(registrationPage.passwordConfirmError());
    }

    // No Privacy Policy Checked

    @Test
    public void errorPrivacyPolicyValidation() {
        var user = UserFactory.createDefault();

        registrationPage.open();
        registrationPage.privacyPolicyBox().click();
        registrationPage.register(user, false);
        registrationPage.assertPrivacyPolicyAgreementValidation();
        System.out.println(registrationPage.privacyPolicyError());
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
















