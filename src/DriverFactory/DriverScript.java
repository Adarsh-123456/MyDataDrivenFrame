package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.parser.NetwareFTPEntryParser;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunctions.FunctionLibrary;
import Constant.AppUtil;
import Utilities.ExcelFileUtil;

public class DriverScript extends AppUtil{
	ExtentReports report;
	ExtentTest test;
	String inputpath="D:\\Selenium930Batch\\DDT_Framework\\TestInput\\LoginData.xlsx";
	String outputpath="D:\\Selenium930Batch\\DDT_Framework\\TestOutput\\DDTResults.xlsx";
	@Test
	public void validateLogin() throws Throwable
	{
		//path for html report
		report= new ExtentReports("./ExtentReport/DDT.html");    // generate under project after execution
		// create reference object to call XL methods
		ExcelFileUtil xl= new ExcelFileUtil(inputpath);
		//count no of rows in login sheet
		int rc= xl.rowCount("Login");
		//count no of cells in 1st row
		int cc= xl.cellCount("Login");
		Reporter.log("No of rows are::"+rc+"   "+"No of cells are::"+cc,true);
		for(int i=1;i<=rc;i++)
		{
			test=report.startTest("login test");
			driver.get(adi.getProperty("Url"));
			driver.manage().window().maximize();
			//read username and password cell data
			String username=xl.getCellData("Login", i, 0);
			String password=xl.getCellData("Login", i, 1);
			//call login method
			FunctionLibrary.verifyLogin(username, password);
			String expected="dashboard";
			String actual=driver.getCurrentUrl();
			if(actual.contains(expected))
			{
				//write as login success in cell
				xl.setCellData("Login", i, 2, "login success", outputpath);
				//write as pass in cell
				xl.setCellData("Login", i, 3, "pass", outputpath);
				test.log(LogStatus.PASS, "Login success:::"+expected+"          "+actual);
				Reporter.log("Login success:::"+expected+"          "+actual,true);
			}
			else
			{
				//take screenshot
				File screen= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screen, new File("./Screenshot/Adarsh/"+i+"Loginpage.png"));
				
				String errormessage=driver.findElement(By.cssSelector(adi.getProperty("objemsg"))).getText();
				xl.setCellData("Login", i, 2, errormessage, outputpath);
				xl.setCellData("Login", i, 3, "Fail", outputpath);
				test.log(LogStatus.FAIL, errormessage+"    "+expected+"          "+actual);
				Reporter.log("Login fail:::"+expected+"          "+actual,true);
			}
			report.endTest(test);
			report.flush();
		}
		
		
		
		
	}
			

}
