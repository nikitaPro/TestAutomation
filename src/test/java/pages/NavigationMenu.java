package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationMenu {
    private static final Logger LOG = Logger.getLogger(NavigationMenu.class);
    
    private WebDriver driver;
    private WebDriverWait wait;
    private final static int timeout = 5;

    public NavigationMenu(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }
    
    public LoginPage openLoginPage() {
        if (LOG.isInfoEnabled()) LOG.info("Opening navigation menu.");
        // open navigation menu
        driver.findElement(By.xpath("//nav[@id='header']//button")).click();
        
        // click "Log in" button
        By loginLink = By.xpath("//a[@href='/login']");
        if (LOG.isInfoEnabled()) LOG.info("Waiting for the 'Log in' buttton to be clickable.");
        wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        
        if (LOG.isInfoEnabled()) LOG.info("Opening the login page.");
        driver.findElement(loginLink).click();
        
        return new LoginPage(driver);
    }
}
