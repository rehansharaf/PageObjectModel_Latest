package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.qa.listeners.WebDriverListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	public static long IMPLICIT_WAIT;
	public static long EXPLICIT_WAIT;
	public static long PAGE_LOAD_TIMEOUT;
	public static WebDriverWait wait;
	public static EventFiringWebDriver e_driver;
	public static WebDriverListener eventListener;
	
	@BeforeSuite
	public void begin() {

		extent = new ExtentReports(System.getProperty("user.dir")+"/Reports/ExtentReport.html",true);
		extent.addSystemInfo("Host Name", "Rehan PC");
		extent.addSystemInfo("User Name", "Rehan");
		extent.addSystemInfo("Environment", "QA Automation");
		
	}

	public TestBase() {

		prop = new Properties();
		FileInputStream fi;
		try {
			fi = new FileInputStream(System.getProperty("user.dir")
					+ "/src/main/java/com/qa/config/config.properties");
			prop.load(fi);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initialization() {
		
		String browser = prop.getProperty("BROWSER");
		IMPLICIT_WAIT = Long.parseLong(prop.getProperty("IMPLICIT_WAIT"));
		EXPLICIT_WAIT = Long.parseLong(prop.getProperty("EXPLICIT_WAIT"));
		PAGE_LOAD_TIMEOUT = Long.parseLong(prop.getProperty("PAGE_LOAD_TIMEOUT"));
		
		if(browser.equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			
		}else if(browser.equals("FF")) {
			
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/Resources/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			
		}
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebDriverListener();
		e_driver.register(eventListener);
		driver = e_driver;	
		
		wait = new WebDriverWait(driver, EXPLICIT_WAIT);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(prop.getProperty("URL"));
		
	}
	
	
	@AfterSuite
	public void setExtent() {
		
		extent.flush();
		extent.close();
		
	}

}
