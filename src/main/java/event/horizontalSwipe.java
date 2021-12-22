package event;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.TouchAction;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;
import static io.appium.java_client.touch.WaitOptions.waitOptions;

/*
 * Execute touch on random coordinates
 */
public class horizontalSwipe extends Event {
	//Random rand = new Random();
	
	private static final Direction DOWN = null;

	public horizontalSwipe(ThreadLocal<AppiumDriver> driver) {
		super();
	}

	@Override
	public boolean execute() {
		org.openqa.selenium.Dimension size = getDriver().manage().window().getSize();
		
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		//js.executeScript("mobile: scroll", scrollObject);
		
		List<AndroidElement> elements = getDriver().findElements(By.xpath("//android.widget.TextView"));
		//System.out.println("Total Elements "+ elements.size());
		AndroidElement toElement = elements.get(0);
		AndroidElement fromElement = elements.get(1);
		int centerY = fromElement.getLocation().y + (fromElement.getSize().height/2);
		int startX= fromElement.getLocation().x;
		int startY= fromElement.getLocation().y;
		int endX= toElement.getLocation().x;
		int endY= toElement.getLocation().y;
		swipe(startX, startY, endX, endY, 100);
		
		return true;
	}
	
	  public void swipe(int x_start, int y_start, int x_stop, int y_stop, int duration) {
		  new TouchAction(getDriver()).press(point(x_start, y_start)).waitAction(waitOptions(ofSeconds(duration)))
	  .moveTo(point(x_stop, y_stop)) .release() .perform();
	  }
	  
	  

	
}
