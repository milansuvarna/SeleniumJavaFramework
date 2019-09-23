package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DetailsObj {
	
	WebDriver driver;
	
	By breadCrumbs_xpath=By.xpath("//div[@class='col-12 crum_box']");
	By bookmark_xpath=By.xpath("//div[@class='social_box_desktop']//div[1]");
	By share_xpath=By.xpath("//div[@class='row remove_padding hide_middle_device']//div[2]");
	By rating_text=By.xpath("//a[contains(text(),'Leave a rating')]");
	By like_text=By.xpath("//div[@class='bot_text cursor']");
	By like_icon=By.xpath("//div[@class='left-icon-img tt_center cursor rating']");
	By phone_xpath=By.xpath("//span[@class='phone-no']");
	By rest_info=By.xpath("//div[@class='rest_short_data']");
	By clickable_items=By.xpath(".//a[@class='no-decoration']");
	By tnc_link=By.xpath("//div[@class='red_drum d-none d-sm-block']//div[@class='offer-container']//div//div[@class='offer-terms'][contains(text(),'See Offer Terms & Conditions')]");
	By tnc_popup=By.xpath("//div[@class='modal-container terms_model']");
	By tnc_ok_btn=By.xpath("//button[@type='button']");
	By detailsMenu=By.xpath("//div[@class='respagemenucontainer']//li[2]//a[contains(text(),Menu)]");
	By menuNext=By.xpath("//button[@id='menu-next-button']");
	By menuPrev=By.xpath("//button[@id='menu-prev-button']");
	By detailsTime=By.xpath("//span[@class='time_bold']");
	
	
	public DetailsObj(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String checkData() throws InterruptedException
	{
		String data=driver.findElement(rest_info).getText();
		Thread.sleep(3000);
		return data;
		
		
	}
	
	public void click_Restaurant()
	{
		
		driver.findElement(clickable_items).click();
	}
	
	public void click_tnc() throws InterruptedException
	{
		driver.findElement(tnc_link).click();
		Thread.sleep(2000);
	}
	
	public boolean check_popup()
	{
		boolean tnc=driver.findElement(tnc_popup).isDisplayed();
		return tnc;
		
	}
	
	public void click_tnc_ok_btn() throws InterruptedException
	{
		driver.findElement(tnc_ok_btn).click();
		Thread.sleep(3000);
	}
	
	public String like_gettext() throws InterruptedException
	{
		String likeText=driver.findElement(like_text).getText();
		Thread.sleep(5000);
		return likeText;
	}
	public String likedIt_gettext() throws InterruptedException
	{
		String likedItText=driver.findElement(like_text).getText();
		Thread.sleep(5000);
		return likedItText;
	}
	
	public void click_like_icon() throws InterruptedException
	{
		driver.findElement(like_icon).click();
		//driver.navigate().refresh();
		Thread.sleep(5000);
	}
	
	public int details_timing()
	{
		List<WebElement> time_disp=driver.findElements(detailsTime);
		int time=time_disp.size();
		return time;

	}
	
	/*public String get_time()
	{
		List<WebElement> tElement=driver.findElements(detailsTime);
		String [] Restaurant_time=tElement.
		System.out.println("Time========="+Restaurant_time);
		return Restaurant_time;
	}*/
	
	public void clickMenu() throws InterruptedException
	{
		driver.findElement(detailsMenu).click();
		Thread.sleep(3000);
	}
	
	public boolean MenuNext()
	{
		return driver.findElement(menuNext).isEnabled();
	}
	
	public boolean MenuPrev()
	{
		return driver.findElement(menuPrev).isDisplayed();
	}
	public void clickMenuNext() throws InterruptedException
	{
		driver.findElement(menuNext).click();
		Thread.sleep(3000);
	}
	
	

}
