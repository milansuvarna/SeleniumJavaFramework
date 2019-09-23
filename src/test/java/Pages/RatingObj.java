package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RatingObj {

	WebDriver driver;

	By rating_tab=By.xpath("//div[@class='menu_text menu-6']");
	By rating_header_xpath=By.xpath("//h2[@class='bookmarks']");
	By rating_img_xpath=By.xpath("//img[@class='fixed_resto_img']");
	By rating_price_xpath=By.xpath("//span[@class='price-fr']");
	By rating_restaurant_name_xpath=By.xpath("//h4[@class='media-heading']");
	By rating_no_data_xpath=By.xpath("//img[@class='no-records-img']");
	By rating_rating_xpath=By.xpath("//span[@class='p_cir']");



	public RatingObj(WebDriver driver)
	{
		this.driver=driver;
	}

	public void click_rating_tab() throws InterruptedException
	{
		driver.findElement(rating_tab).click();
		Thread.sleep(3000);
	}

	public boolean verify_rating_header()
	{
		boolean rating=driver.findElement(rating_header_xpath).isDisplayed();
		return rating;
	}

	public int rating_img()
	{

		List<WebElement> count=driver.findElements(rating_img_xpath);

		int rating_count=count.size();
		return rating_count;
	}

	public boolean no_data()
	{
		boolean no_data=driver.findElement(rating_no_data_xpath).isDisplayed();
		return no_data;
	}

	public void click_rating_img()
	{
		driver.findElement(rating_img_xpath).click();
	}

	public String get_title()
	{
		return driver.getTitle();
	}
}
