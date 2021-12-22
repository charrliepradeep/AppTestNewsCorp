package com.qa.tests;

import base.BaseTest;
import pages.NYPostPage;
import pages.dailyTelegraph;
import utils.TestUtils;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class NYPostTest extends BaseTest {	
  
	//public static NYPostPage NYPost;
	//NYPostPage NYPostPage;
    TestUtils utils = new TestUtils();    
    NYPostPage NYPostPage = PageFactory.initElements((WebDriver) driver, NYPostPage.class);
    dailyTelegraph dailyTelegraph = PageFactory.initElements((WebDriver) driver, dailyTelegraph.class);

    @BeforeClass
    public void beforeClass() {
        //closeApp();
        //launchApp();
    	//NYPost = new NYPostPage(driver);
    	//PageFactory.initElements((WebDriver) driver, NYPostPage);
    }

    @AfterClass
    public void afterClass() {
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
    }

    @AfterMethod
    public void afterMethod() {
    }

    @Test
    public void NYPost() throws InterruptedException {
    	//NYPost.continueBtnClick();
    	//lakshmicommented below line to check daily telegraph *************************
    	NYPostPage.continueBtnClick();
    	//***********************************************************************
    	//dailyTelegraph.pressContinueButton();
    	//NYPostPage.loadingIcon();
    	//try {
    	//NYPostPage.continueBtnClick();
    	//} catch (Exception e) {
         //   System.out.println(e);
       // }

    }

}
