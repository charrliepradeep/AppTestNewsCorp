package com.qa.tests;

import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;

import base.BaseTest;
import helper.DeviceConnectHelper;
import io.appium.java_client.screenrecording.CanRecordScreen;
import scheduler.Console;
import scheduler.MonkeyTestDevice;
import scheduler.MonkeyTestDeviceFactory;
import scheduler.MonkeyTestSeries;
import scheduler.MonkeyTestSeriesFactory;
import utils.TestUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Main extends BaseTest{
	 TestUtils utils = new TestUtils();

	    @BeforeClass
	    public void beforeClass() {
	        //closeApp();
	        //launchApp();
	    }

	    @AfterClass
	    public void afterClass() {
	    }

	    @BeforeMethod
	    public void beforeMethod(Method m) {
	        utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
	    }

	    @AfterMethod
	    public void afterMethod() {
	    }

	    
	@Test
	public void adbNYP() {
		//((CanRecordScreen) getADB()).startRecordingScreen();
		String initFileName = "";
		String rawCommand = "monkey --throttle 300 -c android.intent.category.MONKEY -c android.intent.category.LAUNCHER -c android.intent.category.DEFAULT --monitor-native-crashes --kill-process-after-error --pct-touch 43 --pct-motion 30 --pct-majornav 10 --pct-syskeys 15 --pct-appswitch 2 -v -v -v 200";
		AndroidDebugBridge.terminate();
		//lakshmi
		/*
		 * CLIParser cli = new CLIParser(); boolean success = cli.parse(args); if (false
		 * == success) return;
		 */
		
		File adbFile =  new File(getADB());
		if (false == adbFile.exists())
			throw new RuntimeException("ADB not exist!\nCurrent Localtion is " + getADB());
		DeviceConnectHelper.init(getADB());
		try {
			ArrayList<MonkeyTestSeries> serieslist = new ArrayList<MonkeyTestSeries>();
			DeviceConnectHelper deviceHelper = new DeviceConnectHelper();
			//lakshmi
			ArrayList<String> deviceID = new ArrayList<String>();
			//Huewei real device
			//deviceID.add("");
			deviceID.add("emulator-5554");
			//lakshmi --
			//for (String serialNumber : cli.deivcesId) {
			//--------------------------------------
			for (String serialNumber : deviceID) {
			
				IDevice device = deviceHelper.getConnecedDevice(serialNumber);
				if (device == null)
					device = deviceHelper.waitForDeviceConnected(serialNumber, 60000);
				
				if (device == null) {
					Console.printLogMessage(serialNumber, "device " + serialNumber + "cannot be connected");
					continue;
				}

				MonkeyTestDevice monkeyTestDevice = MonkeyTestDeviceFactory
						.newDevice(device);
				//lakshmi
				//monkeyTestDevice.install(cli.pkgPath);				
				//monkeyTestDevice.install("./dist/nypostGoogleNewDesign.apk");
				
				MonkeyTestSeries series = MonkeyTestSeriesFactory.newSeries(
						monkeyTestDevice, 
						//cli.pkgName, 
						"br.com.golmobile.nypost.dev",
						//cli.pkgVersion, 
						"0.1",
						//cli.pkgPath,
						"./nypostGoogleNewDesign.apk",
						rawCommand, 
						//cli.user, 
						"lakshmi",
						initFileName, 
						//(long) (1000 * 3600 * Float.parseFloat(cli.seriesDuration)),
						(long) (1000 * 3600 * 0.1),
						//(long) (1000 * 3600 * Float.parseFloat(cli.singleDuration)));
						(long) (1000 * 3600 * 0.1));
				series.start();
				serieslist.add(series);
			}
			
			// Wait until the monkey series completed!
			for (MonkeyTestSeries series: serieslist)
				series.mExecutor.join();
			
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			AndroidDebugBridge.terminate();
		}

		System.exit(0);
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
	
	private static String getADB() {
		if (isWindows()) {
			return "." + File.separator + "adb" + File.separator + getPlatform2().split(" ")[0] + File.separator + "adb.exe";
		} else if (isMac() || isLinux()){
			return new File("").getAbsolutePath() + File.separator + "adb" + File.separator + getPlatform2().split(" ")[0] + File.separator + "adb";
		} else {
			throw new RuntimeException("not yet implement");
		}
	}
}
