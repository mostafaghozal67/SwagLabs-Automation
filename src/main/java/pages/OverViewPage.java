package pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverViewPage {
    private final WebDriver driver ;

    public OverViewPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By itemTotal = By.cssSelector("div[class='summary_subtotal_label']");
    private final By tax = By.className("summary_tax_label");
    private final By totalPrice = By.className("summary_total_label");
    private final By finishBtn = By.cssSelector("button[class*='cart_button']");

    public float getItemTotal(){
        return Float.parseFloat(Utility.getText(driver,itemTotal).replace("Item total: $",""));
    }
    public float getTax(){
        return Float.parseFloat(Utility.getText(driver,tax).replace("Tax: $",""));
    }
    public float getTotalPrice(){
        return Float.parseFloat(Utility.getText(driver,totalPrice).replace("Total: $",""));
    }
    public String calculateTotalPrice(){
        return String.valueOf(getItemTotal() + getTax()) ;
    }

    public boolean comparePrices(){
        return calculateTotalPrice().equals(String.valueOf(getTotalPrice()));
    }

    public FinishOrderPage clickOnFinishButton(){
        Utility.clickOnElement(driver,finishBtn);
        return new FinishOrderPage(driver);
    }
}
