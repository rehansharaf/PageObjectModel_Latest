package com.qa.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.ContactPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class HomePageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	ContactPage contactPage;
	Logger log = Logger.getLogger(HomePageTest.class);

	
	
	public HomePageTest() {
		
		super();
		log.info("Excuted TestBase Constructor");
	}
	
	@BeforeMethod
	public void setUp() {
		
		log.info("==== Start Of Home Page Class ===== ");
		log.info("Driver is initializing up From HomePageTest");
		initialization();
		
		log.info("Creating LoginPage from HomePageTest");
		loginPage = new LoginPage();
		log.info("Executing Successful Login Method from HomePageTest");
		homePage = loginPage.successfulLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	
	@Test(priority=1)
	public void verifyLoggedInUser() {
		
		log.info("Executing Verify Logged In User Test from HomePageTest");
		String loggedInUsername = homePage.verifyLoggedInName();
		Assert.assertEquals(loggedInUsername, "Rehan Sharaf");
		log.info("End of Verify Logged In User Test from HomePageTest");

		
	}
	
	@Test(priority=2)
	public void verifyContactLink() {
		
		log.info("Executing Verify Contact Link Test from HomePageTest");
		contactPage = homePage.openContactPage();
		log.info("End of Verify Contact Link Test from HomePageTest");

	}
	
	
	@AfterMethod
	public void tearDown() {
		
		log.info("==== End Of Home Page Class ===== ");
		driver.quit();
		
	}

}
