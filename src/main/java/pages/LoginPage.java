package pages;


import Utilities.Utility;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver ;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public final By username = By.id("user-name");
    public final By password = By.id("password");
    public final By loginBtn = By.cssSelector("input[class *= 'btn_action']");

    @Step("enter the username")
    public LoginPage enterUsername(String name){
        Utility.sendData(driver,username,name);
        return this;
    }

    @Step("enter the password")
    public LoginPage enterPassword(String Password){
        Utility.sendData(driver,password,Password);
        return this;
    }

    @Step("Click on the login button")
    public HomePage clickOnLoginButton(){
        Utility.clickOnElement(driver,loginBtn);
        return new HomePage(driver);
    }


}
