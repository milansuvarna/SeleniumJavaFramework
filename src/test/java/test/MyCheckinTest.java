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
import Pages.LikeObj;
import Pages.ListingObj;
import Pages.MyCheckinObj;
import Pages.SearchObj;
import Pages.SignInPageObj;

public class MyCheckinTest extends SetupDriver {

	//public static WebDriver driver;
	public static SignInPageObj sign;
	public static GetURL get;
	public static MyCheckinObj mycheckin;
	public static Logger log;
	public ExtentReports extent;//extent report
	public ExtentTest test;//report 
	public ExtentHtmlReporter htmlReporter;  
	JavascriptExecutor js;
	String myCheckinTitle="Burrp!";
	SoftAssert softAssertion= new SoftAssert();
	public static String userName=null;
	public static String password=null;
	
	@BeforeMethod
	public void setenv() throws IOException
	{

		driver=SetupDriver.getDriver(); //Get Driver from SetupDriver Class
		sign=new SignInPageObj(driver);
		get=new GetURL(driver);
		mycheckin=new MyCheckinObj(driver);
		log=LogManager.getLogger(MyCheckinTest.class); 	//Create Object for Logger Class
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//ExtentReportResults.html");// creates a toggle for the given test, adds all log events under it
		htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();		//Create Object for ExtentReports Class   
		extent.attachReporter(htmlReporter);// create ExtentReports and attach reporter(s) 
		test = extent.createTest("MyCheckinTest", "Sample description");
		js = (JavascriptExecutor) driver;


	}


	public MyCheckinTest() throws IOException
	{
		//Constructor to handle Exception from Super class(SetupDriver)
	}

	@Test()
	public void verify_MyCheckin() throws InterruptedException, IOException

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


		mycheckin.click_HamburgerMenu();
		test.info("Click Hamburger Menu");
		log.info("Click Hamburger Menu");

		mycheckin.click_mycheckin_tab();
		test.pass("Click My Checkin Menu");
		log.info("Click My Checkin Menu");


		//Verify Header of My Checkin Page


		boolean mycheckin_boolean=mycheckin.verify_mycheckin_header();
		softAssertion.assertEquals(true, mycheckin_boolean);
		if(mycheckin_boolean==true)
		{
			test.pass("My Checkin Page Header is displayed, Assertion passed");
			log.info("My Checkin Page Header is displayed, Assertion passed");

		}
		else
		{
			test.fail("My Checkin Page Header is not displayed, Assertion failed");
			log.error("My Checkin Page Header is not displayed, Assertion failed");
		}

		//Verify Checkin data

		int mycheckin_count=mycheckin.mycheckin_img();//get My check-in count
		test.info("Get My check-in count");
		log.info("Get My check-in count");

		if( mycheckin_count!=0)
		{
			mycheckin.click_mycheckin_img();//click on restaurant shown in my check-in
			test.info("Click on restaurant shown in my check-in");
			log.info("Click on restaurant shown in my check-in");

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			String restaurant_title=mycheckin.get_title();//Get tilte for resaturant cliked from my check-in
			test.info("Get tilte for resaturant cliked from my check-in");
			log.info("Get tilte for resaturant cliked from my check-in");


			softAssertion.assertNotEquals(myCheckinTitle, restaurant_title);
			if(restaurant_title!=myCheckinTitle)
			{
				test.pass("Title are different, test case passed");
				log.info("Title are different, test case passed");

			}
			else
			{
				test.fail("Title are same, test case failed");
				log.error("Title are same, test case failed");
			}

		}
		else
		{
			boolean noData_boolean=mycheckin.no_data();
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

