package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import test.SignInTest;

public class InviteReferObj {

	WebDriver driver;


	By SendInviteLink=By.xpath("//div[@class='menu_text menu-2']");
	By hamburger_menu=By.xpath("//span[@class='menu-img-header']");
	By invite_banner=By.xpath("//div[@class='invite-bg-green']");
	By SendInvitebtn=By.xpath("//button[contains(text(),'Send Invites')]");
	By invite_Box=By.xpath("//div[@class='invite_box']");

	public InviteReferObj(WebDriver driver)
	{
		this.driver=driver;
	}

	public void click_HamburgerMenu() throws InterruptedException
	{

		Thread.sleep(3000);
		driver.findElement(hamburger_menu).click();

	}

	public void refresh_Invite_page()
	{
		driver.navigate().refresh();
	}

	public void click_SendInvite_Menu()
	{
		driver.findElement(SendInviteLink).click();
	}

	public String getInviteTitle()
	{

		return driver.getTitle();
	}

	public boolean verify_Invite_banner()
	{
		boolean invite_ban=driver.findElement(invite_banner).isDisplayed();
		return invite_ban;
	}

	public boolean verify_Send_Invite()
	{
		boolean send_value=driver.findElement(SendInvitebtn).isDisplayed();
		return send_value;
	}

	public void click_Send_Invite()
	{
		driver.findElement(SendInvitebtn).click();
	}

	public boolean verify_Invite_Box()
	{
		boolean invite_box=driver.findElement(invite_Box).isDisplayed();
		return invite_box;
	}

}
