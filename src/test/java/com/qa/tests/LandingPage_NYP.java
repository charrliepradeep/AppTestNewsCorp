package com.qa.tests;

import base.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import pages.NYP;
import reports.ExtentReport;
import utils.TestUtils;

import org.testng.annotations.*;

import com.aventstack.extentreports.Status;

import event.Back;
import event.Event;
import event.EventType;
import event.FindButtonClick;
import event.FindImageButton;
import event.FindImageClick;
import event.FindLinkClick;
import event.FindTextClick;
import event.FindView;
import event.Home;
import event.Rotate;
import event.StartApp;
import event.Tap;
import utils.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class LandingPage_NYP extends BaseTest {	
	//AndroidDriver driver = new AndroidDriver(null);
	BaseTest BaseTest;
	//NYP NYP = new NYP();
	//NYP NYP;
	 TestUtils utils = new TestUtils();
	 

     
    @BeforeClass
    public void beforeClass() {
       // closeApp();
       // launchApp(); 
    }

    @AfterClass
    public void afterClass() {
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
    	NYP NYP = new NYP();
        utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
        //ExtentReport.getTest().log(Status.INFO, "****** starting test:" + m.getName() + "******");
    }

    @AfterMethod
    public void afterMethod() {
    }

    @Test
    public void NYTestPostLandingPage() throws InterruptedException, InvocationTargetException {
    	
    	NYP.pressContinueButton();
    	
     utils.log().info("\n" + "****** stopping test: Test Completed Successfully******" + "\n");
     //ExtentReport.getTest().log(Status.INFO, "****** stopping test: Test Completed Successfully******");
    	
    }   	
    	
}
