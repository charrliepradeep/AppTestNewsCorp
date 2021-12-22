package event;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FindImageClick extends FindElementClick {

	public FindImageClick(ThreadLocal<AppiumDriver> driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView")
	static	List<MobileElement> imageViewList;
	
	@Override
	protected String getTag() {
		return "android.widget.ImageView";
	}

	protected List<MobileElement> getElement() {
		return (List<MobileElement>) imageViewList;
		
	}
}
