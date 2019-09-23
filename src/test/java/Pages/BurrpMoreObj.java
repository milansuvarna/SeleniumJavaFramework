package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BurrpMoreObj {

	WebDriver driver;
	By hamburger_menu=By.xpath("//span[@class='menu-img-header']");
	By burrpMore_Image=By.xpath("//div[@class='more-image']//img");
	
	public BurrpMoreObj(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public boolean verifyBurrpMoreImage()
	{
		boolean img=driver.findElement(burrpMore_Image).isDisplayed();
		return img;
	}
	
	
	
}
