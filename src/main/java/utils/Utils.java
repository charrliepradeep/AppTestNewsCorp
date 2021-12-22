package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.common.collect.ImmutableMap;

import event.Back;
import event.Event;
import event.EventType;
import event.FindButtonClick;
import event.FindImageButton;
import event.FindImageClick;
import event.FindLinkClick;
import event.FindTextClick;
import event.FindView;
import event.Rotate;
import event.StartApp;
import event.Swipe;
import event.Tap;
import event.horizontalSwipe;
import event.scrollVertically;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

import static io.appium.java_client.touch.offset.PointOption.*;

public class Utils {
	static TestUtils utils = new TestUtils();
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}
	
	public static void startApp(String appName, ThreadLocal<AppiumDriver> driver){
		
		Random rand = new Random();
		int width = rand.nextInt(100);
		int height = rand.nextInt(100);
		String command = "mobile: scrollGesture";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("location", "" + width +"," + height);
		
		//Object result = remoteWebDriver.executeScript(command, params);
		/*
		 * boolean canScrollMore = (boolean) driver.executeScript(command,
		 * ImmutableMap.of("left", 100, "top", 100, "width", 200, "height", 200,
		 * "direction", "down", "percent", 4.0 ));
		 */
		//remoteWebDriver.executeScript("mobile: doubleClickGesture", ImmutableMap.of("elementId", ((WebElement) element).getId());
		
		//System.out.println("**** Start app ended with result: "+canScrollMore);
	}
	
	private static void closeApp(String appName, AndroidDriver driver){
		String command = "mobile:application:close";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", appName);
		Object result = driver.executeScript(command, params);
		System.out.println("**** close app ended with result: "+result);
	}
	
	
	private static void cleanApplications(AndroidDriver driver){
		String command = "mobile:application:reset";
		Object result = driver.executeScript(command);
		System.out.println("**** clean app ended with result: "+result);
	}
	
	private static void uninstallApp(String appName, AndroidDriver driver){
		String command = "mobile:application:uninstall";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", appName);
		Object result = driver.executeScript(command, params);
		System.out.println("**** uninstall app ended with result: "+result);
	}
	private static void switchToContext(AndroidDriver driver, String context) {
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		Map<String,String> params = new HashMap<String,String>();
		params.put("name", context);
		executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
	}
	
	/**
	 * Randomize and execute the monkey events
	 * @param events
	 * @param driver
	 */
	public static void goCrazy(ArrayList<Event> events, ThreadLocal<AppiumDriver> driver) {
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

	public static ArrayList<EventType> initChances(Map<EventType,Integer> eventChances){
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
	public static Event randomEvent(ArrayList<EventType> arr, ThreadLocal<AppiumDriver> driver) {
		
		int length = arr.size();
		
		Random rand = new Random();
		int eventRand = rand.nextInt(length);
		
		EventType eventType = arr.get(eventRand);
		
		Event event = null;
		switch(eventType){
		case FIND_TEXT_CLICK:
			event = new FindTextClick(driver);
			break;	
		case SWIPE:
			event = new Swipe(driver);
			break;		
		case SCROLL_VERTICALLY:
			event = new scrollVertically(driver);
			break;			
		case FIND_IMAGE_BUTTON:
			event = new FindImageButton(driver);
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
		case HORIZONTAL_SWIPE:
			event = new horizontalSwipe(driver);
			break;			
		case START_APP:
			event = new StartApp(driver,"br.com.golmobile.nypost.dev");
			break;
		case ROTATE:
			event = new Rotate(driver);
			break;	
		//case HOME:
		//	event = new Home(driver,"br.com.golmobile.nypost.dev");
		//	break;		
		case TAP:
			event = new Tap(driver);
			break;	
		case BACK:
			event = new Back(driver);
			break;				
		}
		
		return event;
	
	

}
	
	//Method - 1 
	public static void voidSwipevertical(AppiumDriver appiumDriver, double startPercentage, double endPercentage){
	    Dimension size=appiumDriver.manage().window().getSize();
	    int width=(int)(size.getWidth()/2);
	    int startPoint=(int)(size.getHeight()*startPercentage);

	    int endPoint=(int)(size.getHeight()*endPercentage);
	    new TouchAction(appiumDriver).press(PointOption.point(width, startPoint)).waitAction().moveTo(PointOption.point(width, endPoint)).release().perform();

	}

	//Method - 2 
	public static void voidSwipevertical2(AndroidDriver<MobileElement> driver, double startPercentage, double endPercentage){
	    Dimension size=driver.manage().window().getSize();
	    int width=(int)(size.getWidth()/2);
	    int startPoint=(int)(size.getHeight()*startPercentage);

	    int endPoint=(int)(size.getHeight()*endPercentage);
	    new TouchAction(driver).press(PointOption.point(width, startPoint)).moveTo(PointOption.point(width, endPoint)).release().perform();
	}

	//Method - 3
	public static void voidSwipevertical3(AndroidDriver<MobileElement> driver, double startPercentage, double endPercentage) throws Exception{
	    Dimension size=driver.manage().window().getSize();
	    int width=(int)(size.getWidth()/2);
	    int startPoint=(int)(size.getHeight()*startPercentage);

	    int endPoint=(int)(size.getHeight()*endPercentage);

	    TouchAction action = new TouchAction(driver); 
	    action.longPress(PointOption.point(width, startPoint)).moveTo(PointOption.point(width, endPoint)).release().perform();
	}
	
	public static boolean swipeFromUpToBottom(ThreadLocal<AppiumDriver> driver) 
    {

     try {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          HashMap<String, String> scrollObject = new HashMap<String, String>();
          scrollObject.put("direction", "up");
          js.executeScript("mobile: scroll", scrollObject);
         // System.out.println("Swipe up was Successfully done.");
          utils.log().info("Swipe up was Successfully done.");
        }
           catch (Exception e) 
            {
               // System.out.println("swipe up was not successfull");
                utils.log().error("swipe up was not successfull");
            }   
        return false;               
    }
	
	 public static boolean swipeFromBottomToUp(ThreadLocal<AppiumDriver> driver) 
	    {       
	      try  {
	              JavascriptExecutor js = (JavascriptExecutor) driver;
	              HashMap<String, String> scrollObject = new HashMap<String,String>();
	              scrollObject.put("direction", "down");
	              js.executeScript("mobile: scroll", scrollObject);
	             // System.out.println("Swipe down was Successfully done");
	              utils.log().info("Swipe down was Successfully done");
	        }
	           catch (Exception e) 
	            {
	                //System.out.println("swipe down was not successfull");
	                utils.log().info("swipe down was not successfull");
	            }   
	        return false;                   
	    }	
	
}
