package com.qa.tests;

import base.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
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

public class dailyTelegraph extends BaseTest {	
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
	    	pages.dailyTelegraph dyt = new pages.dailyTelegraph();
	        utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
	       // ExtentReport.getTest().log(Status.INFO, "****** starting test:" + m.getName() + "******");
	    }

	    @AfterMethod
	    public void afterMethod() {
	    }

	    @Test (priority=1)
	    public void DailyTelegraphPost() throws InterruptedException, InvocationTargetException {
	    	
	    //lakshmi commented below line to check daily telegraph
	    	//NYP.pressContinueButton();
	    	pages.dailyTelegraph.pressContinueButton();
	    	
	     utils.log().info("\n" + "****** stopping test: Test Completed Successfully******" + "\n");
	     //ExtentReport.getTest().log(Status.INFO, "****** stopping test: Test Completed Successfully******");
	    	
	    }
	    
	    @Test (priority=2)
	    public void NYPostMonkeyTest2() throws Exception {
	    	
	    	utils.log().info("\n" + "****** Starting Monkey Test ******" + "\n");
	    		
	    		//Give chances for each event
	    		Map<EventType,Integer> chances = new HashMap<EventType,Integer>();
	    		chances.put(EventType.FIND_BUTTON_CLICK, 200);
	    		chances.put(EventType.FIND_TEXT_CLICK, 1000);
	    		chances.put(EventType.FIND_IMAGE_CLICK, 200);
	    		chances.put(EventType.FIND_LINK_CLICK, 200);		
	    		chances.put(EventType.FIND_IMAGE_BUTTON, 200);	
	    		chances.put(EventType.FIND_VIEW, 100);			
	    		chances.put(EventType.ROTATE, 50);
	    		//chances.put(EventType.HOME, 50);
	    		chances.put(EventType.BACK, 50);
	    		chances.put(EventType.TAP, 1000);
	    		chances.put(EventType.START_APP, 50);
	    		
	    		//How many times do you want to random events and execute?
	    		for (int i=0; i<1; i++){
	    			
	    			//Random events
	    			ArrayList<Event> events = Utils.randomEvents(50,chances,driver);
	    			
	    			//Release the monkey
	    			Utils.goCrazy(events, driver);
	    			//goCrazy(events,getDriver());
	    		}
	    	}
    	
    	
}
