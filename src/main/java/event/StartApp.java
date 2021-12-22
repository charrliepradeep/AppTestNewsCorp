package event;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class StartApp extends Event {

	private String _appName;

	public StartApp(ThreadLocal<AppiumDriver> driver,String appName) {
		super();
		_appName = appName;
	}

	public boolean execute() {
		
		//Utils.startApp(_appName, _driver);
		//Utils.sleep(1000);
		
		return true;
	}


	
	
}
