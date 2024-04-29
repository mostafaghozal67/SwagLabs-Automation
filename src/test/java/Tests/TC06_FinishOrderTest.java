package Tests;

import Utilities.DataUtility;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.OverViewPage;

import java.io.FileNotFoundException;
import java.io.IOException;

import static DriverFactory.DriverFactory.*;

public class TC06_FinishOrderTest {
    @BeforeMethod
    public void setup() throws IOException {
        setupDriver(DataUtility.getPropertiesValue("environments","Browser"));
        getDriver().get(DataUtility.getPropertiesValue("environments","Base_URL"));
    }


    @Test
    public void FinishOrderTC() throws IOException {
        new LoginPage(getDriver()).
                enterUsername(DataUtility.getJsonData("ValidLogin","username")).
                enterPassword(DataUtility.getJsonData("ValidLogin","password")).
                clickOnLoginButton().
                addRandomProductsToCart(5).
                clickOnCartIcon().
                clickOnCheckoutBtn().
                enterFirstname(DataUtility.getJsonData("CheckoutInformation","firstname")).
                enterLastname(DataUtility.getJsonData("CheckoutInformation","lastname")).
                enterZipCode(DataUtility.getJsonData("CheckoutInformation","zipCode")).
                clickOnContinueButton().
                clickOnFinishButton().
                checkVisibilityOfThanksMessageElement();

    }

    @AfterMethod
    public void quit(){
        closeBrowser();
    }
}
