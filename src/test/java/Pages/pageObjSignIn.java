package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class pageObjSignIn {

	static WebElement element=null;

	public static WebElement findSignIn(WebDriver driver)
	{
		element=driver.findElement(By.xpath("//h2[contains(text(),'Sign in')]"));
		return element;
	}

	public static WebElement findGoogle(WebDriver driver)
	{
		element=driver.findElement(By.xpath("//div[@class='google_login']"));
		return element;
	}

	public static WebElement email(WebDriver driver)
	{
		element=driver.findElement(By.xpath("//input[@name='identifier']"));
		return element;
	}

	public static WebElement userNext(WebDriver driver)
	{
		element=driver.findElement(By.xpath("//div[@id='identifierNext']//content[@class='CwaK9']"));
		return element;		
	}

	public static WebElement pass(WebDriver driver)
	{
		element=driver.findElement(By.xpath("//input[@name='password']"));
		return element;	
	}

	public static WebElement passNext(WebDriver driver)
	{
		element=driver.findElement(By.xpath("//span[contains(text(),'Next')]"));
		return element;		
	}


}
