package DriverFactory;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunctions.AddEmpPage;
import CommonFunctions.LogOutPage;
import CommonFunctions.LoginPage;
import Utilities.ExcelFileUtil;

public class POMEmpDriverScript {
	Workbook wb;
	WebDriver driver;
	String inputpath="D:\\Selenium930Batch\\DDT_Framework\\TestInput\\AddEmployeeData.xlsx";
	String outputpath="D:\\Selenium930Batch\\DDT_Framework\\TestOutput\\POMEmpResults.xlsx";
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
	public void validateaddEmp() throws Throwable
	{
		boolean res=false;
		ExcelFileUtil xl= new ExcelFileUtil(inputpath);

			int rc=xl.rowCount("AddEmpData");
			int cc=xl.cellCount("AddEmpData");
			Reporter.log("No of rows are:"+rc+"       "+" no of cells in 1st row :"+cc,true );
			for(int i=1;i<=rc;i++)
			{
				String firstName=xl.getCellData("AddEmpData", i, 0);
				String middleName=xl.getCellData("AddEmpData", i, 1);
				String lastName=xl.getCellData("AddEmpData", i, 2);
				AddEmpPage employee=PageFactory.initElements(driver, AddEmpPage.class);
				String Results=employee.verifyAddEmp(firstName, middleName, lastName);
				xl.setCellData("AddEmpData", i, 3, Results, outputpath);
				
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
