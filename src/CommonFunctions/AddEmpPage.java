package CommonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AddEmpPage {
	WebDriver driver;
	public AddEmpPage(WebDriver driver)
	{
		this.driver=driver;
		
	}
	@FindBy(xpath="//b[contains(text(),'PIM')]")
	WebElement pim;
	@FindBy(xpath="//input[@id='btnAdd']")
	WebElement obadd;
	@FindBy(xpath="//input[@id='firstName']")
	WebElement obfname;
	@FindBy(xpath="//input[@id='middleName']")
	WebElement obmname;
	@FindBy(xpath="//input[@id='lastName']")
	WebElement oblname;
	@FindBy(xpath="//input[@id='btnSave']")
	WebElement savebtn;
	public String verifyAddEmp(String fname,String mname,String lname) throws Throwable

	{
		String res="";
		Actions ac= new Actions(driver);
		ac.moveToElement(pim).click().perform();
		Thread.sleep(1000);
		ac.moveToElement(obadd).click().perform();
		Thread.sleep(1000);
		obfname.sendKeys(fname);
		obmname.sendKeys(mname);
		oblname.sendKeys(lname);
		savebtn.click();
		Thread.sleep(1000);
		String expected="empNumber";
		String actual=driver.getCurrentUrl();
		if(actual.contains(expected))
		{
			res="DONE";
		}
		else
		{
			res="NOT DONE";
		}
		return res;
	}
}
		
		
		
		
		
		
		
		
		
		
		

