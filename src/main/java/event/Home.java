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
import utils.Utils;

public class Home extends Event {

	private String _appName;

	public Home(ThreadLocal<AppiumDriver> driver,String appName) {
		super();
		_appName = appName;
	}

	@Override
	public boolean execute() {
		Map<String, Object> params = new HashMap<String, Object>();
		params = new HashMap<String, Object>();
		params.put("keySequence", "HOME");
		((PressesKey) getDriver()).pressKey(new KeyEvent().withKey(AndroidKey.HOME));	
		return true;
	}

}
