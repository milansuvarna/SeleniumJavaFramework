package Pages;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInPageObj {

	WebDriver driver=null;
	
	By sign_in=By.xpath("//div[@class='prof_data']//h2[contains(text(),'Sign in')]");
	By google_button=By.xpath("//div[@class='google_login']");	
	By gmail_username=By.xpath("//input[@name='identifier']");
	By email_next=By.xpath("//span[contains(text(),'Next')]");
	By gmail_password=By.xpath("//input[@name='password']");
	By pass_next=By.xpath("//span[contains(text(),'Next')]");
	By hamburger_menu=By.xpath("//span[@class='menu-img-header']");
	By close_invite_popup_xpath=By.xpath("//div[@class='CT_Interstitial']");
	By email_error=By.xpath("//div[@class='GQ8Pzc']");//Couldn't find your Google Account
	By pass_error=By.xpath("//content[contains(text(),'Wrong password. Try again or click Forgot password')]");

	public SignInPageObj(WebDriver driver)
	{
		this.driver=driver;
	}

	public void close_Invite_Popup()
	{
		driver.findElement(close_invite_popup_xpath).click();
	}

	public void clickSignIn() throws InterruptedException
	{
		driver.findElement(sign_in).click();
		Thread.sleep(3000);
		System.out.println("Inside click signin");
	}

	public void clickGoogle() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.findElement(google_button).click();
		Thread.sleep(2000);
		System.out.println(driver.getTitle());//Print Main Title
		Set<String> windowIds= driver.getWindowHandles();
		System.out.println("Window Ids"+windowIds);//Print Window Ids
		Iterator<String> iter=windowIds.iterator();
		String mainWindowID=iter.next();
		String childWindowID=iter.next();
		driver.switchTo().window(childWindowID);
		Thread.sleep(5000);
		String s1=driver.getTitle();
		System.out.println(s1);//Print Child Title

		if(s1==null)
		{
			driver.switchTo().window(mainWindowID);
			driver.findElement(google_button).click();
			driver.switchTo().window(childWindowID);
		}	
	}

	public void enterUserName(String uname) throws InterruptedException
	{
		Thread.sleep(3000);
		driver.findElement(gmail_username).sendKeys(uname);

	}
	
	public int userNameError() throws InterruptedException
	{
		List<WebElement> label=driver.findElements(email_error);
		int nolabel=label.size();
		return nolabel;
	}

	public void clickUserNameNext() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.findElement(email_next).click();

	}

	public void enterPassword(String pass) throws InterruptedException
	{
		Thread.sleep(3000);
		driver.findElement(gmail_password).sendKeys(pass);

	}
	
	public int passError() throws InterruptedException
	{
		List<WebElement> label=driver.findElements(pass_error);
		int nolabel_pass=label.size();
		return nolabel_pass;
		
	}

	public void clickPassNext() throws InterruptedException
	{
		driver.findElement(pass_next).click();
		Thread.sleep(2000);
		int pass_incorrect=passError();
		if(pass_incorrect==0)
		{
		Set<String> windowIds= driver.getWindowHandles();
		Iterator<String> iter=windowIds.iterator();
		String mainWindowID=iter.next();
		driver.switchTo().window(mainWindowID);
		Thread.sleep(3000);
		}
	}
	public void click_HamburgerMenu() throws InterruptedException
	{

		Thread.sleep(5000);
		driver.findElement(hamburger_menu).click();
		Thread.sleep(3000);

	}
	public void closeDriver()
	{
		//driver.close();
		driver.quit();
	}

}


