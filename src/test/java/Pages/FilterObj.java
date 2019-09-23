package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FilterObj {
	
	WebDriver driver;
	
	By filter_burrpMore=By.xpath("//label[contains(text(),'Burrp More')]");
	
	public FilterObj(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void click_filter()
	{
		driver.findElement(filter_burrpMore).click();
	}
	
	public String getCurrenturl()
	{
		return driver.getCurrentUrl();
	}

}
