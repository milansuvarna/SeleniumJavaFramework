package test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Pages.BlogObj;
import Pages.BookmarkObj;
import Pages.GetURL;
import Pages.LikeObj;
import Pages.SignInPageObj;




public class Blog extends SetupDriver {

	//public static WebDriver driver;
	public static SignInPageObj sign;
	public static GetURL get;
	public static BlogObj blog;

	String title="Burrp!";
	public ExtentReports extent;//extent report
	public ExtentTest test;//report 
	public static Logger log;
	public ExtentHtmlReporter htmlReporter;    
	public static String userName=null;
	public static String password=null;
	
	SoftAssert softAssertion = new SoftAssert();
	@BeforeMethod
	public void setenv() throws IOException
	{
		driver=SetupDriver.getDriver(); //Get Driver from SetupDriver Class	
		sign=new SignInPageObj(driver);
		get=new GetURL(driver);
		blog=new BlogObj(driver);

		log=LogManager.getLogger(Blog.class); 	//Create Object for Logger Class
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//ExtentReportResults.html");// creates a toggle for the given test, adds all log events under it
		htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();		//Create Object for ExtentReports Class   
		extent.attachReporter(htmlReporter);// create ExtentReports and attach reporter(s) 
		test = extent.createTest("Blog Page", "Sample description");

	}

	public  Blog() throws InterruptedException, IOException//Constructor to handle Exception from Super class(SetupDriver)
	{

	}


	@Test()
	public void verifyBlogPage() throws InterruptedException, IOException
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



		blog.click_HamburgerMenu();
		test.pass("Click Hamburger Menu");
		log.info("Click Hamburger Menu");
		Thread.sleep(3000);


		blog.click_Blog_Menu();
		test.pass("Click Blog Menu");
		log.info("Click Blog Menu");

		blog.refresh_page();
		Thread.sleep(3000);


		//Verifying Blog Page

		String blogTitle=blog.get_title();
		softAssertion.assertEquals(title, blogTitle);

		if(blogTitle.equals(title))
		{
			test.pass("Assert Blog Title Passed");
			log.info("Assert Blog Title Passed");
		}
		else
		{
			test.fail("Assert Blog Title Failed");
			log.info("Assert Blog Title Failed");
		}


		boolean search=blog.verify_searchbox();
		softAssertion.assertEquals(true, search);
		if(search==true)
		{
			test.pass("Assert Blog Search Box Passed");
			log.info("Assert Blog Search Box Failed");
		}
		else
		{
			test.fail("Assert Blog Search Box Failed");
			log.info("Assert Blog Search Box Failed");
		}
		boolean explore=blog.verify_explore();
		softAssertion.assertEquals(true, explore);
		if(explore==true)
		{
			test.pass("Assert Explore Text Passed");
			log.info("Assert Explore TextPassed");
		}
		else
		{
			test.fail("Assert Explore Text Failed");
			log.info("Assert Explore TextPassed Failed");
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







