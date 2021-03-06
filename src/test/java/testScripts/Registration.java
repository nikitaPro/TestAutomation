package testScripts;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

import dataSets.SignupDataSet;
import drivers.Chrome;
import pages.LoginPage;
import pages.NavigationMenu;
import pages.RegistrationPage;

@RunWith(Parameterized.class)
public class Registration {
    private static WebDriver driver;
    private final static String MAIN_PAGE_ADDRESS = "https://netpeaksoftware.com/";
    private final static int TIMEOUT = 30;
    
    private static NavigationMenu menu;
    private static LoginPage loginPage;
    private static RegistrationPage regPage;
    
    private static SignupDataSet firstSet;
    private static SignupDataSet secondSet;
    private static SignupDataSet thirdSet;
    private static SignupDataSet fourthSet;
    
    public Registration(Lang lang) {
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
        driver.manage().window().fullscreen();
        
        firstSet = new SignupDataSet("", "", "valid@gmail.com", "123456", true);
        secondSet = new SignupDataSet("SomeName", "SomeLastName", "", "123456", true);
        thirdSet = new SignupDataSet("", "", "valid@gmail.com", "123456", false);
        fourthSet = new SignupDataSet("SomeName", "SomeLastName", "valid@gmail.com", "123456", false);
        
        menu = new NavigationMenu(driver, TIMEOUT);
    }
    
    @Before
    public void init() {
        loginPage = menu.openLoginPage();
        regPage = loginPage.openRegistrationPage();
    }
    
    @Test
    public void checkRegistrationWithoutFirstAndLastNames() {
        RegistrationPage regPage = registration(firstSet);
        
        // check fields with error
        if (!regPage.isFirstNameHasError()) {
            fail("First name must have an error.");
        }
        if (!regPage.isLastNameHasError()) {
            fail("Last name must have an error.");
        }
    }    
    
    @Test
    public void checkRegistrationWithouEmail() {
        RegistrationPage regPage = registration(secondSet);
        
        // check fields with error
        if (!(regPage.isEmailHasError())) {
            fail("Email must have an error.");
        }
    } 
    
    @Test
    public void checkRegistrationWithouNamesAndCheckBoxes() {
        RegistrationPage regPage = registration(thirdSet);
        
        // check fields with error
        if (!regPage.isFirstNameHasError()) {
            fail("First name must have an error.");
        }
        if (!regPage.isLastNameHasError()) {
            fail("Last name must have an error.");
        }
        if (!regPage.isTermsOfUseHasError()) {
            fail("Term of use must have an error.");
        }
        if (!regPage.isPrivacyPolicyHasError()) {
            fail("Privacy policy must have an error.");
        }
    } 
    
    @Test
    public void checkRegistrationWithouCheckBoxes() {
        RegistrationPage regPage = registration(fourthSet);
        
        // check fields with error
        if (!regPage.isTermsOfUseHasError()) {
            fail("Term of use must have an error.");
        }
        if (!regPage.isPrivacyPolicyHasError()) {
            fail("privacy policy must have an error.");
        }
    } 
    
    private RegistrationPage registration(SignupDataSet dataSet) {
        return regPage.fillInSignupForm(dataSet)
                .clickSignUpButton();
    }
    
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
