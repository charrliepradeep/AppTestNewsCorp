package event;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofSeconds;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.Status;

//import com.epam.reportportal.message.ReportPortalMessage;

//import rp.com.google.common.io.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HasOnScreenKeyboard;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import reports.ExtentReport;
import utils.TestUtils;
import utils.Utils;

public abstract class FindElementClick extends Event {
	//private static final Logger LOG = LogManager.getLogger();

	Random rand = new Random();
	TestUtils utils = new TestUtils();
	public FindElementClick(ThreadLocal<AppiumDriver> driver) {
		super();
	}


	
	@SuppressWarnings("deprecation")
	@Override
	public boolean execute() {
		//getDriver().manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		
		// List<WebElement> elements = _driver.findElements(By.xpath("//" + getTag()
		// +"[@hidden=\"false\"]"));		
		//List<WebElement> elements = (List<WebElement>) getElement();
		List<MobileElement> elements =  getElement();
		//List<WebElement> elements = getDriver().findElements(By.xpath("//" + getTag()));

		//getDriver().manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);

		int size = elements.size();

		if (size == 0)
			return true;

		int index = rand.nextInt(size);

		WebElement button = elements.get(index);
		try {	 
			// captureScreenshot(button.getText(), _driver);
			String nextElementToBeClicked="";
			String nextElementType="";
			boolean isFound=false;
			boolean isAd=false;
			nextElementToBeClicked = button.getText();
			nextElementType = button.getAttribute("class");
			utils.log().info("\n" + "****** Action performed on - " + nextElementToBeClicked + "******" + "\n");
			utils.log().info("\n" + "****** Control Type - " + nextElementType + "******" + "\n");
			 ExtentReport.getTest().log(Status.INFO, "****** Action performed on - " + nextElementToBeClicked + "******");
			 ExtentReport.getTest().log(Status.INFO, "****** Control Type - " + nextElementType + "******");
			String checkVideo = button.getAttribute("resource-id");
			isFound = checkVideo.contains("play_icon"); // true
			isAd = checkVideo.contains("Browser Tester"); // true
			if(isFound == false || isAd == false) {
				//JavascriptExecutor jse = (JavascriptExecutor) getDriver();
		        //jse.executeScript("arguments[0].style.border='3px solid red'", button);
				
				//for(int i=1;i<=10;i++){
					//Utils.swipeFromUpToBottom(driver);	
					//Utils.swipeFromBottomToUp(driver);	
					//Utils.voidSwipevertical(getDriver(), 10, 30);
				//}
//******************************************************** can be enabled to perform swipe actions working fine****
				//if(nextElementType.equals("android.widget.TextView") || nextElementType.equals("android.widget.ImageView")) {
				//swipeElementAndroid(button, "DOWN");
				//utils.log().info("****** Swipe down on - " + nextElementToBeClicked + "******");
				//ExtentReport.getTest().log(Status.INFO, "****** Swipe down on - " + nextElementToBeClicked + "******");
				swipeElementAndroid(button, "UP");
				utils.log().info("****** Swipe down up - " + nextElementToBeClicked + "******");
				ExtentReport.getTest().log(Status.INFO, "****** Swipe down up - " + nextElementToBeClicked + "******");
				swipeElementAndroid(button, "LEFT");
				utils.log().info("****** Swipe Left - " + nextElementToBeClicked + "******");
				ExtentReport.getTest().log(Status.INFO, "****** Swipe Left - " + nextElementToBeClicked + "******");
				//swipeElementAndroid(button, "RIGHT");
				//utils.log().info("****** Swipe Right - " + nextElementToBeClicked + "******");
				//ExtentReport.getTest().log(Status.INFO, "****** Swipe Right - " + nextElementToBeClicked + "******");
				//}
//*********************************************************************************************************				
				//if(nextElementToBeClicked != "Latest Stories" || nextElementType != "android.widget.ImageView") {
					utils.log().info("\n" + "****** Tap Action performed on control - " + nextElementToBeClicked + "******" + "\n");
					ExtentReport.getTest().log(Status.INFO, "****** Tap Action performed on control - " + nextElementToBeClicked + "******");
					if(((HasOnScreenKeyboard) getDriver()).isKeyboardShown()) {   getDriver().hideKeyboard(); }
					///new code added to highlight element
					new Actions(getDriver()).moveToElement(button).perform();
					
					new TouchAction(getDriver()).tap(TapOptions.tapOptions().withElement(ElementOption.element(button))).perform();
				//click(button, "****** Tap Action performed on control - " + nextElementToBeClicked);
				Utils.voidSwipevertical(getDriver(), 10, 80);
				//button.click();
				//}
			}
			if(isFound == true) {
				getDriver().findElement(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']")).click();
			}
			if(isAd == true) {				
				//getDriver().pressKey(new KeyEvent(AndroidKey.BACK));
				//getDriver().navigate().back();				
			}
			//AppScreenPrint(nextElementToBeClicked, _driver);
			//takeAppScreenPrint(nextElementToBeClicked, _driver);
		} catch (Exception e) {
			// LOG.error(e.getMessage());
		}

		return true;
	}

	private void takeAppScreenPrint(String nextElementToBeClicked, AndroidDriver driver) {

	
	}

	protected abstract String getTag();
	protected abstract List<MobileElement> getElement();

	public void captureScreenshot(String fileName, AndroidDriver driver) {
		try {
			String aString = fileName;
			String cutString = aString.substring(0, 10);
			new File("target/surefire-reports/").mkdirs();
			FileOutputStream out = new FileOutputStream("target/surefire-reports/screenshot-" + cutString + ".png");
			out.write(driver.getScreenshotAs(OutputType.BYTES));
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void swipeElementAndroid(WebElement button, String string) {
	    System.out.println("swipeElementAndroid(): dir: '" + string + "'"); // always log your actions

	    // Animation default time:
	    //  - Android: 300 ms
	    //  - iOS: 200 ms
	    // final value depends on your app and could be greater
	    final int ANIMATION_TIME = 200; // ms

	    final int PRESS_TIME = 200; // ms

	    int edgeBorder;
	    PointOption pointOptionStart, pointOptionEnd;

	    org.openqa.selenium.Rectangle rect = button.getRect();
	    // sometimes it is needed to configure edgeBorders
	    // you can also improve borders to have vertical/horizontal
	    // or left/right/up/down border variables
	    edgeBorder = 0;

	    switch (string) {
	        case "DOWN": // from up to down
	            pointOptionStart = PointOption.point(rect.x + rect.width / 2,
	                    rect.y + edgeBorder);
	            pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
	                    rect.y + rect.height - edgeBorder);
	            break;
	        case "UP": // from down to up
	            pointOptionStart = PointOption.point(rect.x + rect.width / 2,
	                    rect.y + rect.height - edgeBorder);
	            pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
	                    rect.y + edgeBorder);
	            break;
	        case "LEFT": // from right to left
	            pointOptionStart = PointOption.point(rect.x + rect.width - edgeBorder,
	                    rect.y + rect.height / 2);
	            pointOptionEnd = PointOption.point(rect.x + edgeBorder,
	                    rect.y + rect.height / 2);
	            break;
	        case "RIGHT": // from left to right
	            pointOptionStart = PointOption.point(rect.x + edgeBorder,
	                    rect.y + rect.height / 2);
	            pointOptionEnd = PointOption.point(rect.x + rect.width - edgeBorder,
	                    rect.y + rect.height / 2);
	            break;
	        default:
	            throw new IllegalArgumentException("swipeElementAndroid(): dir: '" + string + "' NOT supported");
	    }

	    // execute swipe using TouchAction
	    try {
	        new TouchAction(getDriver())
	                .press(pointOptionStart)
	                // a bit more reliable when we add small wait
	                .waitAction(waitOptions(ofSeconds(1)))
	                .moveTo(pointOptionEnd)
	                .release().perform();
	    } catch (Exception e) {
	      //  System.err.println("swipeElementAndroid(): TouchAction FAILED\n" + e.getMessage());
	        utils.log().error("swipeElementAndroid(): TouchAction FAILED\n" + e.getMessage());
	        ExtentReport.getTest().log(Status.INFO,"swipeElementAndroid(): TouchAction FAILED\n" + e.getMessage());
	        return;
	    }
	    
	    // always allow swipe action to complete
	    try {
	        Thread.sleep(ANIMATION_TIME);
	    } catch (InterruptedException e) {
	        // ignore
	    }
	}
	

}
