package pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private final WebDriver driver ;
    public CheckoutPage(WebDriver driver) {
        this.driver= driver ;
    }

    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By zipCode = By.id("postal-code");
    private final By continueBtn = By.id("continue");

    public CheckoutPage enterFirstname(String Firstname){
        Utility.sendData(driver,firstName,Firstname);
        return this;
    }

    public CheckoutPage enterLastname(String Lastname){
        Utility.sendData(driver,lastName,Lastname);
        return this;
    }

    public CheckoutPage enterZipCode(String ZipCode){
        Utility.sendData(driver,zipCode,ZipCode);
        return this;
    }

    public OverViewPage clickOnContinueButton(){
        Utility.clickOnElement(driver,continueBtn);
        return new OverViewPage(driver);
    }



}
