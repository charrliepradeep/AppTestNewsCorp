package event;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FindView extends FindElementClick {

	public FindView(ThreadLocal<AppiumDriver> driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.view.View")
	static	List<MobileElement> viewList;
	
	@Override
	protected String getTag() {
		return "android.view.View";
	}

	protected List<MobileElement> getElement() {
		return (List<MobileElement>) viewList;
		
	}
}
