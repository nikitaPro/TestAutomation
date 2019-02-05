package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataSets.LoginDataSet;

public class LoginPage {
    private static final Logger LOG = Logger.getLogger(LoginPage.class);
    
    private WebDriver driver;
    private WebDriverWait wait;
    private final static int timeout = 5;
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }
    
    public RegistrationPage openRegistrationPage() {
        if (LOG.isInfoEnabled()) {
            LOG.info("Opening registration page.");
        }
        driver.findElement(By.xpath("//a[@href='/signup']")).click();
        return new RegistrationPage(driver);
    }
    
    public LoginPage fillDownEmailAndPass(LoginDataSet dataSet) {
        return fillDownEmailAndPass(dataSet.getEmail(), dataSet.getPassword());
    }
    
    public LoginPage fillDownEmailAndPass(String email, String password) {
        By emailInput = By.xpath("//input[@id='inputUsername']");
        By passInput = By.xpath("//input[@id='inputPassword']");
        
        if (LOG.isInfoEnabled()) {
            LOG.info("Typing email and pass.");
        }
        typingIntoInputField(emailInput, email);
        typingIntoInputField(passInput, password);
        
        return this;
    }
    
    public LoginPage clickLoginButton() {
        if (LOG.isInfoEnabled()) {
            LOG.info("Clicking by Login button.");
        }
        driver.findElement(By.id("_submit")).submit();
        return this;
    }
    
    public boolean isErrorMessageAppear() {
        By alertDiv = By.xpath("//div[contains(@class, 'alert alert-danger')]");
        try {
            if (LOG.isInfoEnabled()) {
                LOG.info("Waiting for error message.");
            }
            
            wait.until(ExpectedConditions.presenceOfElementLocated(alertDiv));
            driver.findElement(alertDiv);
        
        } catch (NoSuchElementException e) {
            if (LOG.isInfoEnabled()) LOG.info("Shouldn't happen ", e);
            return false;
        } catch (TimeoutException e) {
            if (LOG.isInfoEnabled()) LOG.info("No error message appear ", e);
            return false;
        }
        return true;
    }
    
    private void typingIntoInputField(By field, String inputData) {
        WebElement elem = driver.findElement(field);
        elem.clear();
        elem.sendKeys(inputData);
    }
}
