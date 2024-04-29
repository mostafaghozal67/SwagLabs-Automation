package listeners;

import Utilities.Utility;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.IOException;

import static DriverFactory.DriverFactory.getDriver;

public class IInvokedListenerClass implements IInvokedMethodListener {

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
    }




    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        if(testResult.getStatus() == ITestResult.FAILURE){
            try {
                Utility.takeScreenshot(getDriver(),testResult.getName());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

