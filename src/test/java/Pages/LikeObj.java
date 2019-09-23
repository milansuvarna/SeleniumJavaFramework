package Pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import test.SetupDriver;

public class LikeObj{
	WebDriver driver;

	By like_tab=By.xpath("//div[@class='menu_text menu-8']");
	By like_header_xpath=By.xpath("//h2[@class='bookmarks']");
	By like_img_xpath=By.xpath("//img[@class='fixed_resto_img']");
	By like_price_xpath=By.xpath("//span[@class='price-fr']");
	By like_restaurant_name_xpath=By.xpath("//h4[@class='media-heading']");
	By like_no_data_xpath=By.xpath("//img[@class='no-records-img']");
	By like_rating_xpath=By.xpath("//span[@class='p_cir']");

	public LikeObj(WebDriver driver) 
	{
		this.driver=driver;
	}

	public void click_like_tab() throws InterruptedException
	{
		driver.findElement(like_tab).click();
		Thread.sleep(3000);
	}

	public boolean verify_like_header()
	{
		boolean like=driver.findElement(like_header_xpath).isDisplayed();
		return like;
	}


	public int like_img()
	{
		List<WebElement> label=driver.findElements(like_img_xpath);
		int nolabel=label.size();
		return nolabel;
	}

	public boolean no_data()
	{
		boolean no_data=driver.findElement(like_no_data_xpath).isDisplayed();
		return no_data;
	}

	public void click_like_img()
	{
		driver.findElement(like_img_xpath).click();
	}

	public String get_title()
	{
		return driver.getTitle();
	}

}

