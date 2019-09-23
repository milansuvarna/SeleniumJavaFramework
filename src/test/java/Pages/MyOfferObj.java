package Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyOfferObj {

	WebDriver driver;

	By myoffer_menu_tab_xpath=By.xpath("//div[@class='menu_text menu-3']");
	By myOffer_Header_xpath=By.xpath("//h2[@class='bookmarks' and contains(text(),'My Offers')]");
	By myOffer_GetCode_xpath=By.xpath("//body/div[@id='app']/div/div[@class='myprofile-container']/div[@class='container profile-container']/div[@class='row profile-row']/div[@class='col-md-12 col-sm-12 col-lg-9 col-12 nopaddingXXX']/div[@class='l-box my_offers_box profile_white_bg']/div[@class='myOffers row']/div[@class='col-sm-12']/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]");
	By myOffer_Modal_xpath=By.xpath("//div[@class='modal-container']");
	By myOffer_Modal_ok_xpath=By.xpath("//div[@class='bot_text']");
	By myOffer_no_data_xpath=By.xpath("//img[@class='no-records-img']");

	public MyOfferObj(WebDriver driver)
	{
		this.driver=driver;
	}

	public void click_my_offer_menu_tab()
	{
		driver.findElement(myoffer_menu_tab_xpath).click();
	}

	public boolean verify_MyOffer_Header()
	{
		boolean myOffer_header=driver.findElement(myOffer_Header_xpath).isDisplayed();
		return myOffer_header;
	}

	public int verify_MyOffer_GetCode()
	{
		List<WebElement> count=driver.findElements(myOffer_GetCode_xpath);
		int myOffer_GetCode_count=count.size();
		return myOffer_GetCode_count;	
	}

	public void click_GetCode() 
	{
		driver.findElement(myOffer_GetCode_xpath).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public boolean verify_MyOffer_PostGetCode_Modal()
	{
		boolean modal=driver.findElement(myOffer_Modal_xpath).isDisplayed();
		return modal;
	}

	public void click_modal_ok_xpath()
	{
		driver.findElement(myOffer_Modal_ok_xpath).click();
	}

	public boolean no_data()
	{
		boolean no_data=driver.findElement(myOffer_no_data_xpath).isDisplayed();
		return no_data;
	}


}
