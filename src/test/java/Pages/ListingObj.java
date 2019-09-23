package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListingObj {
	WebDriver driver;
	By countList=By.xpath(".//div[@class='list_restaurant']");
	By footer=By.xpath("//div[@class='lft_foot']");
	
	public ListingObj(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public WebElement footer()
	{
		WebElement Element = driver.findElement(footer);
		return Element;
		
	}
	public int get_restaurant_count()
	{
		List<WebElement>list_restaurant=driver.findElements(countList);
		int count=list_restaurant.size();
		return count;
	}

}
