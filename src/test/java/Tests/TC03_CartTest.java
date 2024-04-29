package Tests;

import Utilities.DataUtility;
import io.qameta.allure.Description;
import listeners.IInvokedListenerClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import java.io.FileNotFoundException;
import java.io.IOException;

import static DriverFactory.DriverFactory.*;


@Listeners(IInvokedListenerClass.class)
public class TC03_CartTest {

    @BeforeMethod
    public void setup() throws IOException {
        setupDriver(DataUtility.getPropertiesValue("environments","Browser"));
        getDriver().get(DataUtility.getPropertiesValue("environments","Base_URL"));
    }

    @Description("This test case verify that the total prices of the selected products in the home page are equal to the total prices of the products in the cart page")
    @Test
    public void comparingPrices() throws FileNotFoundException {
        float totalPrices = new LoginPage(getDriver()).
                enterUsername(DataUtility.getJsonData("ValidLogin","username")).
                enterPassword(DataUtility.getJsonData("ValidLogin","password")).
                clickOnLoginButton().
                addRandomProductsToCart(4).
                getTotalPricesOfSelectedProducts();
        new HomePage(getDriver()).clickOnCartIcon();
        Assert.assertEquals(totalPrices,new CartPage(getDriver()).getTotalPrices());
    }

    @Test
    public void clickOnCheckoutBtn() throws IOException {
        new LoginPage(getDriver()).
                enterUsername(DataUtility.getJsonData("ValidLogin","username")).
                enterPassword(DataUtility.getJsonData("ValidLogin","password")).
                clickOnLoginButton().
                addRandomProductsToCart(4).
                clickOnCartIcon().
                clickOnCheckoutBtn();
        Assert.assertEquals(getDriver().getCurrentUrl(),DataUtility.getPropertiesValue("environments","Checkout_URL"));
    }

    @AfterMethod
    public void quit(){
        closeBrowser();
    }
}
