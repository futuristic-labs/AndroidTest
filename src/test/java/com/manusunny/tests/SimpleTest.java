package com.manusunny.tests;

import com.manusunny.framework.BaseTest;
import com.manusunny.pages.HomePage;

public class SimpleTest extends BaseTest {
    @org.testng.annotations.Test(description = "Basic test")
    public void verify() throws Exception {
        HomePage homePage = new HomePage();
        homePage.verifyTextView();
        homePage.verifySnackBar();
    }
}