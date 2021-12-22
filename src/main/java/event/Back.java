package event;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;

public class Back extends Event {

	/*
	 * public Back(AndroidDriver driver) { super(driver); }
	 */

	public Back(ThreadLocal<AppiumDriver> driver) {
		super();
	}

	@Override
	public boolean execute() {
		Map<String, Object> params = new HashMap<String, Object>();
		params = new HashMap<String, Object>();
		params.put("keySequence", "BACK");
		//getDriver().navigate().back();
		((PressesKey) getDriver()).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		//getDriver().executeScript("mobile:longClickGesture", ImmutableMap.of("x", 100, "y", 100, "duration", 1000));
		//_driver.executeScript("mobile: clickGesture", params);
		//_driver.executeScript("mobile: doubleClickGesture", ImmutableMap.of("x", 100, "y", 100, "duration", 1000));
		
		return true;
	}

}
