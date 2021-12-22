package event;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FindButtonClick extends FindElementClick {

	/*
	 * public FindButtonClick(AndroidDriver driver) { super(driver); }
	 */
	@AndroidFindBy(xpath = "//android.widget.Switch")
	static List<MobileElement> ButtonList;
	
	public FindButtonClick(ThreadLocal<AppiumDriver> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getTag() {
		return "android.widget.Switch";
	}

	protected List<MobileElement> getElement() {
		return (List<MobileElement>) ButtonList;
		
	}

}
