package event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.Dimension;

import com.aventstack.extentreports.Status;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import reports.ExtentReport;
import io.appium.java_client.TouchAction;

/*
 * Execute touch on random coordinates
 */
public class Tap extends Event {
	Random rand = new Random();
	
	public Tap(ThreadLocal<AppiumDriver> driver) {
		super();
	}

	@Override
	public boolean execute() {
		int width = rand.nextInt(100);
		int height = rand.nextInt(100);
		
		
		//JavascriptExecutor js = (JavascriptExecutor) getDriver();
		//HashMap<String, String> scrollObject = new HashMap<String, String>();
		//scrollObject.put("direction", "down");
		//js.executeScript("mobile: scroll", "direction: down");
		
	//	Map<String, Object> params = new HashMap<String, Object>();
		//params.put("location", "" + width +"%," + height + "%");

		//new TouchAction(getDriver()).tap(TapOptions.tapOptions().withElement(ElementOption.element(button))).perform();
		

		//touch tap command not valid
		//_driver.executeScript("mobile:touch:tap", params);
		//_driver.executeScript("mobile: doubleClickGesture", params);
		/*
		 * _driver.executeScript("mobile: swipeGesture", ImmutableMap.of( "left", 100,
		 * "top", 100, "width", 200, "height", 200, "direction", "left", "percent",
		 * 0.35));
		 */
		//new TouchAction(_driver).tap(TapOptions.tapOptions().withElement(ElementOption.element(button))).perform();
		//((PressesKey) getDriver()).pressKey(new KeyEvent().withKey(AndroidKey.TAB));	
		tapOnMiddleOfScreenOnDevice();
		return true;
	}

	

	//act.press(PointOption.point(startX,centerY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3))).moveTo(PointOption.point(endX,centerY)).release().perform();
	private void scrollDown() {
	    //if pressX was zero it didn't work for me
	    int pressX = getDriver().manage().window().getSize().width / 2;
	    // 4/5 of the screen as the bottom finger-press point
	    int bottomY = getDriver().manage().window().getSize().height * 4/5;
	    // just non zero point, as it didn't scroll to zero normally
	    int topY = getDriver().manage().window().getSize().height / 8;
	    //scroll with TouchAction by itself
	   // scroll(pressX, bottomY, pressX, topY);
	    
	    swipeFromUpToBottom();
	}

	/*
	 * don't forget that it's "natural scroll" where 
	 * fromY is the point where you press the and toY where you release it
	 */
	public boolean swipeFromUpToBottom() 
    {

     try {
          JavascriptExecutor js = (JavascriptExecutor) getDriver();
          HashMap<String, String> scrollObject = new HashMap<String, String>();
          scrollObject.put("direction", "up");
          js.executeScript("mobile: scroll", scrollObject);
          System.out.println("Swipe up was Successfully done.");
        }
           catch (Exception e) 
            {
                System.out.println("swipe up was not successfull");
            }   
        return false;               
    }
	
    private void tapOnMiddleOfScreenOnDevice() {
        
        Dimension screenSize = getDriver().manage().window().getSize();
        int midHeight = screenSize.height / 2;
        int midWidth = screenSize.width / 2;
        String msg = String.format("tapOnMiddleOfScreen: Screen dimensions: '%s'. Tapping on coordinates: %d:%d%n", screenSize.toString(), midWidth, midHeight);
        ExtentReport.getTest().log(Status.INFO, msg);       
        TouchAction touchAction = new TouchAction(getDriver());
        touchAction
                .tap(PointOption.point(midWidth, midHeight))
                .perform();
    }
}
