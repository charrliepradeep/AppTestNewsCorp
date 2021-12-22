package event;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Rotate extends Event {

	public Rotate(ThreadLocal<AppiumDriver> driver) {
		super();
	}

	@Override
	public boolean execute() {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("operation", "next");
		getDriver().rotate(new DeviceRotation(0, 90, 0));
		return true;
	}

}
