package com.manusunny.pages;

import com.manusunny.framework.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends MobileDriver {

    public void verifyTextView() {
        AndroidDriver driver = MobileDriver.getInstance();
        WebElement textView = driver.findElement(By.id("text"));
        String textViewText = "Hello World!";
        assert textView.getText().equals(textViewText) : "Actual value is : " + textView.getText() + " did not match with expected value: " + textViewText;
        System.out.println("-> Value of TextView :  " + textView.getText());
    }

    public void verifySnackBar() {
        AndroidDriver driver = MobileDriver.getInstance();
        WebElement fab = driver.findElement(By.id("fab"));
        fab.click();
        WebElement snackBar = driver.findElement(By.id("com.manusunny.materialdesign:id/snackbar_text"));
        String snackBarText = "Replace with your own action";
        assert snackBar.getText().equals(snackBarText) : "Actual value is : " + snackBar.getText() + " did not match with expected value: " + snackBarText;
        System.out.println("-> Value of SnackBar :  " + snackBar.getText());
    }
}
