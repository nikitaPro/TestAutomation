package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataSets.SignupDataSet;

public class RegistrationPage {
    private static final Logger LOG = Logger.getLogger(RegistrationPage.class);
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    public RegistrationPage(WebDriver driver, int timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }
    
    public RegistrationPage fillInSignupForm(SignupDataSet dataSet) {
        if (LOG.isInfoEnabled()) {
            LOG.info("Filling in the registration form.");
        }
        typeFirstName(dataSet.getFirstName());
        typeLastName(dataSet.getLastName());
        typeEmail(dataSet.getEmail());
        typePassword(dataSet.getPassword());
        
        if (dataSet.isCheckBoxesChecked()) {
            if (LOG.isInfoEnabled()) LOG.info("Clicking by checkboxes");
            clickTermsOfUse();
            clickPrivacyPolicy();
        }
        return this;
    }
    
    public RegistrationPage typeFirstName(String name) {
        By firstNameField = By.id("fos_user_registration_form_firstName");
        typingIntoInputField(firstNameField, name);
        return this;
    }
    
    public RegistrationPage typeLastName(String sName) {
        By lastNameField = By.id("fos_user_registration_form_lastName");
        typingIntoInputField(lastNameField, sName);
        return this;
    }
    
    public RegistrationPage typeEmail(String email) {
        By emailField = By.id("fos_user_registration_form_email");
        typingIntoInputField(emailField, email);
        return this;
    }
    
    public RegistrationPage typePassword(String pass) {
        By passField = By.id("fos_user_registration_form_plainPassword");
        typingIntoInputField(passField, pass);
        return this;
    }
    
    public RegistrationPage clickTermsOfUse() {
        By checkBox = By.id("fos_user_registration_form_termsOfUse");
        clickCheckBox(checkBox);
        return this;
    }
    
    public RegistrationPage clickPrivacyPolicy() {
        By checkBox = By.id("fos_user_registration_form_privacyPolicy");
        clickCheckBox(checkBox);
        return this;
    }
    
    public RegistrationPage clickSignUpButton() {
        if (LOG.isInfoEnabled()) LOG.info("Clicking by sign up button");
        driver.findElement(By.id("registerButton")).submit();
        return this;
    }
    
    public boolean isFirstNameHasError() {
        return isInputFieldHasError("fos_user_registration_form_firstName");
    }
    
    public boolean isLastNameHasError() {
        return isInputFieldHasError("fos_user_registration_form_lastName");
    }
    
    public boolean isEmailHasError() {
        return isInputFieldHasError("fos_user_registration_form_email");
    }
    
    public boolean isPasswordHasError() {
        return isInputFieldHasError("fos_user_registration_form_plainPassword");
    }
    
    public boolean isTermsOfUseHasError() {
        return isInputFieldHasError("fos_user_registration_form_termsOfUse");
    }
    
    public boolean isPrivacyPolicyHasError() {
        return isInputFieldHasError("fos_user_registration_form_privacyPolicy");
    }
    
    private boolean isInputFieldHasError(String id) {
        By field = By.cssSelector("div.has-error input#" + id);
        try {
            if (LOG.isInfoEnabled()) {
                LOG.info("Waiting for error for input with id='" + id + "'.");
            }
            wait.until(ExpectedConditions.presenceOfElementLocated(field));
            driver.findElement(field);
        } catch (NoSuchElementException e) {
            if (LOG.isInfoEnabled()) LOG.info("Shouldn't happen ", e);
            return false;
        } catch (TimeoutException e) {
            if (LOG.isInfoEnabled()) LOG.info("No error message appear ", e);
            return false;
        }
        return true;
    }

    private void clickCheckBox(By checkBox) {
        driver.findElement(checkBox).click();
    }
    
    private void typingIntoInputField(By field, String inputData) {
        WebElement elem = driver.findElement(field);
        elem.clear();
        elem.sendKeys(inputData);
    }

}
