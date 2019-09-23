package test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Pages.BlogObj;
import Pages.BookmarkObj;
import Pages.GetURL;
import Pages.InviteReferObj;
import Pages.LikeObj;
import Pages.SignInPageObj;

public class InviteReferTest extends SetupDriver {
	
	//public static WebDriver driver;
	public static SignInPageObj sign;
	public static GetURL get;

	public static InviteReferObj invite;

	public static Logger log;
	public ExtentReports extent;//extent report
	public ExtentTest test;//report 
	public ExtentHtmlReporter htmlReporter;  
	String title="Burrp!";
	SoftAssert softAssertion= new SoftAssert();
	JavascriptExecutor js;
	
	public static String userName=null;
	public static String password=null;

	@BeforeMethod
	public void setenv() throws IOException
	{

		driver=SetupDriver.getDriver(); //Get Driver from SetupDriver Class
		sign=new SignInPageObj(driver);
		get=new GetURL(driver);
		invite=new InviteReferObj(driver);

		log=LogManager.getLogger(InviteReferTest.class); 	//Create Object for Logger Class
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//ExtentReportResults.html");// creates a toggle for the given test, adds all log events under it
		htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();		//Create Object for ExtentReports Class   
		extent.attachReporter(htmlReporter);// create ExtentReports and attach reporter(s) 
		test = extent.createTest("Invite and Refer Page", "Sample description");
		js = (JavascriptExecutor) driver;

	}


	public InviteReferTest() throws InterruptedException, IOException
	{
		//Constructor to handle Exception from Super class(SetupDriver)

	}


	@Test
	public void verifyInviteReferPage() throws InterruptedException, IOException
	{
		test.log(Status.INFO, "starting test case");
		get.getUrl();			
		test.info("Get URL");
		log.info("Get URL");

		sign.clickSignIn();				
		test.info("click Sign In");
		Thread.sleep(3000);

		sign.clickGoogle();
		test.info("click on Google");
		log.info("Clicked Google Sign In ");

		sign.enterUserName(userName);
		test.info("Enter User name");
		log.info("Enter User name ");
		
		sign.clickUserNameNext();
		test.info("Click Next in User Name");
		log.info("Click Next in User Name ");
		
		int email_error=sign.userNameError();
		if(email_error!=0)
		{
			test.fail("Incorrect Email ID");
		}
		else
		{
			test.pass("Valid Email Id");
		}
		Assert.assertEquals(0, email_error);


		sign.enterPassword(password);
		test.info("Enter Pasword");
		log.info("Enter Password");
		
		
		sign.clickPassNext();
		test.info("clicked on Password Next");
		log.info("clicked on Password Next");
		
		int password_error=sign.passError();
		if(password_error!=0)
		{
			test.fail("Incorrect Password");
		}
		else
		{
			test.pass("Valid Password");
		}
		Assert.assertEquals(0, password_error);

		invite.click_HamburgerMenu();
		test.info("Click Hamburger Menu");
		log.info("Click Hamburger Menu");


		Thread.sleep(5000);
		//Verify Invite and Refer Page
		invite.click_SendInvite_Menu();
		test.info("Click Invite and Refer tab");
		log.info("Click Invite and Refer tab");

		invite.refresh_Invite_page();
		test.info("Refresh Invite and Refer Page");
		log.info("Refresh Invite and Refer Page");

		String InviteTitle=invite.getInviteTitle();
		softAssertion.assertEquals("Burrp!", title);

		if(title.equalsIgnoreCase("Burrp!"))
		{
			test.pass("Invite and Refer Page Title assertion passed"+title);
			log.info("Invite and Refer Page Title assertion passed"+title);
		}
		else
		{
			test.fail("Title Assertion Failed" +title);
			log.error("Title Assertion Failed"+title);

		}

		boolean InviteBanner=invite.verify_Invite_banner();
		softAssertion.assertEquals(true, InviteBanner);
		if(InviteBanner==true)
		{
			test.pass("Assertion passed, Invite Baner is visible");
			log.info("Assertion passed, Invite Banner is visible");
		}
		else
		{
			test.fail("Assertion failed, Invite Baner is not visible");
			log.error("Assert failed, Invite Banner is not visible");
		}
		js.executeScript("window.scrollBy(0,1000)");//Scroll to bottom of Page
		Thread.sleep(5000);

		boolean search=invite.verify_Send_Invite();
		softAssertion.assertEquals(true, search);
		if(search==true)
		{
			test.pass("Assert Send Invite Button");
			log.info("Assert Send Invite Button");
		}
		else
		{
			test.fail("Assertion failed, Invite Button is not visible");
			log.error("Assert failed, Invite Button is not visible");
		}

		invite.click_Send_Invite();
		test.pass("Click Send Invite Button");
		log.info("Click Send Invite Button");

		Thread.sleep(5000);
		boolean invite_box_boolean=invite.verify_Invite_Box();
		softAssertion.assertEquals(true, invite_box_boolean);
		if(invite_box_boolean==true)
		{
			test.pass("Assert Invitation Platform Pop-up ");
			log.info("Assert Invitation Platform Pop-up");
		}
		else
		{
			test.fail("Assertion failed, Invite Platform Pop-up is not visible");
			log.error("Assert failed, Invite Platform Pop-up is not visible");
		}

	}
	@AfterMethod
	public void closeDriver()
	{
		if(driver!=null)
		{
			// calling flush writes everything to the log file
			extent.flush();			
			sign.closeDriver();
		}
		else{
			log.error("Driver is null at AfterClass (TestBase)");
		}
		log.info("Teardown - Exiting");
	}

}




