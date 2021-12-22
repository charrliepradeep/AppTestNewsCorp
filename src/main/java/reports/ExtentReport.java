package reports;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.*;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReport {
    static ExtentReports extent;
    final static String filePath = "Extent.html";
    static Map<Integer, ExtentTest> extentTestMap = new HashMap();
    //-----------------------------------
    private static String reportFileName = "Reel_Application_Monkey_Testing"+".html";
    private static String fileSeperator = System.getProperty("file.separator");
    private static String reportFilepath = System.getProperty("user.dir") +fileSeperator+ "TestReport";
    private static String reportFileLocation =  reportFilepath +fileSeperator+ reportFileName;
    //----------------------------------------------------------------------
    
    
    public synchronized static ExtentReports getReporter() {
    	System.out.println("Directory: " + reportFilepath );
    	String fileName = getReportPath(reportFilepath);
        if (extent == null) {
        	//ExtentSparkReporter html = new ExtentSparkReporter("Extent.html");
        	ExtentSparkReporter html = new ExtentSparkReporter(fileName);
        	html.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        	html.config().setDocumentTitle("reportFileName");
        	html.config().setReportName(reportFileName);
        	html.config().setTheme(Theme.DARK);
        	html.config().setEncoding("utf-8");
            extent = new ExtentReports();
            extent.attachReporter(html);
            extent.setSystemInfo("OS", "Windows");
    		extent.setSystemInfo("AUT", "QA");
    		return extent;
        }
        
        return extent;
    }
    
    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = getReporter().createTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }
    
    private static String getReportPath (String path) {
    	File testDirectory = new File(path);
        if (!testDirectory.exists()) {
        	if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!" );
                return reportFileLocation;
            } else {
                System.out.println("Failed to create directory: " + path);
                return System.getProperty("user.dir");
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
		return reportFileLocation;
    }
}
