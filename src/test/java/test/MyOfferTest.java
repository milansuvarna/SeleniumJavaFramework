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

import Pages.GetURL;
import Pages.LikeObj;
import Pages.MyCheckinObj;
import Pages.MyOfferObj;
import Pages.SignInPageObj;

public class MyOfferTest extends SetupDriver {

	//public static WebDriver driver;
	public static SignInPageObj sign;
	public static GetURL get;
	public static MyOfferObj offer;
	public static Logger log;
	public ExtentReports extent;//extent report
	public ExtentTest test;//report 
	public ExtentHtmlReporter htmlReporter;  
	JavascriptExecutor js;
	String myOfferTitle="Burrp!";
	SoftAssert softAssertion= new SoftAssert();
	public static String userName=null;
	public static String password=null;
	
	@BeforeMethod
	public void setenv() throws IOException
	{

		driver=SetupDriver.getDriver(); //Get Driver from SetupDriver Class
		sign=new SignInPageObj(driver);
		get=new GetURL(driver);
		offer=new MyOfferObj(driver);
		log=LogManager.getLogger(MyOfferTest.class); 	//Create Object for Logger Class
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//ExtentReportResults.html");// creates a toggle for the given test, adds all log events under it
		htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();		//Create Object for ExtentReports Class   
		extent.attachReporter(htmlReporter);// create ExtentReports and attach reporter(s) 
		test = extent.createTest("MyOfferTest", "Sample description");
		js = (JavascriptExecutor) driver;


	}

	public MyOfferTest() throws IOException
	{
		//Constructor to handle Exception from Super class(SetupDriver)
	}

	@Test()
	public void verify_MyOfferPage() throws InterruptedException, IOException
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

		offer.click_my_offer_menu_tab();
		test.info("Click My Offer Menu");
		log.info("Click My Offer Menu");
		Thread.sleep(3000);


		//Verify Offer Header
		boolean offer_boolean=offer.verify_MyOffer_Header();
		test.info("Check if My Offer Header is visible or not");
		log.info("Check if My Offer Header is visible or not");
		
		softAssertion.assertEquals(true, offer_boolean);// Assert My Offer Header
		if(offer_boolean==true)
		{
			test.pass("My Offer Header verified, test case passed");
			log.info("My Offer Header verified, test case passed");
		}
		else
		{
			test.fail("My Offer Header is not shown, test case failed");
			log.info("My Offer Header is not shown, test case failed");
		}
		//Verify My offer Data
		int myOffer_GetCode_count=offer.verify_MyOffer_GetCode();
		test.info("Get count of offer in My offer page");
		log.info("Get count of offer in My offer page");
		
		
		if(myOffer_GetCode_count!=0)
		{
			offer.click_GetCode();
			test.info("Click on Get Code Button");
			log.info("Click on Get Code Button");

			boolean postGetCode_modal_boolean=offer.verify_MyOffer_PostGetCode_Modal();
			softAssertion.assertEquals(true, postGetCode_modal_boolean);
			if(postGetCode_modal_boolean==true)
			{
			test.pass("Post Get Code CLick verify 'DownLoad Burrp app Modal' dispalyed, test case passed");
			log.info("Post Get Code CLick verify 'DownLoad Burrp app Modal' displayed, test case passed");
			}
			else
			{
				test.fail("Post Get Code CLick verify 'DownLoad Burrp app Modal' not shown, test case failed");
				log.info("Post Get Code CLick verify 'DownLoad Burrp app Modal', test case failed");
			}
			offer.click_modal_ok_xpath();
			test.pass("Click on OK Button");
			log.info("Click on OK Button");

		}
		else
		{
			boolean noData_boolean=offer.no_data();//Verify No Record Found image
			softAssertion.assertEquals(true, noData_boolean);
			if(noData_boolean==true)
			{
				test.pass("No Record found Image verified, Test case passed");
				log.info("No Record found Image verified, Test case passed");
			}
			else
			{
				test.fail("No Record found Image not shown, Test case failed");
				log.info("No Record found Image not shown, Test case failed");
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


