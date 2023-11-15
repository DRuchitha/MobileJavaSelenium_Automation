package tests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class BaseClass {
       public  AndroidDriver driver;
       private static AppiumDriverLocalService service;
       static ExtentReports report;

       @BeforeClass
       public static void startTest()
       {
       	  report = new ExtentReports();
       	 ExtentHtmlReporter reports=new ExtentHtmlReporter("reports1.html");
       	 report.attachReporter(reports);
   			ExtentTest test1=report.createTest("BigOven page");
       }
	@BeforeSuite
	public void setup() {
		
		try {
		DesiredCapabilities capabilities=new DesiredCapabilities();
		//capabilities.setCapability("platformName", "ANDROID");
		//capabilities.setCapability(CapabilityType.PLATFORM_NAME,"ANDROID");
		capabilities.setCapability("PLATFORM_NAME","ANDROID");
		capabilities.setCapability("PLATFORM_VERSION", "14");
		capabilities.setCapability("DEVICE_NAME","emulator-5554");
		capabilities.setCapability("APP", "/MobileAppiumProject/src/test/resources/apps/bigoven-recipes-6-1-37.apk");
		capabilities.setCapability("NEW_COMMAND_TIMEOUT",60);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
		capabilities.setCapability("appPackage","com.bigoven.android");
		capabilities.setCapability("appActivity","com.bigoven.android.LauncherActivity");
		//either appPackage or browser should use but  not both at a time
		//capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"chrome");
	
			//URL url=new URL("http://127.0.0.1:4723/");
			driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		//driver=new AndroidDriver(capabilities);
			System.out.println("Application starting");
	}
	
		 catch (Exception e) {
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		}
	    @Test
		public void sample() {
			System.out.println("This is sample project");
		}
			
	
	@AfterSuite
	public void teardown() {
		driver.quit();
		
		
	}
	@AfterClass
    public static void endTest()
    {
    report.flush();
    }

}
