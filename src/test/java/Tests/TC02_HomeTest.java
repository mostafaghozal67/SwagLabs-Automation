package Tests;

import Utilities.DataUtility;
import io.qameta.allure.Description;
import listeners.IInvokedListenerClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.io.FileNotFoundException;
import java.io.IOException;

import static DriverFactory.DriverFactory.*;


@Listeners(IInvokedListenerClass.class)
public class TC02_HomeTest {



    @BeforeMethod
    public void setup() throws IOException {
        setupDriver(DataUtility.getPropertiesValue("environments","Browser"));
        getDriver().get(DataUtility.getPropertiesValue("environments","Base_URL"));

    }



    @Description("This test case verifies that the number of products added to the cart is equal to the number of products on the cart icon")
    @Test
    public void comparingNumberOfSelectedProducts() throws IOException {
        new LoginPage(getDriver()).
                enterUsername(DataUtility.getJsonData("ValidLogin","username")).
                enterPassword(DataUtility.getJsonData("ValidLogin","password")).
                clickOnLoginButton().
                addAllProductsToCart();
        Assert.assertEquals(new HomePage(getDriver()).getNumberOfProductsOnCartIcon(),new HomePage(getDriver()).getNumberOfSelectedProducts());
    }

    @Description("This test case adds a random number of products to the cart and then verifies if the selected products are equal to the number of products on the cart icon")
    @Test
    public void addRandomProductsToTheCart() throws FileNotFoundException {
        new LoginPage(getDriver()).
                enterUsername(DataUtility.getJsonData("ValidLogin","username")).
                enterPassword(DataUtility.getJsonData("ValidLogin","password")).
                clickOnLoginButton().
                addRandomProductsToCart(5);
        Assert.assertEquals(new HomePage(getDriver()).getNumberOfSelectedProducts(),new HomePage(getDriver()).getNumberOfProductsOnCartIcon());
    }

    @Test
    public void clickOnCartIcon() throws IOException {
        new LoginPage(getDriver()).
                enterUsername(DataUtility.getJsonData("ValidLogin","username")).
                enterPassword(DataUtility.getJsonData("ValidLogin","password")).
                clickOnLoginButton().
                clickOnCartIcon();
        Assert.assertEquals(getDriver().getCurrentUrl(),DataUtility.getPropertiesValue("environments","Cart_URL"));
    }


    @AfterMethod
    public void quit(){
        closeBrowser();
    }
}
