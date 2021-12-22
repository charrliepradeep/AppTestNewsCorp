package event;

import io.appium.java_client.AppiumDriver;

public class Swipe extends Event {
	
	public Swipe(ThreadLocal<AppiumDriver> driver) {
		super();
	}

	@Override
	public boolean execute() {
		
		swipeRight();
		swipeLeft();
		return true;
	}

}
