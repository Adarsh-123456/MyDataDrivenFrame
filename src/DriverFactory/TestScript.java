package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import CommonFunctions.AddEmpPage;
import CommonFunctions.AddUserPage;
import CommonFunctions.LogOutPage;
import CommonFunctions.LoginPage;

public class TestScript {
	WebDriver driver;
	@BeforeMethod
	public void validateadminLogin() throws Throwable
	{
		System.setProperty("webdriver.chrome.driver","./CommonDrivers/chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://orangehrm.qedgetech.com/");
		LoginPage login=PageFactory.initElements(driver, LoginPage.class);
		login.verifyLogin("Admin", "Qedge123!@#");
		
		
	}
	@Test(priority=1)
	public void validateadduser() throws Throwable
	{
		AddUserPage auser=PageFactory.initElements(driver, AddUserPage.class);
		String results=auser.verifyAddUser("Admin", "ANIKET JAIN", "Adarsh1234", "Selenium@12adi", "Selenium@12adi");
		Reporter.log(results,true);
	}
	@Test(priority=0)
	public void validateaddemp() throws Throwable
	{
		AddEmpPage aemp=PageFactory.initElements(driver, AddEmpPage.class);
		String results1=aemp.verifyAddEmp("Milan", "Kumar", "Panda");
		Reporter.log(results1,true);
	}
	@AfterMethod
	public void teardown() throws Throwable
	{
		LogOutPage lout=PageFactory.initElements(driver, LogOutPage.class);
		lout.verifyLogOut();
		driver.close();
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

