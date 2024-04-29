package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Utility {

    private static final String ScreenshotPath = "Test-outputs/screenshots";

    public static WebElement getWebElement(WebDriver driver , By locator){
        return driver.findElement(locator);
    }

    public static void clickOnElement(WebDriver driver , By locator){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(locator));
        //driver.findElement(locator).click();
        getWebElement(driver,locator).click();

    }

    public static void sendData(WebDriver driver ,  By locator , String data){

        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        //driver.findElement(locator).sendKeys(data);
        getWebElement(driver,locator).sendKeys(data);
    }

    public static String getText(WebDriver driver , By locator){

        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return getWebElement(driver,locator).getText() ;

    }

    public static WebDriverWait Wait(WebDriver driver){
        return new WebDriverWait(driver,Duration.ofSeconds(10));
    }


    public static void Scroll(WebDriver driver , By locator){
        ((JavascriptExecutor)driver).executeScript("arguments[0]",getWebElement(driver,locator));
    }

    public static String getTimeStamp(){
        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
    }

    public static void takeScreenshot(WebDriver driver , String screenshotName) throws IOException {

        try {
            File screenshotSrc = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File screenshotFile = new File(ScreenshotPath + screenshotName + "-" + getTimeStamp() +".png");
            FileUtils.copyFile(screenshotSrc,screenshotFile);
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotFile.getPath())));
        } catch (Exception e){
            //System.out.println(e.toString());
        }

    }

    public static void takeFullScreenshot(WebDriver driver,By locator){
        Shutterbug.shootPage(driver, Capture.FULL_SCROLL).highlight(getWebElement(driver,locator)).save(ScreenshotPath);
    }

    public static int generateRandomNumber(int upperBound){

        return new Random().nextInt(upperBound) + 1 ;
    }

    public static Set<Integer> generateUniuqeRandomNumbers(int numberOfProductsNeededToBeSelected , int totalNumberOfProducts){
        Set<Integer> generatedNumbers = new HashSet<>();
        while (generatedNumbers.size() < numberOfProductsNeededToBeSelected){
            int randomNumber = generateRandomNumber(totalNumberOfProducts);
            generatedNumbers.add(randomNumber);
        }
        return generatedNumbers;

    }


}
