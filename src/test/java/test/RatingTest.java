package test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Pages.GetURL;
import Pages.MyOfferObj;
import Pages.RatingObj;
import Pages.SignInPageObj;

public class RatingTest extends SetupDriver {

	//public static WebDriver driver;
	public static SignInPageObj sign;
	public static GetURL get;
	public static RatingObj rating;
	public static Logger log;
	public ExtentReports extent;//extent report
	public ExtentTest test;//report 
	public ExtentHtmlReporter htmlReporter;  
	JavascriptExecutor js;
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
		rating=new RatingObj(this.driver);
		log=LogManager.getLogger(RatingTest.class); 	//Create Object for Logger Class
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//ExtentReportResults.html");// creates a toggle for the given test, adds all log events under it
		htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();		//Create Object for ExtentReports Class   
		extent.attachReporter(htmlReporter);// create ExtentReports and attach reporter(s) 
		test = extent.createTest("Rating Test", "Sample description");
		js = (JavascriptExecutor) driver;


	}


	public RatingTest() throws IOException
	{
		//Constructor to handle Exception from Super class(SetupDriver)
	}

	@Test
	public void verify_Rating() throws InterruptedException, IOException

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
		test.info("Click Hamburger Menu");
		log.info("Click Hamburger Menu");

		rating.click_rating_tab();
		test.pass("Click Rating Menu");
		log.info("Click Rating Menu");



		//Verify Rating Header

		boolean rating_header=rating.verify_rating_header();

		softAssertion.assertEquals(true, rating_header);
		if(rating_header==true)
		{
			test.pass("Rating Page Header is displayed, Assertion passed");
			log.info("Rating Page Header is displayed, Assertion passed");

		}
		else
		{
			test.fail("RatingPage Header is not displayed, Assertion failed");
			log.error("RatingPage Header is not displayed, Assertion failed");
		}


		//Verify Rating Data
		int rating_count=rating.rating_img();//get rating record count
		test.info("Get rating record count");
		log.info("Get rating record count");

		if( rating_count!=0)
		{
			rating.click_rating_img(); //click a record from rating page
			test.info("Click rating record");
			log.info("Click rating record");

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			String rating_title=rating.get_title();//Get rating title
			test.info("Get rating title");
			log.info("Get rating title");

			softAssertion.assertNotEquals(title, rating_title);
			if(rating_title!=title)
			{
				test.pass("Title is Not Same, Test case Passed");
				log.info("Title is Not Same, Test case Passed");
			}
			else
			{
				test.fail("Title is Same, Test case failed");
				log.error("Title is Same, Test case failed");
			}

		}
		else
		{
			boolean noData_boolean=rating.no_data(); //check 'No record found image'
			softAssertion.assertEquals(true, noData_boolean);
			if(noData_boolean==true)
			{
				test.pass("No Record Found image shown, test case passed");
				log.info("No Record Found image shown, test case passed");

			}
			else
			{
				test.fail("No Record Found image not shown, test case failed");
				log.error("No Record Found image not shown, test case failed");
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

