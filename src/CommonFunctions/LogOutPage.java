package CommonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogOutPage {
@FindBy(xpath="//a[@id='welcome']")
WebElement welcome;
@FindBy(xpath="//a[contains(text(),'Logout')]")
WebElement logout;
public void verifyLogOut() throws Throwable
{
	welcome.click();
	Thread.sleep(1000);
	logout.click();
}


}
