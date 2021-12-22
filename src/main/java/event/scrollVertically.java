package event;

import io.appium.java_client.AppiumDriver;

public class scrollVertically extends Event {
	
	public scrollVertically(ThreadLocal<AppiumDriver> driver) {
		super();
	}

	@Override
	public boolean execute() {
		
		scrollVertically(10,50,40);
		scrollDownByScreenSize();
		return true;
	}
}
