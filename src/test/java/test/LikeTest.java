package test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Pages.GetURL;
import Pages.InviteReferObj;
import Pages.LikeObj;
import Pages.RatingObj;
import Pages.SignInPageObj;

public class LikeTest extends SetupDriver {


	//public static WebDriver driver;
	public static SignInPageObj sign;
	public static GetURL get;
	public static LikeObj like;
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
		like=new LikeObj(driver);

		log=LogManager.getLogger(LikeTest.class); 	//Create Object for Logger Class
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//ExtentReportResults.html");// creates a toggle for the given test, adds all log events under it
		htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();		//Create Object for ExtentReports Class   
		extent.attachReporter(htmlReporter);// create ExtentReports and attach reporter(s) 
		test = extent.createTest("LikeTest", "Sample description");
		js = (JavascriptExecutor) driver;

	}


	public LikeTest() throws IOException
	{
		//Constructor to handle Exception from Super class(SetupDriver)
	}

	@Test(priority=1)
	public void verify_Like() throws InterruptedException, IOException

	{
		test.log(Status.INFO, "starting test case");
		get.getUrl();			
		test.info("Get URL");
		log.info("Get URL");

		sign.clickSignIn();				
		test.info("click Sign In");
		Thread.sleep(3000);
		test.addScreenCaptureFromPath("screenshot1.png");

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
		test.info("Click Hamburger Menu");
		log.info("Click Hamburger Menu");

		like.click_like_tab();
		test.info("Click Like Menu");
		log.info("Click Like Menu");




		boolean like_boolean=like.verify_like_header();//Verify Like page Header
		softAssertion.assertEquals(true, like_boolean);
		if(like_boolean==true)
		{
			test.pass("Assertion passed,Like Page Header Displayed");
			log.info("Assertion passed,Like Page Header Displayed");;
		}


		int img_size=like.like_img();//Get number of Resaturant in like page
		test.info("Get number of resarurant in Like page");
		log.info("Get number of resarurant in Like page");
		if( img_size!=0)
		{
			like.click_like_img(); //Click on any restaurant in like page
			test.info("Click on any restaurant in like page");
			log.info("Get number of resarurant in Like page");

			String like_title=like.get_title();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			softAssertion.assertNotEquals(title, like_title);	
			if(like_title!=title)
			{
				test.pass("Clicked successfully and user is redirected to details page, Test case passed");
				log.info("Clicked successfully and user is redirected to details page, Test case passed");

			}
			else
			{
				test.fail("Unable to click on like page, Test case Failed");
				log.info("Unable to click on like page, Test case Failed");

			}

		}
		else
		{
			boolean noData_boolean=like.no_data();//Verify No Record found image is shown
			softAssertion.assertEquals(true, noData_boolean);
			if(noData_boolean==true)
			{
				test.pass("No record found image shown, Test case passed");
				log.info("No record found image shown, Test case passed");
			}
			else
			{
				test.fail("No record found image not shown, Test case Failed");
				log.info("No record found image not shown, Test case Failed");

			}
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