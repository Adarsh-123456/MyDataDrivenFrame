package CommonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
@FindBy(xpath="//input[@id='txtUsername']")
WebElement uname;
@FindBy(xpath="//input[@id='txtPassword']")
WebElement pword;
@FindBy(xpath="//input[@id='btnLogin']")
WebElement lbtn;
public void verifyLogin(String username,String password) throws Throwable
{
	uname.sendKeys(username);
	pword.sendKeys(password);
	lbtn.click();
	Thread.sleep(2000);
}
}
