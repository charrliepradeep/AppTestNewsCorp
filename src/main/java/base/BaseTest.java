package base;

import com.aventstack.extentreports.Status;


import org.openqa.selenium.Dimension;
import reports.ExtentReport;
import utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.HasOnScreenKeyboard;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.screenrecording.BaseStartScreenRecordingOptions;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.screenrecording.ScreenRecordingUploadOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.touch.offset.PointOption;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import static java.lang.String.format;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.ThreadContext;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import org.testng.ITestResult;
import org.testng.annotations.*;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofSeconds;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.remote.RemoteWebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
	private static BaseTest instance = null;
    private static final String API_KEY = "";
    private static final String APPIUM_HUB = "mobilecloud.epam.com";
    private static final String PROJECT_NAME = "";
    
	 public static BaseTest getInstance() {
	        if ( instance == null ) 
	            instance = new BaseTest();
	        return instance;
	    }
	protected static ThreadLocal <AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
	protected static ThreadLocal <Properties> props = new ThreadLocal<Properties>();
	protected static ThreadLocal <HashMap<String, String>> strings = new ThreadLocal<HashMap<String, String>>();
	protected static ThreadLocal <String> platform = new ThreadLocal<String>();
	protected static ThreadLocal <String> dateTime = new ThreadLocal<String>();
	protected static ThreadLocal <String> deviceName = new ThreadLocal<String>();
	private static AppiumDriverLocalService server;
	static TestUtils utils = new TestUtils();
	
	  public static AppiumDriver getDriver() {
		  return driver.get();
	  }
	  
	  public void setDriver(AppiumDriver driver2) {
		  driver.set(driver2);
	  }
	  
	  public Properties getProps() {
		  return props.get();
	  }
	  
	  public void setProps(Properties props2) {
		  props.set(props2);
	  }
	  
	  public HashMap<String, String> getStrings() {
		  return strings.get();
	  }
	  
	  public void setStrings(HashMap<String, String> strings2) {
		  strings.set(strings2);
	  }
	  
	  public String getPlatform() {
		  return platform.get();
	  }
	  
	  public void setPlatform(String platform2) {
		  platform.set(platform2);
	  }
	  
	  public String getDateTime() {
		  return dateTime.get();
	  }
	  
	  public void setDateTime(String dateTime2) {
		  dateTime.set(dateTime2);
	  }
	  
	  public String getDeviceName() {
		  return deviceName.get();
	  }
	  
	  public void setDeviceName(String deviceName2) {
		  deviceName.set(deviceName2);
	  }
	
	public BaseTest() {
		PageFactory.initElements(new AppiumFieldDecorator(getDriver(),Duration.ofSeconds(10)), this);
	}
	

	@BeforeMethod
	public void beforeMethod() {
//		((CanRecordScreen) getDriver()).startRecordingScreen();
//		try
//		{
//		Process p = Runtime.getRuntime().exec("adb shell screenrecord /sdcard/charrlie.mp4");				
//		}
//		catch(Exception e)
//		{
//			System.out.println("Error in running adb shell");
//		}
		
		
		
		((CanRecordScreen)getDriver()).startRecordingScreen();
	}
	
	//stop video capturing and create *.mp4 file
	@AfterMethod
	public synchronized void afterMethod(ITestResult result) throws Exception {
	
//		try
//		{
//		String directoryPath = System.getenv("$BITRISE_SOURCE_DIR");
//		Process p = Runtime.getRuntime().exec("adb pull /sdcard/charrlie.mp4 "+directoryPath);
//		InputStream is = p.getInputStream();
//		BufferedReader br = new BufferedReader(new InputStreamReader(is));
//			while(p.waitFor()==0)
//			{
//				while(br.readLine()!=null)
//				{
//					System.out.println(br.readLine());
//					utils.log().info(br.readLine());
//				}
//				
//			}
//		br.close();
//		is.close();
//		p.destroy();
//		}
//		catch(Exception e)
//		{
//			System.out.println("Error in running adb shell");
//		}
		String media = ((CanRecordScreen) getDriver()).stopRecordingScreen();
		
		Map <String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();		
		String dirPath = "videos" + File.separator + params.get("platformName") + "_" + params.get("deviceName") 
		+ File.separator + getDateTime() + File.separator + result.getTestClass().getRealClass().getSimpleName();
		
		File videoDir = new File(dirPath);
		
		synchronized(videoDir){
			if(!videoDir.exists()) {
				videoDir.mkdirs();
			}	
		}
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(videoDir + File.separator + result.getName() + ".mp4");
			stream.write(Base64.decodeBase64(media));
			stream.close();
			utils.log().info("video path: " + videoDir + File.separator + result.getName() + ".mp4");
		} catch (Exception e) {
			utils.log().error("error during video capture" + e.toString());
		} finally {
			if(stream != null) {
				stream.close();
			}
		}		
	}
	
	@BeforeSuite
	public void beforeSuite() throws Exception, Exception {
		ThreadContext.put("ROUTINGKEY", "ServerLogs");
		//server = getAppiumService();
		if(!checkIfAppiumServerIsRunnning(4723)) {
			//server.start();
			//server.clearOutPutStreams();
			utils.log().info("Appium server started");
		} else {
			utils.log().info("Appium server already running");
		}	
	}
	
	public boolean checkIfAppiumServerIsRunnning(int port) throws Exception {
	    boolean isAppiumServerRunning = false;
	    ServerSocket socket;
	    try {
	        socket = new ServerSocket(port);
	        socket.close();
	    } catch (IOException e) {
	    	System.out.println("1");
	        isAppiumServerRunning = true;
	    } finally {
	        socket = null;
	    }
	    return isAppiumServerRunning;
	}
	
	@AfterSuite
	public void afterSuite() {
		//server.stop();
		utils.log().info("Appium server stopped");
	}
	
	public AppiumDriverLocalService getAppiumServerDefault() {
		return AppiumDriverLocalService.buildDefaultService();
	}
	
	public AppiumDriverLocalService getAppiumService() {
		HashMap<String, String> environment = new HashMap<String, String>();
		String androidHomePath = "C:\\Users\\Lakshminarayanan_Kri\\AppData\\Local\\Android\\Sdk";
		String javaJdkHomeBinPath = "C:\\Program Files\\Java\\jdk1.8.0_291\\bin";
		String androidSdkToolsPath = "C:\\Users\\Lakshminarayanan_Kri\\AppData\\Local\\Android\\Sdk\\tools";
		String androidSdkPlatformToolsPath = "C:\\Users\\Lakshminarayanan_Kri\\AppData\\Local\\Android\\Sdk\\platform-tools";
		String nodeJsPath = "C:\\Program Files\\nodejs\\node.exe";
		String appiumMainJs = "C:\\Users\\Acer\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
//		environment.put("PATH", "/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/bin:/Users/Om/Library/Android/sdk/tools:/Users/Om/Library/Android/sdk/platform-tools:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin" + System.getenv("PATH"));
//		environment.put("PATH", javaJdkHomeBinPath.replace("\\", "/") + ":"
//				+ androidSdkToolsPath.replace("\\", "/") + ":"
//				+ androidSdkPlatformToolsPath.replace("\\", "/") + ":"
//				+ System.getenv("PATH"));
//		environment.put("ANDROID_HOME", androidHomePath.replace("\\", "/"));
		return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
//				.usingDriverExecutable(new File("/usr/local/bin/node"))
//				.usingDriverExecutable(new File(nodeJsPath.replace("\\", "/")))
//				.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
				.withAppiumJS(new File(appiumMainJs.replace("\\", "/")))
				.usingPort(4723)
				.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
				.withEnvironment(environment)
				.withLogFile(new File("ServerLogs/server.log")));
	}
	
  @Parameters({"emulator", "platformName", "udid", "deviceName", "systemPort", 
	  "chromeDriverPort", "wdaLocalPort", "webkitDebugProxyPort"})
  @BeforeTest
  public void beforeTest(@Optional("androidOnly")String emulator, String platformName, String udid, String deviceName, 
		  @Optional("androidOnly")String systemPort, @Optional("androidOnly")String chromeDriverPort, 
		  @Optional("iOSOnly")String wdaLocalPort, @Optional("iOSOnly")String webkitDebugProxyPort) throws Exception {
	  setDateTime(utils.dateTime());
	  setPlatform(platformName);
	  setDeviceName(deviceName);
	  
	  Process p = Runtime.getRuntime().exec("java --version");
	  InputStreamReader ir =new InputStreamReader(p.getInputStream());
	  BufferedReader br = new BufferedReader(ir);
	  while(br.readLine()!=null)
	  {
		  System.out.println(br.readLine());
	  }
	  URL url;
		InputStream inputStream = null;
		InputStream stringsis = null;
		Properties props = new Properties();
		AppiumDriver driver;
		
		String strFile = "logs" + File.separator + platformName + "_" + deviceName;
		File logFile = new File(strFile);
		if (!logFile.exists()) {
			logFile.mkdirs();
		}
		//route logs to separate file for each thread
		ThreadContext.put("ROUTINGKEY", strFile);
		utils.log().info("log path: " + strFile);
		
	  try {
		  props = new Properties();
		  String propFileName = "config.properties";
		  //String xmlFileName = "strings/strings.xml";
		  
		  utils.log().info("load " + propFileName);
		  inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		  props.load(inputStream);
		  setProps(props);
		  
		 // utils.log().info("load " + xmlFileName);
		 // stringsis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
		 // setStrings(utils.parseStringXML(stringsis));
		  String apppath = props.getProperty("androidAppLocation");
		  
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setCapability("autoGrantPermissions", true);
			desiredCapabilities.setCapability("platformName", platformName);
			desiredCapabilities.setCapability("deviceName", deviceName);
			//lakshmi revert
			desiredCapabilities.setCapability("udid", udid);
			url = new URL(props.getProperty("appiumURL"));
			
			switch(platformName) {
			case "Android":
				desiredCapabilities.setCapability("automationName", props.getProperty("androidAutomationName"));	
				desiredCapabilities.setCapability("appPackage", props.getProperty("androidAppPackage"));
				//desiredCapabilities.setCapability("appActivity", props.getProperty("androidAppActivity"));
				if(emulator.equalsIgnoreCase("true")) {
					desiredCapabilities.setCapability("avd", deviceName);
					desiredCapabilities.setCapability("avdLaunchTimeout", 200000);
//					desiredCapabilities.setCapability("adbExecTimeout",50000);
//					desiredCapabilities.setCapability("appWaitDuration",50000);
					desiredCapabilities.setCapability("appWaitForLaunch",true);
					
				}
				//desiredCapabilities.setCapability("systemPort", systemPort);
				//desiredCapabilities.setCapability("chromeDriverPort", chromeDriverPort);
				String androidAppUrl = new File(apppath).getAbsolutePath();

//				String androidAppUrl = getClass().getResource(props.getProperty("androidAppLocation")).getFile();
//				String androidAppUrl = "";
				utils.log().info("appUrl is" + androidAppUrl);
				desiredCapabilities.setCapability("app", androidAppUrl);
				
				String key = URLEncoder.encode(API_KEY, StandardCharsets.UTF_8.name());
				
				//lakshmi revert
				driver = new AndroidDriver(url, desiredCapabilities);
				//driver = new AndroidDriver(
		         //       new URL(format("https://%s:%s@%s/wd/hub", PROJECT_NAME, key, APPIUM_HUB)), desiredCapabilities);

				break;
			case "iOS":
				desiredCapabilities.setCapability("automationName", props.getProperty("iOSAutomationName"));
				String iOSAppUrl = getClass().getResource(props.getProperty("iOSAppLocation")).getFile();
				utils.log().info("appUrl is" + iOSAppUrl);
				desiredCapabilities.setCapability("bundleId", props.getProperty("iOSBundleId"));
				desiredCapabilities.setCapability("wdaLocalPort", wdaLocalPort);
				desiredCapabilities.setCapability("webkitDebugProxyPort", webkitDebugProxyPort);
				desiredCapabilities.setCapability("app", iOSAppUrl);

				driver = new IOSDriver(url, desiredCapabilities);
				break;
			default:
				throw new Exception("Invalid platform! - " + platformName);
			}
			setDriver(driver);
			utils.log().info("driver initialized: " + driver);
	  } catch (Exception e) {
		  utils.log().fatal("driver initialization failure. ABORT!!!\n" + e.toString());
		  throw e;
	  } finally {
		  if(inputStream != null) {
			  inputStream.close();
		  }
		  if(stringsis != null) {
			  stringsis.close();
		  }
	  }
  }
  
  public static void waitForVisibility(MobileElement e) {
	  WebDriverWait wait = new WebDriverWait(getDriver(), TestUtils.WAIT);
	  wait.until(ExpectedConditions.visibilityOf(e));
  }

	public void waitForElementDisappear(MobileElement e) {
		WebDriverWait wait = new WebDriverWait(getDriver(), TestUtils.WAIT);
		wait.until(ExpectedConditions.invisibilityOf(e));
	}
  
  public static void waitForVisibility(WebElement e){
	  Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
	  .withTimeout(Duration.ofSeconds(30))
	  .pollingEvery(Duration.ofSeconds(5))
	  .ignoring(NoSuchElementException.class);
	  
	  wait.until(ExpectedConditions.visibilityOf(e));
	  }
  
  public void clear(MobileElement e) {
	  waitForVisibility(e);
	  e.clear();
  }
  
  public void click(MobileElement e) {
	  waitForVisibility(e);
	  e.click();
  }
  
  public static void click(WebElement button, String msg) {

	  if(((HasOnScreenKeyboard) getDriver()).isKeyboardShown()) {   getDriver().hideKeyboard(); }
	  
	  if(button.isDisplayed() && button.isEnabled()){
		  waitForVisibility(button);
		  utils.log().info(msg);
		  ExtentReport.getTest().log(Status.INFO, msg);		  
		  button.click();
	  }
  }
  
  public void sendKeys(MobileElement e, String txt) {
	  waitForVisibility(e);
	  e.sendKeys(txt);
  }
  
  public void sendKeys(MobileElement e, String txt, String msg) {
	  waitForVisibility(e);
	  utils.log().info(msg);
	  ExtentReport.getTest().log(Status.INFO, msg);
	  e.sendKeys(txt);
  }
  
  public String getAttribute(MobileElement e, String attribute) {
	  waitForVisibility(e);
	  return e.getAttribute(attribute);
  }
  
  public String getText(MobileElement e, String msg) {
	  String txt = null;
	  switch(getPlatform()) {
	  case "Android":
		  txt = getAttribute(e, "text");
		  break;
	  case "iOS":
		  txt = getAttribute(e, "label");
		  break;
	  }
	  utils.log().info(msg + txt);
	  ExtentReport.getTest().log(Status.INFO, msg);
	  return txt;
  }
  
  protected int getWindowHeight() {
      Dimension windowSize = getDriver().manage().window().getSize();
      ExtentReport.getTest().log(Status.INFO, "dimension: " + windowSize.toString());
      return windowSize.height;
  }  
  
  protected int getWindowWidth () {
      return getDriver().manage().window().getSize().width;
  }
  
  public void closeApp() {
	 // ((InteractsWithApps) getDriver()).closeApp();
  }
  
  public void launchApp() {
	  ((InteractsWithApps) getDriver()).launchApp();
  }
  
  public static MobileElement scrollToElement() {	  
		return (MobileElement) ((FindsByAndroidUIAutomator) getDriver()).findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
						+ "new UiSelector().resourceId(\"br.com.golmobile.nypost.dev:id/excerpt_text_view\"));");
  }
  
  public void swipeRight() {
		int height = getWindowHeight() / 2;
        int fromWidth = (int) (getWindowWidth() * 0.5);
        int toWidth = (int) (getWindowWidth() * 0.9);
        ExtentReport.getTest().log(Status.INFO, "height: " + height + ", from width: " + fromWidth + ", to width: " + toWidth);
        
        swipe(height, fromWidth, toWidth, 1);	  
	  
  }
  
	 private void swipe (int height, int fromWidth, int toWidth, int duration) {
	        TouchAction touchAction = new TouchAction(getDriver());
	        touchAction.press(PointOption.point(new Point(fromWidth, height)))
	                .waitAction(waitOptions(ofSeconds(duration)))
	                .moveTo(PointOption.point(new Point(toWidth, height)))
	                .release()
	                .perform();
	    }	

	 public void swipeLeft() {
	        int height = getWindowHeight() / 2;
	        int fromWidth = (int) (getWindowWidth() * 0.9);
	        int toWidth = (int) (getWindowWidth() * 0.5);
	        ExtentReport.getTest().log(Status.INFO, "height: " + height + ", from width: " + fromWidth + ", to width: " + toWidth);	        
	        
	        swipe(height, fromWidth, toWidth, 1);
	    }

	    public void scrollVertically (int fromPercentScreenHeight, int toPercentScreenHeight, int percentScreenWidth) {
	        Dimension windowSize = getDriver().manage().window().getSize();
	        ExtentReport.getTest().log(Status.INFO, "dimension: " + windowSize.toString());	        
	        
	        int width = windowSize.width * (percentScreenWidth / 100);
	        int fromHeight = windowSize.height * (fromPercentScreenHeight / 100);
	        int toHeight = windowSize.height * (toPercentScreenHeight / 100);
	        ExtentReport.getTest().log(Status.INFO, "height: " + toHeight + ", from height: " + fromHeight + ", to width: " + width);

	        TouchAction touchAction = new TouchAction(getDriver());
	        touchAction.press(PointOption.point(new Point(width, fromHeight)))
	                .waitAction(waitOptions(Duration.ofSeconds(1)))
	                .moveTo(PointOption.point(new Point(width, toHeight)))
	                .release()
	                .perform();
	    }
	    public void scrollDownByScreenSize() {
	        Dimension windowSize = getDriver().manage().window().getSize();
	        ExtentReport.getTest().log(Status.INFO, "dimension: " + windowSize.toString());
	        
	        int width = windowSize.width / 2;
	        int fromHeight = (int) (windowSize.height * 0.9);
	        int toHeight = (int) (windowSize.height * 0.5);
	        
	        ExtentReport.getTest().log(Status.INFO, "height: " + toHeight + ", from height: " + fromHeight + ", to width: " + width);

	        TouchAction touchAction = new TouchAction(getDriver());
	        touchAction.press(PointOption.point(new Point(width, fromHeight)))
	                .waitAction(waitOptions(Duration.ofSeconds(1)))
	                .moveTo(PointOption.point(new Point(width, toHeight)))
	                .release().perform();
	    }

	    
  public void iOSScrollToElement() {
	  RemoteWebElement element = (RemoteWebElement)getDriver().findElement(By.name("test-ADD TO CART"));
	  String elementID = element.getId();
	  HashMap<String, String> scrollObject = new HashMap<String, String>();
	  scrollObject.put("element", elementID);
//	  scrollObject.put("direction", "down");
//	  scrollObject.put("predicateString", "label == 'ADD TO CART'");
//	  scrollObject.put("name", "test-ADD TO CART");
	  scrollObject.put("toVisible", "sdfnjksdnfkld");
	  getDriver().executeScript("mobile:scroll", scrollObject);
  }

  @AfterTest
  public void afterTest() {
	  getDriver().quit();
  }
}