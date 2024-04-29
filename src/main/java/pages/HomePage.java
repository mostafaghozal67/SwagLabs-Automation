package pages;

import Utilities.Utility;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class HomePage {

    private final WebDriver driver ;


    private final By addToCartBtnForAllProducts = By.cssSelector("button[class *='btn_inventory']");
    private final By numberOfProductsOnCartIcon = By.className("shopping_cart_badge");
    private final By numberOfSelectedProducts = By.xpath("//button[text()='Remove']");
    private final By cartIcon = By.className("shopping_cart_link");
    private final By pricesOfSelectedProducts = By.xpath("//button[text()='Remove']/preceding-sibling::div[@class='inventory_item_price']");
    float totalPrices = 0 ;
    private static List<WebElement> allProducts ;

    public HomePage(WebDriver driver) {
        this.driver = driver;

    }



    @Step("Add all products to the cart")
    public HomePage addAllProductsToCart(){
        allProducts = driver.findElements(addToCartBtnForAllProducts);
        for (WebElement element : allProducts){
            element.click();
        }
        return this;
    }

    @Step("Get the number of products on the cart icon")
    public String getNumberOfProductsOnCartIcon(){

        try { // to handle if there are no products in the cart
            return Utility.getText(driver,numberOfProductsOnCartIcon);
        } catch (Exception e){
            //System.out.println(e.toString());
        }
        return "0" ;
    }

    @Step("Get the number of the selected products")
    public String getNumberOfSelectedProducts(){
        try {
            List<WebElement> selectedProducts = driver.findElements(numberOfSelectedProducts);
            return String.valueOf(selectedProducts.size());
        } catch (Exception e){
            //System.out.println(e.toString());
        }
        return "0" ;
    }

    public HomePage addRandomProductsToCart(int numberOfProductsNeededToBeSelected){
        allProducts = driver.findElements(addToCartBtnForAllProducts);
        Set<Integer> randomNumbers = Utility.generateUniuqeRandomNumbers(numberOfProductsNeededToBeSelected,allProducts.size());
        for (int i : randomNumbers ){
            By addToCartBtnForAllProducts = By.xpath("(//button[contains(@class,'btn_inventory')])[" + i + "]"); // dynamic locator
            Utility.clickOnElement(driver,addToCartBtnForAllProducts);
        }
        return this;
    }

    public float getTotalPricesOfSelectedProducts(){
        List<WebElement> prices = driver.findElements(pricesOfSelectedProducts);
        for (WebElement element : prices){
            String price = element.getText();
            totalPrices+= Float.parseFloat(price.replace("$",""));
        }
        return totalPrices ;
    }

    public CartPage clickOnCartIcon(){
        Utility.clickOnElement(driver, cartIcon);
        return new CartPage(driver);
    }




}
