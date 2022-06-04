package CommonFunctions;

import org.openqa.selenium.By;

import Constant.AppUtil;

public class FunctionLibrary extends AppUtil{
	public static void verifyLogin(String username,String password)
	{
		driver.findElement(By.cssSelector(adi.getProperty("objuser"))).sendKeys(username);
		driver.findElement(By.cssSelector(adi.getProperty("objpass"))).sendKeys(password);
		driver.findElement(By.cssSelector(adi.getProperty("objlogbtn"))).click();
	}

}


