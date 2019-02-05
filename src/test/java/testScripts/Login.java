package testScripts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import dataSets.LoginDataSet;
import drivers.Chrome;
import pages.LoginPage;
import pages.NavigationMenu;

public class Login {
    private static WebDriver driver;
    private final static String WEB_PAGE_ADDRESS = "https://netpeaksoftware.com/";
    private final static int TIMEOUT = 10;
    
    private static String email = "netpeaksoftwaretest@gmail.com";
    private static String pass = "123456";
    
    private static LoginDataSet validDataSet;
    
    private static LoginDataSet invalidDataSet_1;
    private static LoginDataSet invalidDataSet_2;
    private static LoginDataSet invalidDataSet_3;
    
    private static NavigationMenu menu;
    private static LoginPage loginPage;
    
    @BeforeClass
    public static void startLoginPage() {
        driver = new Chrome().getDriver(WEB_PAGE_ADDRESS, TIMEOUT);
        menu = new NavigationMenu(driver, TIMEOUT);
        
        validDataSet = new LoginDataSet(email, pass);
        
        invalidDataSet_1 = new LoginDataSet(email, "");
        invalidDataSet_2 = new LoginDataSet("123@123", pass);
        invalidDataSet_3 = new LoginDataSet(email, "654321");
    }
    
    @Before
    public void init() {
        loginPage = menu.openLoginPage();
    }
    
    @Test
    public void loginWithValidData() {
        loginPage.fillDownEmailAndPass(validDataSet)
            .clickLoginButton();
        
        assertEquals("User control panel page not opened!", 
                "https://netpeaksoftware.com/ru/ucp", 
                driver.getCurrentUrl());
    }
    
    @Test
    public void loginWithEmptyPass() {
        loginWithInvalidData(invalidDataSet_1);
    }
    
    @Test
    public void loginWithInvalidEmail() {
        loginWithInvalidData(invalidDataSet_2);
    }
    
    @Test
    public void loginWithInvalidPass() {
        loginWithInvalidData(invalidDataSet_3);
    }
    
    private void loginWithInvalidData(LoginDataSet invalidDataSet) {
        loginPage.fillDownEmailAndPass(invalidDataSet)
            .clickLoginButton();
        
        if (!loginPage.isErrorMessageAppear()) {
            fail("Expected error message 'Invalid credentials.'");
        }
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
