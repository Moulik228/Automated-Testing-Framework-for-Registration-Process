package pages;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class RegistrationPage {

    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/register");
    }

    public WebElement mainHeading() {
        return driver.findElement(By.tagName("h1"));
    }

    public WebElement errorSummary() {
        return driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"));
    }

    public WebElement loginPageLink() {
        return driver.findElement(By.xpath("//a[normalize-space()='login page']"));
    }

    public WebElement firstNameInput() {
        return driver.findElement(By.cssSelector("#input-firstname"));
    }

    public WebElement firstNameLabel() {
        return driver.findElement(By.cssSelector("label[for='input-firstname']"));
    }

    public String firstNameError() {
        return driver.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]")).getText();
    }

    public WebElement lastNameInput() {
        return driver.findElement(By.cssSelector("#input-lastname"));
    }

    public WebElement lastNameLabel() {
        return driver.findElement(By.cssSelector("label[for='input-lastname']"));
    }

    public String lastNameError() {
        return driver.findElement(By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")).getText();
    }

    public WebElement emailInput() {
        return driver.findElement(By.cssSelector("#input-email"));
    }

    public WebElement emailLabel() {
        return driver.findElement(By.cssSelector("label[for='input-email']"));
    }

    public String emailError() {
        return driver.findElement(By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid!')]")).getText();
    }

    public WebElement telephoneInput() {
        return driver.findElement(By.cssSelector("#input-telephone"));
    }

    public WebElement telephoneLabel() {
        return driver.findElement(By.cssSelector("label[for='input-telephone']"));
    }

    public String telephoneError() {
        return driver.findElement(By.xpath("//div[normalize-space()='Telephone must be between 3 and 32 characters!']")).getText();
    }

    public WebElement passwordInput() {
        return driver.findElement(By.cssSelector("#input-password"));
    }

    public WebElement passwordLabel() {
        return driver.findElement(By.cssSelector("label[for='input-password"));
    }

    public String passwordError() {
        return driver.findElement(By.xpath("//div[contains(text(),'Password must be between 4 and 20 characters!')]")).getText();
    }

    public WebElement passwordConfirmInput() {
        return driver.findElement(By.cssSelector("#input-confirm"));
    }

    public WebElement passwordConfirmLabel() {
        return driver.findElement(By.cssSelector("label[for='input-confirm"));
    }

    public String passwordConfirmError() {
        return driver.findElement(By.xpath("//div[@class='text-danger']")).getText();
    }

    public WebElement newsletterSubscribeYes() {
        return driver.findElement(By.cssSelector("label[for='input-newsletter-yes']"));
    }

    public WebElement newsletterSubscribeNo() {
        return driver.findElement(By.cssSelector("label[for='input-newsletter-no']"));
    }

    public String privacyPolicyError() {
        return driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
    }

    public WebElement subscribeLabel() {
        return driver.findElement(By.xpath("//label[normalize-space()='Subscribe']"));
    }

    public WebElement privacyPolicyBox() {
        return driver.findElement(By.cssSelector("label[for='input-agree']"));
    }

    public WebElement privacyPolicyLink() {
        return driver.findElement(By.tagName("b"));
    }

    public void openPrivacyPolicy() {
        privacyPolicyLink().click();
    }

    public WebElement continueButton() {
        return driver.findElement(By.cssSelector("input[value='Continue']"));
    }

    public String getErrorMessage(String inputLabel) {
        var errorMessageLocator = String.format("//label[text()='%s']//following-sibling::div/div", inputLabel);
        return driver.findElement(By.xpath(errorMessageLocator)).getText();
    }

    public void assertFirstNameValidation() {
        var actualError = getErrorMessage("First Name");
        Assert.assertEquals(actualError, "First Name must be between 1 and 32 characters!");
    }

    public void assertLastNameValidation() {
        var actualError = getErrorMessage("Last Name");
        Assert.assertEquals(actualError, "Last Name must be between 1 and 32 characters!");
    }

    public void assertEmailValidation() {
        var actualError = getErrorMessage("E-Mail");
        Assert.assertEquals(actualError, "E-Mail Address does not appear to be valid!");
    }

    public void assertTelephoneValidation() {
        var actualError = getErrorMessage("Telephone");
        Assert.assertEquals(actualError, "Telephone must be between 3 and 32 characters!");
    }

    public void assertPasswordValidation() {
        var actualError = getErrorMessage("Password");
        Assert.assertEquals(actualError, "Password must be between 4 and 20 characters!");
    }

    public void assertPasswordConfirmValidation() {
        var actualError = getErrorMessage("Password Confirm");
        Assert.assertEquals(actualError, "Password confirmation does not match password!");
    }

    public void assertPrivacyPolicyAgreementValidation() {
        Assert.assertEquals(errorSummary().getText(), "Warning: You must agree to the Privacy Policy!");
    }

    public String getPlaceholder(WebElement element) {
        return element.getAttribute("placeholder");
    }

    public void assertPlaceholder(String expectedText, WebElement element) {
        var actualPlaceholder = getPlaceholder(element);
        Assert.assertEquals(actualPlaceholder, expectedText);
    }

    // All The Registration Process

    public void register(User user, Boolean useEnter) {

        if(!user.getFirstName().isEmpty()) {
            firstNameInput().sendKeys(user.getFirstName());
        }

        if(!user.getLastName().isEmpty()) {
            lastNameInput().sendKeys(user.getLastName());
        }

        if(!user.getEmail().isEmpty()) {
            emailInput().sendKeys(user.getEmail());
        }

        if(!user.getTelephone().isEmpty()) {
            telephoneInput().sendKeys(user.getTelephone());
        }

        if (!user.getPassword().isEmpty()) {
            passwordInput().sendKeys(user.getPassword());
        }

        if(!user.getPasswordConfirm().isEmpty()) {
            passwordConfirmInput().sendKeys(user.getPasswordConfirm());
        }

        if(user.getSubscribe() && !newsletterSubscribeYes().isSelected()) {
            newsletterSubscribeYes().click();
        }
        else if(user.getSubscribe() && !newsletterSubscribeNo().isSelected()) {
            newsletterSubscribeNo().click();
        }

        if(user.getPrivacyPolicy()) {
            privacyPolicyBox().click();
        }

        if(useEnter) {
            continueButton().sendKeys(Keys.ENTER);
        }
        else {
            continueButton().click();
        }
    }
}

















