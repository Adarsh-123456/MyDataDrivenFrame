package CommonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AddUserPage {
	WebDriver driver;
	public AddUserPage(WebDriver adi)
	{
		driver=adi;    // Or this.driver=driver
		
	}
	@FindBy(xpath="//b[contains(text(),'Admin')]")
	WebElement admin;
	@FindBy(xpath="//a[@id='menu_admin_UserManagement']")
	WebElement umang;
	@FindBy(xpath="//a[@id='menu_admin_viewSystemUsers']")
	WebElement users;
	@FindBy(xpath="//input[@id='btnAdd']")
	WebElement uadd;
	@FindBy(xpath="//select[@id='systemUser_userType']")
	WebElement objurole;
	@FindBy(xpath="//input[@id='systemUser_employeeName_empName']")
	WebElement objename;
	@FindBy(xpath="//input[@id='systemUser_userName']")
	WebElement objuname;
	@FindBy(xpath="//input[@id='systemUser_password']")
	WebElement objpword;
	@FindBy(xpath="//input[@id='systemUser_confirmPassword']")
	WebElement objcpword;
	@FindBy(xpath="//input[@id='btnSave']")
	WebElement save;
public String verifyAddUser(String userrole,String empname,String usname,String psword,String cpsword) throws Throwable
{
	String res="";
	Actions ac= new Actions(driver);
	ac.moveToElement(admin).perform();
	Thread.sleep(2000);
	ac.moveToElement(umang).perform();
	Thread.sleep(2000);
	ac.moveToElement(users).click().perform();
	Thread.sleep(2000);
	ac.moveToElement(uadd).click().perform();
	Thread.sleep(2000);
	new Select(objurole).selectByVisibleText(userrole);
	objename.sendKeys(empname);
	Thread.sleep(1000);
	objuname.sendKeys(usname);
	Thread.sleep(1000);
	objpword.sendKeys(psword);
	Thread.sleep(1000);
	objcpword.sendKeys(cpsword);
	save.click();
	String expected="viewSystemUsers";
	String actual=driver.getCurrentUrl();
	if(actual.contains(expected))
	{
		res=("PASS");
	}
	else
	{
		res=("FAIL");
	}
	return res;
	}
	
	}
