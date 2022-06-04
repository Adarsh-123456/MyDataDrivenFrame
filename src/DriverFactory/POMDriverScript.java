package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunctions.AddUserPage;
import CommonFunctions.LogOutPage;
import CommonFunctions.LoginPage;
import Utilities.ExcelFileUtil;

public class POMDriverScript {
WebDriver driver;
String inputpath="D:\\Selenium930Batch\\DDT_Framework\\TestInput\\AddUserData.xlsx";
String outputpath="D:\\Selenium930Batch\\DDT_Framework\\TestOutput\\PomResults.xlsx";
@BeforeTest
public void adminLogin() throws Throwable
{
	System.setProperty("webdriver.chrome.driver","./CommonDrivers/chromedriver.exe");
	driver= new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://orangehrm.qedgetech.com/");
	LoginPage login=PageFactory.initElements(driver, LoginPage.class);
	login.verifyLogin("Admin", "Qedge123!@#");
}
@Test
public void validateUser() throws Throwable
{
boolean res=false;
ExcelFileUtil xl= new ExcelFileUtil(inputpath);

	int rc=xl.rowCount("AddUser");
	int cc=xl.cellCount("AddUser");
	Reporter.log("No of rows are:"+rc+"       "+" no of cells in 1st row :"+cc,true );
	for(int i=1;i<=rc;i++)
	{
		String userRole=xl.getCellData("AddUser", i, 0);
		String Ename=xl.getCellData("AddUser", i, 1);
		String userName=xl.getCellData("AddUser", i, 2);
		String Password=xl.getCellData("AddUser", i, 3);
		String CPassword=xl.getCellData("AddUser", i,4);
		AddUserPage user=PageFactory.initElements(driver, AddUserPage.class);
		String Results=user.verifyAddUser(userRole, Ename, userName, Password, CPassword);
		xl.setCellData("AddUser", i, 5, Results, outputpath);
				
	}
}
@AfterTest 
public void teardown() throws Throwable
{
	LogOutPage logout=PageFactory.initElements(driver, LogOutPage.class);
	logout.verifyLogOut();
	driver.close();
}
}
