package com.manusunny.framework;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseTest {

    @DataProvider(name = "init")
    public Object[][] injectPage(Method method) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Test test = method.getAnnotation(Test.class);
        Class<? extends Page> clazz = test.page();
        if (clazz.equals(Object.class)) {
            return new Object[][]{{}};
        }

        return new Object[1][1];
    }

    @AfterMethod(alwaysRun = true)
    public void takeScreenShotOnFailure(ITestResult testResult, Method method) throws Exception {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            System.out.println(testResult.getStatus());
            RemoteWebDriver driver = MobileDriver.getInstance();
            File scrFile = driver.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("./screenshots/ErrorScreenshot" + method.getName() + " .png"));
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        MobileDriver.quit();
    }

    public interface Page {
        void setup();
    }
}
