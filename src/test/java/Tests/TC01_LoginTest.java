package Tests;

import Utilities.DataUtility;
import listeners.IInvokedListenerClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.IOException;

import static DriverFactory.DriverFactory.*;

@Listeners(IInvokedListenerClass.class)
public class TC01_LoginTest {

    @BeforeMethod
    public void setup() throws IOException {
        setupDriver(DataUtility.getPropertiesValue("environments","Browser"));
        getDriver().get(DataUtility.getPropertiesValue("environments","Base_URL"));

    }

    @Test
    public void validLoginTC() throws IOException {
        new LoginPage(getDriver()).
                enterUsername(DataUtility.getJsonData("ValidLogin","username")).
                enterPassword(DataUtility.getJsonData("ValidLogin","password")).
                clickOnLoginButton();
        Assert.assertEquals(getDriver().getCurrentUrl(),DataUtility.getPropertiesValue("environments","Home_URL"));
    }
    @AfterMethod
    public void quit(){
        closeBrowser();
    }

}
