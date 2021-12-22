package com.qa.tests;

import base.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import pages.NYP;
import utils.TestUtils;

import org.testng.annotations.*;

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

public class NYPTest_MonkeyTest extends BaseTest {	
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
    }

    @AfterMethod
    public void afterMethod() {
    }

    @Test
    public void NYTestPost() throws InterruptedException, InvocationTargetException {
    	
    NYP.pressContinueButton();

    	
     utils.log().info("\n" + "****** stopping test: Test Completed Successfully******" + "\n");
    	
    }
    
    @Test
    public void NYPostMonkeyTest() throws Exception {
    	
    	utils.log().info("\n" + "****** Starting Monkey Test ******" + "\n");
    		
    		//Give chances for each event
    		Map<EventType,Integer> chances = new HashMap<EventType,Integer>();
    		chances.put(EventType.FIND_BUTTON_CLICK, 200);
    		chances.put(EventType.FIND_TEXT_CLICK, 200);
    		chances.put(EventType.FIND_IMAGE_CLICK, 100);
    		chances.put(EventType.FIND_LINK_CLICK, 100);		
    		chances.put(EventType.FIND_IMAGE_BUTTON, 200);	
    		chances.put(EventType.FIND_VIEW, 100);			
    		chances.put(EventType.ROTATE, 50);
    		chances.put(EventType.HOME, 50);
    		chances.put(EventType.BACK, 50);
    		chances.put(EventType.TAP, 50);
    		chances.put(EventType.START_APP, 50);
    		
    		//How many times do you want to random events and execute?
    		for (int i=0; i<1; i++){
    			
    			//Random events
    			ArrayList<Event> events = randomEvents(50,chances,driver);
    			
    			//Release the monkey
    			goCrazy(events,driver);
    		}
    	}
    	
    	/**
    	 * Randomize and execute the monkey events
    	 * @param events
    	 * @param driver
    	 */
    	private static void goCrazy(ArrayList<Event> events, ThreadLocal<AppiumDriver> driver) {
    		Iterator<Event> it = events.iterator();
    		
    		//Run the monkey testing. After each test check if the app on front.
    		while (it.hasNext()){
    			Event event = it.next();
    			event.execute();
    			
    			//String command = "mobile:application:info";
    			//Map<String, Object> params = new HashMap<String, Object>();
    			//params.put("property", "frontapp");
    			//String frontapp = (String) driver.executeScript(command, params);
    			
    			//Map<String, Object> params = new HashMap<>();
    			//params.put("property", "model");
    			//String model = (String) driver.executeScript("mobile:deviceInfo", params);
    			//System.out.println("**** Model name: "+model);
    			//if (!frontapp.equalsIgnoreCase("br.com.golmobile.nypost.dev")){
    				//Utils.startApp("br.com.golmobile.nypost.dev", driver);
    				
    				//Utils.sleep(1000);
    			//}
    		}
    	}

    	/**
    	 * Randomize a monkey event
    	 * @param eventsNum
    	 * @param eventChances
    	 * @param driver
    	 * @return
    	 */
    	public static ArrayList<Event> randomEvents(int eventsNum, Map<EventType,Integer> eventChances, ThreadLocal<AppiumDriver> driver) {
    		ArrayList<EventType> arr = initChances(eventChances);
    		ArrayList<Event> events = new ArrayList<Event>();
    		
    		for (int i=0; i<eventsNum; i++){
    			Event event = randomEvent(arr,driver);
    			events.add(event);
    		}
    		
    		return events;
    	}

    	private static ArrayList<EventType> initChances(Map<EventType,Integer> eventChances){
    		ArrayList<EventType> arr = new ArrayList<EventType>();
    		for (EventType eventType : eventChances.keySet()){
    			Integer chances = eventChances.get(eventType);
    			
    			for (int i=0; i<chances; i++){
    				arr.add(eventType);
    			}
    		}
    		
    		return arr;
    	}
    	
    	/**
    	 * initialized events handler for the randomizer
    	 * @param arr
    	 * @param driver
    	 * @return
    	 */
    	private static Event randomEvent(ArrayList<EventType> arr, ThreadLocal<AppiumDriver> driver) {
    		
    		int length = arr.size();
    		
    		Random rand = new Random();
    		int eventRand = rand.nextInt(length);
    		
    		EventType eventType = arr.get(eventRand);
    		
    		Event event = null;
    		switch(eventType){
    		case FIND_IMAGE_BUTTON:
    			event = new FindImageButton(driver);
    			break;				
    		case FIND_TEXT_CLICK:
    			event = new FindTextClick(driver);
    			break;		
    		case FIND_IMAGE_CLICK:
    			event = new FindImageClick(driver);
    			break;		
    		case FIND_LINK_CLICK:
    			event = new FindLinkClick(driver);
    			break;	
    		case FIND_BUTTON_CLICK:
    			event = new FindButtonClick(driver);
    			break;			
    		case FIND_VIEW:
    			event = new FindView(driver);
    			break;				
    		case START_APP:
    			event = new StartApp(driver,"br.com.golmobile.nypost.dev");
    			break;
    		case ROTATE:
    			event = new Rotate(driver);
    			break;	
    		case HOME:
    			event = new Home(driver,"br.com.golmobile.nypost.dev");
    			break;		
    		case TAP:
    			event = new Tap(driver);
    			break;	
    		case BACK:
    			event = new Back(driver);
    			break;				
    		}
    		
    		return event;
    	
    	

    }


}
