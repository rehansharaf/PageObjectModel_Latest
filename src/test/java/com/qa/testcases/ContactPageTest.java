package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.ContactPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.NewContactPage;
import com.qa.util.TestUtil;

public class ContactPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	ContactPage contactPage;
	NewContactPage newContactPage;
	
	
	public ContactPageTest() {
		
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		
		initialization();
		loginPage = new LoginPage();
		homePage = loginPage.successfulLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactPage = homePage.openContactPage();
	}
	
	
	@DataProvider
	public Object[][] getNewContactData() {
		
		return TestUtil.getTestData("CreateContact");
	}
	
	@Test(priority=1)
	public void verifyContactPageHeadingTest() {
		
		String contactPageHeading =  contactPage.verifyContactPageHeading();
		Assert.assertEquals(contactPageHeading, "Contacts");
		
	}
	
	@Test(priority=2)
	public void verifyNewContactPageTest() {
		
		newContactPage = contactPage.verifyNewContactPage();
		
	}
	
	@Test(priority=3, dataProvider="getNewContactData")
	public void createNewContactTest(String firstName, String lastName, String birthDay, String birthMonth, String birthYear) {
		
		newContactPage = contactPage.verifyNewContactPage();
		Assert.assertTrue(newContactPage.createNewcontact(firstName, lastName, birthDay, birthMonth, birthYear));
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}

}
