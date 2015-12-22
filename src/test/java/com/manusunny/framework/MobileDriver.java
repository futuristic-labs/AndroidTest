package com.manusunny.framework;

import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MobileDriver {

    private static final Logger LOGGER = LogManager.getLogger(MobileDriver.class);
    private static AndroidDriver driver;
    private static AppiumServer server;

    public static AndroidDriver getInstance() {
        if (driver == null) {
            createDriver();
        }
        return driver;
    }

    private static void createDriver() {
        LOGGER.debug("Driver has not been initialised...");
        try {
            server = new AppiumServer();
            server.start();
            Thread.sleep(3000);
            while (!server.isRunning()) ;
            LOGGER.debug("Appium server started.");
            System.out.println("Initialising device...\n\n");
            File appDir = new File(".");
            File app = new File(appDir.getAbsolutePath(), "app/app.apk");
            URL address = new URL("http://127.0.0.1:4723/wd/hub");

            driver = new AndroidDriver(address, getDesiredCapabilities(app));
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            LOGGER.debug("Device initialised.\n");
        } catch (Exception e) {
            LOGGER.error("Cannot initialise driver!", e);
            e.printStackTrace();
            LOGGER.error("Cannot create Appium driver! : " + e.getMessage());
        }
    }

    private static DesiredCapabilities getDesiredCapabilities(File app) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "android");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability(CapabilityType.VERSION, "4.3");
        capabilities.setCapability("newCommandTimeout", 180);
        capabilities.setCapability("devices", "Android");
        capabilities.setCapability("deviceName", "");
        capabilities.setCapability("app", app.getAbsolutePath());
        return capabilities;
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
            server.end();
        }
    }
}
