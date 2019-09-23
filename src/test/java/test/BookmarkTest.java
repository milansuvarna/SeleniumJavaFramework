package test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Pages.BookmarkObj;
import Pages.GetURL;
import Pages.LikeObj;
import Pages.SignInPageObj;
import junit.framework.Assert;

public class BookmarkTest extends SetupDriver {

	//public static WebDriver driver;
	public static SignInPageObj sign;
	public static GetURL get;

	public static BookmarkObj bookmark;

	public static Logger log;
	public ExtentReports extent;//extent report
	public ExtentTest test;//report 
	public ExtentHtmlReporter htmlReporter;  
	String title="Burrp!";
	SoftAssert softAssertion= new SoftAssert();
	public static String userName=null;
	public static String password=null;
	
	@BeforeMethod
	public void setenv() throws IOException
	{

		driver=SetupDriver.getDriver(); //Get Driver from SetupDriver Class
		sign=new SignInPageObj(driver);
		get=new GetURL(driver);
		bookmark=new BookmarkObj(driver);

		log=LogManager.getLogger(BookmarkTest.class); 	//Create Object for Logger Class
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//ExtentReportResults.html");// creates a toggle for the given test, adds all log events under it
		htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();		//Create Object for ExtentReports Class   
		extent.attachReporter(htmlReporter);// create ExtentReports and attach reporter(s) 
		test = extent.createTest("Bookmark Page", "Sample description");


	}

	public BookmarkTest() throws IOException
	{
		//Constructor to handle Exception thrown from Super class(SetupDriver)
	}

	@Test()
	public void verify_Bookmark() throws InterruptedException, IOException

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

		sign.click_HamburgerMenu();
		test.log(Status.INFO, "Click Hamburger Menu");
		log.info("Click Hamburger Menu");

		bookmark.click_bookmark_tab();
		test.log(Status.INFO, "Click Bookmark Menu");
		log.info("Click Bookmark Menu");


		boolean bookmark_boolean=bookmark.verify_bookmark_header(); //verify bookmark header

		if(bookmark_boolean)
		{
			test.pass("Test Passed");
		}
		else if(bookmark_boolean==false)
		{
			test.fail("Test Failed");
		}
		else
		{
			test.skip("Test Skipped");
		}
		softAssertion.assertEquals(true, bookmark_boolean);


		int bookmark_data=bookmark.bookmark_img();//Check if bookamrk has data or not
		if( bookmark_data!=0)
		{
			bookmark.click_bookmark_img(); //click on bookmark data if present
			test.log(Status.INFO, "Click Bookmark data");
			log.info("Click Bookmark Menu");

			Thread.sleep(3000);
			String bookmark_title=bookmark.get_title(); 


			if(bookmark_title!=title)
			{
				test.pass("Test Passed");
			}
			else
			{
				test.fail("Test Failed");
			}
			softAssertion.assertNotEquals(title, bookmark_title);//assert bookmark title

		}
		else
		{

			boolean noData_boolean=bookmark.no_data(); //If no data verify image

			if(noData_boolean==true)
			{
				test.pass("Test Passed");
			}
			else
			{
				test.fail("Test Failed");
			}
			softAssertion.assertEquals(true, noData_boolean);//assert no data image


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

