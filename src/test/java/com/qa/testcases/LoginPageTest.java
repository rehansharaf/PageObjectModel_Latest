package com.qa.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	Logger log = Logger.getLogger(LoginPageTest.class);

	
	public LoginPageTest() {
		
		super();
		log.info("Excuted TestBase Constructor");

	}
	
	@BeforeMethod
	public void setUp() {
		
		log.info("==== Start Of Login Page Class ===== ");
		log.info("Driver is initializing up");
		initialization();
		
	}
	
	
	@Test
	public void successfulLoginTest() {
		
		log.info("Started Successful Login Test Execution");
		log.info("Login object is getting created");
		loginPage = new LoginPage();
		log.info("Successful login method is getting called");
		homePage = loginPage.successfulLogin(prop.getProperty("username"), prop.getProperty("password"));
		log.info("End Successful Login Test Execution");

	}
	
	@AfterMethod
	public void tearDown() {
		
		log.info("===== End Successful Login Class Execution ======= ");
		driver.quit();
	}
	

}
