package testScripts;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

import dataSets.LoginDataSet;
import drivers.Chrome;
import pages.LoginPage;
import pages.NavigationMenu;

@RunWith(Parameterized.class)
public class Login {
    private static WebDriver driver;
    private final static String MAIN_PAGE_ADDRESS = "https://netpeaksoftware.com/";
    private final static int TIMEOUT = 10;
    
    private static String email = "netpeaksoftwaretest@gmail.com";
    private static String pass = "123456";
    
    private static LoginDataSet validDataSet;
    
    private static LoginDataSet invalidDataSet_1;
    private static LoginDataSet invalidDataSet_2;
    private static LoginDataSet invalidDataSet_3;
    
    private static NavigationMenu menu;
    private static LoginPage loginPage;
    
    public Login(Lang lang) {
        switch (lang) {
        case RU: menu.chooseRuLanguage(); break;
        case EN: menu.chooseEnLanguage(); break;
        }
    }
    
    @Parameters
    public static Collection<Object[]> data() {
      Object[][] data = {{Lang.RU},{Lang.EN}};
      return Arrays.asList(data);
    }
    
    @BeforeClass
    public static void startLoginPage() {
        driver = new Chrome().getDriver(MAIN_PAGE_ADDRESS, TIMEOUT);
        menu = new NavigationMenu(driver, TIMEOUT);
        menu.chooseRuLanguage();
        
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
        try {
            loginPage.fillDownEmailAndPass(validDataSet)
                .clickLoginButton();
            
            assertTrue("User control panel page not opened!", 
                    driver.getCurrentUrl().contains("ucp"));
        } finally {
            driver.get(MAIN_PAGE_ADDRESS + "logout");
        }
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
