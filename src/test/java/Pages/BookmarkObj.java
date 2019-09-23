package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BookmarkObj {

	WebDriver driver;

	By bookmark_tab=By.xpath("//div[@class='menu_text menu-4']");
	By bookmark_header_xpath=By.xpath("//h2[@class='bookmarks']");
	By bookmark_img_xpath=By.xpath("//div[contains(@class,'col-sm-12')]//div[2]//div[1]//div[1]//img[1]");
	By bookmark_price_xpath=By.xpath("//div[contains(@class,'col-sm-12')]//div[2]//div[1]//div[2]//p[1]//span[3]");
	By bookmark_restaurant_name_xpath=By.xpath("//h4[contains(text(),\"Chili's American Grill & Bar\")]");
	By bookmark_no_data_xpath=By.xpath("//img[@class='no-records-img']");



	public BookmarkObj(WebDriver driver)
	{
		this.driver=driver;
	}

	public void click_bookmark_tab() throws InterruptedException
	{
		driver.findElement(bookmark_tab).click();
		Thread.sleep(5000);
	}

	public boolean verify_bookmark_header()
	{
		boolean bookmark=driver.findElement(bookmark_header_xpath).isDisplayed();
		return bookmark;
	}

	public int bookmark_img()
	{
		List<WebElement> label=driver.findElements(bookmark_img_xpath);
		int nolabel=label.size();
		return nolabel;

	}

	public boolean no_data()
	{
		boolean no_data=driver.findElement(bookmark_no_data_xpath).isDisplayed();
		return no_data;
	}

	public void click_bookmark_img()
	{
		driver.findElement(bookmark_img_xpath).click();
	}

	public String get_title()
	{
		return driver.getTitle();
	}



}
