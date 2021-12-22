package pages;

import utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import base.BaseTest;

public class NYPostPage extends BaseTest {

	// private ThreadLocal<AppiumDriver> driver2;
	public WebDriverWait wait;

	
	/*
	 * public NYPostPage(SearchContext driver) { this.driver =
	 * (ThreadLocal<AppiumDriver<WebElement>>) driver; PageFactory.initElements(new
	 * AppiumFieldDecorator(driver), this); }
	 */
	 
	//laskhmi commented below line
	//TestUtils utils = new TestUtils();

	
	  public NYPostPage(ThreadLocal<AppiumDriver<WebElement>> driver) {
	  //base.BaseTest.driver = driver; 
	  PageFactory.initElements(new
	  AppiumFieldDecorator((SearchContext) driver), this); 
	  }
	 

	@AndroidFindBy(className = "android.widget.ProgressBar")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Toggle\"]/parent::*[1]/preceding-sibling::*[1]")
	private static MobileElement loadingIcon;

	@AndroidFindBy(className = "android.view.View")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther...etc")
	private MobileElement saveBtn;

	@AndroidFindBy(xpath = "//android.widget.Button")
	private static MobileElement continueBtn;


	BaseTest BaseTest;

	public static void loadingIcon() throws InterruptedException, NullPointerException {
		try {
			while (loadingIcon.isDisplayed()) {
				Thread.sleep(500);
			}
		} catch (Exception e) {
			System.out.println("The element disappeared");
		}
	}

	public void saveBtnClick() {
		saveBtn.click();
	}

	public void continueBtnClick() {
		// try {
		//((AndroidElement) getWrappedElement()).tap(1, 1500);
		wait = new WebDriverWait((WebDriver) driver, 10);
		wait.until(ExpectedConditions.visibilityOf(continueBtn));
		continueBtn.click();
		// } catch (Exception e) {
		// System.out.println(e);
		// }
		// BaseTest.click(continueBtn);
		// findElement(By.xpath("//android.widget.ImageButton[@content-desc='Navigate
		// up']")).click();
	}

	

}
