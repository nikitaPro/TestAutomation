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
    private int timeout;

    public NavigationMenu(WebDriver driver, int timeout) {
        this.driver = driver;
        this.timeout = timeout;
        this.wait = new WebDriverWait(driver, timeout);
    }
    
    public void chooseEnLanguage() {
        if (!driver.getCurrentUrl().contains("ru")) return;
        openNavigationMenu();
        if (LOG.isInfoEnabled()) LOG.info("Change language to EN.");
        By ruLink = By.xpath("//a[@param-lang='en']");
        clickByMenuItem(ruLink);
    }
    
    public void chooseRuLanguage() {
        if (driver.getCurrentUrl().contains("ru")) return;
        openNavigationMenu();
        if (LOG.isInfoEnabled()) LOG.info("Change language to RU.");
        By ruLink = By.xpath("//a[@param-lang='ru']");
        clickByMenuItem(ruLink);
    }
    
    public LoginPage openLoginPage() {
        openNavigationMenu();
        // click "Log in" button
        By loginLink = By.xpath("//li[contains(@class,'conditional-bg')]/a[contains(@href,'login')]");
        if (LOG.isInfoEnabled()) LOG.info("Click by login");
        clickByMenuItem(loginLink);
        
        // check the url
        if (LOG.isInfoEnabled()) LOG.info("Opening the login page.");
        wait.until(ExpectedConditions.urlContains("login"));
        
        return new LoginPage(driver, timeout);
    }
    
    private void clickByMenuItem(By link) {
        if (LOG.isInfoEnabled()) LOG.info("Waiting for the link buttton to be clickable.");
        wait.until(ExpectedConditions.elementToBeClickable(link));
        
        if (LOG.isInfoEnabled()) LOG.info("Click on the menu link.");
        driver.findElement(link).click();
    }
    
    private void openNavigationMenu() {
        if (LOG.isInfoEnabled()) LOG.info("Opening navigation menu.");
        // open navigation menu
        By menuButton = By.xpath("//nav[@id='header']//button");
        wait.until(ExpectedConditions.elementToBeClickable(menuButton));
        driver.findElement(menuButton).click();

        if (LOG.isInfoEnabled()) LOG.info("Waiting for navigation menu scroll down.");
        By navbar = By.xpath("//div[@id='navbar']/ul");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(navbar));
    }
}
