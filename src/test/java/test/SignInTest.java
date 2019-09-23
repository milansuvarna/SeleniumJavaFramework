package test;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.awt.Toolkit;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
//import com.sun.tools.sjavac.Log;

import Pages.BookmarkObj;
import Pages.GetURL;
import Pages.LikeObj;
import Pages.PropertiesFile;
import Pages.RatingObj;
import Pages.SignInPageObj;

public class SignInTest extends SetupDriver {

	//public static WebDriver driver;
	public static SignInPageObj sign;
	public static GetURL get;
	public static Logger log;
	public ExtentHtmlReporter htmlReporter;    
	public static String userName=null;
	public static String password=null;

	public ExtentReports extent;//extent report
	public ExtentTest test;//report 
	
	@BeforeMethod
	public void setenv() throws IOException
	{
		driver=SetupDriver.getDriver(); //Get Driver from SetupDriver Class
		sign=new SignInPageObj(driver);
		get=new GetURL(driver);
		log=LogManager.getLogger(RatingTest.class); 	//Create Object for Logger Class
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//ExtentReportResults.html");// creates a toggle for the given test, adds all log events under it
		htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();		//Create Object for ExtentReports Class   
		extent.attachReporter(htmlReporter);// create ExtentReports and attach reporter(s) 
		test = extent.createTest("SignIn Test", "Sample description");
		
	}

	public  SignInTest() throws IOException //Constructor to handle Exception from Super class(SetupDriver) 
	{

	}
	@Test(priority=1)
	public void TestsignIn() throws InterruptedException, IOException	
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


