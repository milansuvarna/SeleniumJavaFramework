package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchObj {
	
	WebDriver driver;
	
	By search_box_xpath=By.xpath(".//div[@class='text_box small_device_search']//input[@type='text']");
	By search_cuisine_xpath=By.xpath("//input[@id='cuisine']");
	By search_autosuggestion_xpath=By.xpath("//li[@id='react-autowhatever-1--item-0']");
	By search_bengaluru_xpath=By.xpath("//span[contains(text(),'bengaluru')]");
	By search_delhi_xpath=By.xpath("//span[contains(text(),'delhi NCR')]");
	By search_mumbai_xpath=By.xpath("//span[contains(text(),'mumbai')]");
	By search_kolkata_xpath=By.xpath("//span[contains(text(),'kolkata')]");
	By search_chennai_xpath=By.xpath("//span[contains(text(),'chennai')]");
	By search_hyderabad_xpath=By.xpath("//span[contains(text(),'hyderabad')]");
	By search_pune_xpath=By.xpath("//span[contains(text(),'pune')]");
	By suggested_search_nearby=By.xpath("//a[contains(text(),'Offers Nearby')]");
	By suggested_search_cafe=By.xpath("//a[contains(text(),'Cafes')]");
	
	
	public SearchObj(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void click_search_box()
	{
		driver.findElement(search_box_xpath).click();
	}
	
	public void search_cuisine() throws InterruptedException
	{
		driver.findElement(search_cuisine_xpath).sendKeys("mexican");
		Thread.sleep(3000);
	}
	
	public void click_auto_suggestion()
	{
		driver.findElement(search_autosuggestion_xpath).click();
	}
	
	public boolean bengaluru_tab()
	{
		return driver.findElement(search_bengaluru_xpath).isDisplayed();
	}
	
	public boolean delhi_tab()
	{
		return driver.findElement(search_delhi_xpath).isDisplayed();
	}
	public boolean mumbai_tab()
	{
		return driver.findElement(search_mumbai_xpath).isDisplayed();
	}
	public boolean kolkata_tab()
	{
		return driver.findElement(search_kolkata_xpath).isDisplayed();
	}
	public boolean chennai_tab()
	{
		return driver.findElement(search_chennai_xpath).isDisplayed();
	}
	public boolean hyderabad_tab()
	{
		return driver.findElement(search_hyderabad_xpath).isDisplayed();
	}
	public boolean pune_tab()
	{
		return driver.findElement(search_pune_xpath).isDisplayed();
	}
	public boolean search_nearby()
	{
		return driver.findElement(suggested_search_nearby).isDisplayed();
	}
	public boolean search_cafe()
	{
		return driver.findElement(suggested_search_cafe).isDisplayed();
	}
}


