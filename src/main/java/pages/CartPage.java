package pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    private final WebDriver driver ;
    public CartPage(WebDriver driver) {
        this.driver = driver ;
    }
    private final By pricesOfSelectedProducts = By.xpath("//button[text()='Remove']/preceding-sibling::div[@class='inventory_item_price']");
    float totalPrices = 0 ;
    private final By checkoutBtn = By.id("checkout");

    public float getTotalPrices(){
        List<WebElement> prices = driver.findElements(pricesOfSelectedProducts);
        for (WebElement element : prices){
            String price = element.getText();
            totalPrices+= Float.parseFloat(price.replace("$",""));
        }
        return totalPrices ;
    }

    public CheckoutPage clickOnCheckoutBtn(){
        Utility.clickOnElement(driver,checkoutBtn);
        return new CheckoutPage(driver);

    }
}
