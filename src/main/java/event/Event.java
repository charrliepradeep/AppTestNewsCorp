package event;

import org.openqa.selenium.remote.RemoteWebDriver;

import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;

public abstract class Event extends BaseTest{
	protected AndroidDriver _driver;
	
	
	 public Event(){ 
		 super(); 
		 }
	
	
	public abstract boolean execute();
	
	
}
