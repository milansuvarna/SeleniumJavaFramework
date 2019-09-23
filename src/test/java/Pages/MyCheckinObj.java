package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyCheckinObj {
	WebDriver driver;

	By mycheckin_tab=By.xpath("//div[@class='menu_text menu-7']");
	By mycheckin_header_xpath=By.xpath("//h2[@class='bookmarks']");
	By mycheckin_img_xpath=By.xpath("//img[@class='fixed_resto_img']");
	By mycheckin_price_xpath=By.xpath("//span[@class='price-fr']");
	By mycheckin_restaurant_name_xpath=By.xpath("//h4[@class='media-heading']");
	By mycheckin_no_data_xpath=By.xpath("//img[@class='no-records-img']");
	By mycheckin_rating_xpath=By.xpath("//span[@class='p_cir']");
	By hamburger_menu=By.xpath("//span[@class='menu-img-header']");



	public MyCheckinObj(WebDriver driver)
	{
		this.driver=driver;
	}

	public void click_HamburgerMenu() throws InterruptedException
	{

		
		driver.findElement(hamburger_menu).click();
		Thread.sleep(3000);

	}
	public void click_mycheckin_tab() throws InterruptedException
	{
		driver.findElement(mycheckin_tab).click();
		Thread.sleep(3000);
	}

	public boolean verify_mycheckin_header()
	{
		boolean mycheckin=driver.findElement(mycheckin_header_xpath).isDisplayed();
		return mycheckin;
	}

	public int mycheckin_img()
	{
		List<WebElement> count=driver.findElements(mycheckin_img_xpath);
		int checkin_count=count.size();
		return checkin_count;
	}

	public boolean no_data()
	{
		boolean no_data=driver.findElement(mycheckin_no_data_xpath).isDisplayed();
		return no_data;
	}

	public void click_mycheckin_img()
	{
		driver.findElement(mycheckin_img_xpath).click();
	}

	public String get_title()
	{
		return driver.getTitle();
	}




}


