package Tests;

import Utilities.DataUtility;
import io.qameta.allure.Description;
import listeners.IInvokedListenerClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.OverViewPage;
import java.io.FileNotFoundException;
import java.io.IOException;
import static DriverFactory.DriverFactory.*;

@Listeners(IInvokedListenerClass.class)

public class TC05_OverviewTest {

    @BeforeMethod
    public void setup() throws IOException {
        setupDriver(DataUtility.getPropertiesValue("environments","Browser"));
        getDriver().get(DataUtility.getPropertiesValue("environments","Base_URL"));
    }

    @Description("This test case checks if the sum of the item price and tax is equal to the total price")
    @Test
    public void checkoutTotalPrice() throws FileNotFoundException {
        new LoginPage(getDriver()).
                enterUsername(DataUtility.getJsonData("ValidLogin","username")).
                enterPassword(DataUtility.getJsonData("ValidLogin","password")).
                clickOnLoginButton().
                addRandomProductsToCart(3).
                clickOnCartIcon().
                clickOnCheckoutBtn().
                enterFirstname(DataUtility.getJsonData("CheckoutInformation","firstname")).
                enterLastname(DataUtility.getJsonData("CheckoutInformation","lastname")).
                enterZipCode(DataUtility.getJsonData("CheckoutInformation","zipCode")).
                clickOnContinueButton();
                //getTotalPrice();
        Assert.assertTrue(new OverViewPage(getDriver()).comparePrices());
    }

    @Test
    public void clickOnFinishBtn() throws IOException {
        new LoginPage(getDriver()).
                enterUsername(DataUtility.getJsonData("ValidLogin","username")).
                enterPassword(DataUtility.getJsonData("ValidLogin","password")).
                clickOnLoginButton().
                addRandomProductsToCart(3).
                clickOnCartIcon().
                clickOnCheckoutBtn().
                enterFirstname(DataUtility.getJsonData("CheckoutInformation","firstname")).
                enterLastname(DataUtility.getJsonData("CheckoutInformation","lastname")).
                enterZipCode(DataUtility.getJsonData("CheckoutInformation","zipCode")).
                clickOnContinueButton().
                clickOnFinishButton();
        Assert.assertEquals(getDriver().getCurrentUrl(),DataUtility.getPropertiesValue("environments","Finish_URL"));

    }

    @AfterMethod
    public void quit(){
        closeBrowser();
    }


}
