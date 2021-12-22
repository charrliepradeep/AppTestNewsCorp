package com.qa.tests;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.google.common.collect.ImmutableMap;

import base.BaseTest;
import helper.DeviceConnectHelper;
import pages.NYP;
import pages.dailyTelegraph;
import reports.ExtentReport;
import utils.TestUtils;
import java.util.List;


public class MonkeyADBShell extends BaseTest {
	BaseTest BaseTest;
	TestUtils utils = new TestUtils();
	NYP NYP;
	 @BeforeClass
	    public void beforeClass() {

	    }

	    @AfterClass
	    public void afterClass() {
	    }

	    @BeforeMethod
	    public void beforeMethod(Method m) {
	    	NYP = new NYP();
	    	//dailyTelegraph dailyTelegraph = new dailyTelegraph();
	        utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
	    }

	    @AfterMethod
	    public void afterMethod() {
	    }

	    @Test
	    public void ADBShellMonkeyTest() throws InterruptedException, InvocationTargetException {

	    	String rawCommand = "monkey --throttle 1000 -c android.intent.category.MONKEY -c android.intent.category.LAUNCHER -c android.intent.category.DEFAULT --monitor-native-crashes --kill-process-after-error  -v -v -v 200";
	    	
	    	//NYP.pressContinueButton();
	    	//dailyTelegraph.pressContinueButton();
	    	getDriver().manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
	    	
	    	utils.log().info("\n" + "****** Initial Steps Completed Successfully******" + "\n");
	    	utils.log().info("\n" + "****** ADB shell Monkey Test Started ******" + "\n");
			File adbFile =  new File(getADB());
			if (false == adbFile.exists())
				throw new RuntimeException("ADB not exist!\nCurrent Localtion is " + getADB());
			DeviceConnectHelper.init(getADB());
			
	    	List<String> lsArgs = Arrays.asList("cpuinfo");
	    	Map<String, Object> lsCmd = ImmutableMap.of(
	    	    "command", "dumpsys",
	    	    "args", lsArgs
	    	);
	    	String lsOutput = (String) getDriver().executeScript("mobile: shell", lsCmd).toString();
	    	ExtentReport.getTest().log(Status.INFO, "CPU Info" + lsOutput);	
			
	    	//adb shell monkey -p your.package.name -v 500
			//"--pct-syskeys", "1", "--pct-appswitch", "1", "--pct-touch","43","--pct-motion", "30","--pct-majornav", "50",
	    	List<String> list = Arrays.asList("-p", "br.com.golmobile.nypost.dev", "-v", "1000", "--throttle", "5000", "-c", "android.intent.category.MONKEY", "-c", "android.intent.category.LAUNCHER", "c", "android.intent.category.DEFAULT", "--monitor-native-crashes", "--kill-process-after-error", "--pct-syskeys", "0", "--pct-appswitch", "0", "--pct-majornav", "50", "-v", "-v", "-v");
			//removed system commands
			//List<String> list = Arrays.asList("-p", "com.newscorp.thedailytelegraph", "-v", "500", "--throttle", "1000", "-c", "android.intent.category.MONKEY", "-c", "android.intent.category.LAUNCHER", "c", "android.intent.category.DEFAULT", "--monitor-native-crashes", "--kill-process-after-error", "--pct-touch","30","--pct-motion", "30","--pct-majornav", "20", "--pct-appswitch", "2", "-v", "-v", "-v");
	    	Map<String, Object> argv = new HashMap<>();
	    	argv.put("command", "monkey ");
	    	argv.put("args", list);
	    	String result = getDriver().executeScript("mobile: shell", argv).toString();
	    	utils.log().info(result);
	    	ExtentReport.getTest().log(Status.INFO, result);

	    	
	    	
//	    	List<String> dumpsysReport = Arrays.asList("procstats", "--hours", "1");
//	    	Map<String, Object> argv2 = new HashMap<>();
//	    	argv.put("command", "dumpsys ");
//	    	argv.put("args", dumpsysReport);
//	    	String dumpsysResult = getDriver().executeScript("mobile: shell", argv2).toString();
//	    	utils.log().info(dumpsysResult);
//	    	ExtentReport.getTest().log(Status.INFO, dumpsysResult);	    	
//	    	utils.log().info("****** stopping test: Test Completed Successfully******");

	    	//List<String> lsArgs = Arrays.asList("procstats", "--pid", "16436");

	    }	
	    
	    static boolean isMac() {
			return getPlatform2().startsWith("Mac OS"); 
		}
		
		static boolean isWindows() {
			return getPlatform2().startsWith("Windows");
		}
		
		static boolean isLinux() {
			return getPlatform2().startsWith("Linux");
		}

		private static String getPlatform2() {
			return System.getProperty("os.name");
		}
		
		static boolean isDeviceNameEmulator()
		{
			return new BaseTest().getDeviceName().equalsIgnoreCase("emulator");
		}
		
		private static String getADB() {
			try
			{
				if(isDeviceNameEmulator())
				{
					return "/opt/android-sdk-linux/platform-tools/adb";
				}
				else if (isWindows()) {
					return "." + File.separator + "adb" + File.separator + getPlatform2().split(" ")[0] + File.separator + "adb.exe";
				} else if (isMac() || isLinux()){
					
					return new File("").getAbsolutePath() + File.separator + "adb" + File.separator + getPlatform2().split(" ")[0] + File.separator + "adb";
				} else {
					throw new RuntimeException("not yet implement");
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception in getting ADB Path");
			}
			return "ADB Path Not Found";
		}

}
