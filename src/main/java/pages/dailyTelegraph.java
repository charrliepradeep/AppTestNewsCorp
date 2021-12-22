package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import reports.ExtentReport;
import utils.TestUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import base.BaseTest;



public class dailyTelegraph extends BaseTest {
	
	//public NYP() {
	//	super();
		//PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
	//}
	//private AndroidDriver<AndroidElement> driver;
	
	//public NYP(ThreadLocal<AppiumDriver<WebElement>> driver2) {
	//	this.driver = driver;
	//	PageFactory.initElements(new AppiumFieldDecorator(driver), this);
   // }

	//public NYP(AndroidDriver<AndroidElement> driver) {
    //    this.driver = driver;
    //    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    //}
	
	//@AndroidFindBy(className = "androidx.recyclerview.widget.RecyclerView")
	//    private AndroidElement keyboard;
	 TestUtils utils = new TestUtils();
	BaseTest BaseTest;
	
	 //@resource-id='br.com.golmobile.nypost.dev:id/welcome_continue']"
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,'next_button')]")
	static	MobileElement WelcomeContinue;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'skip_button')]")
	static	MobileElement skipButton;	
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Top Stories']")
	static	MobileElement topStories;
	

    public boolean isDisplayed() {
    	
        return WelcomeContinue.isDisplayed();
    }
    
 //   public void hideKeyboardIfVisible() {
  //      if (keyboard != null) {
  //      	driver.longPressKey(new KeyEvent(AndroidKey.ESCAPE));
  //      }
  //  }    
    
    public static void pressContinueButton () {
    	
    	 //getDriver().findElement(By.xpath("//android.widget.Button")).click();
    	 //hideKeyboardIfVisible();

    		click(WelcomeContinue, "Welcome page Continue button clicked");
    		click(skipButton, "Skip link clicked");
    		click(skipButton, "Skip link clicked");
    		click(skipButton, "Skip link clicked");    		
    		try {
    		scrollToElement();
    		}catch (Exception e) {
    			System.out.println(e);
    		}
    		    		
    		waitForVisibility(topStories);

    }
   

}
