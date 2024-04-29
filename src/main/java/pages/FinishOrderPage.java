package pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FinishOrderPage {
    private final WebDriver driver ;
    FinishOrderPage(WebDriver driver){
        this.driver = driver ;
    }

    private final By thanksMessage = By.className("complete-header");

    public boolean checkVisibilityOfThanksMessageElement(){
        return Utility.getWebElement(driver,thanksMessage).isDisplayed();
    }


}
