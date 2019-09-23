package test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.Toolkit;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Pages.DetailsObj;
import Pages.GetURL;
import Pages.ListingObj;
import Pages.SearchObj;
import Pages.SignInPageObj;

public class ListingTest extends SetupDriver {


	//public static WebDriver driver;
		public static SignInPageObj sign;
		public static GetURL get;

		public static SearchObj search;
		public static ListingObj list;
		public static Logger log;
		public ExtentReports extent;//extent report
		public ExtentTest test;//report 
		public ExtentHtmlReporter htmlReporter;  
		JavascriptExecutor js;

		String title="Burrp!";
		SoftAssert softAssertion= new SoftAssert();

		@BeforeMethod
		public void setenv() throws IOException
		{

			driver=SetupDriver.getDriver(); //Get Driver from SetupDriver Class
			sign=new SignInPageObj(driver);
			get=new GetURL(driver);
			search=new SearchObj(driver);
			list=new ListingObj(driver);
			log=LogManager.getLogger(ListingTest.class); 	//Create Object for Logger Class
			htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//ExtentReportResults.html");// creates a toggle for the given test, adds all log events under it
			htmlReporter.setAppendExisting(true);
			extent = new ExtentReports();		//Create Object for ExtentReports Class   
			extent.attachReporter(htmlReporter);// create ExtentReports and attach reporter(s) 
			test = extent.createTest("Listing Page", "Sample description");
			js = (JavascriptExecutor) driver;


		}
	
	public ListingTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
		//Constructor to handle Exception from Super class(SetupDriver)
	}


	@Test(priority=1)
	public void VerifyListingPage() throws InterruptedException
	{
		get.getUrl();	
		test.log(Status.INFO,"Get Url");
		log.info("Get Url");

		search.click_search_box();
		test.log(Status.INFO, "Click Search box");
		log.info("Click Search box");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Thread.sleep(3000);
		search.search_cuisine();//Enter Cuisine Name
		test.log(Status.INFO, "Enter Cuisine Name");
		log.info("Enter Cuisine Name");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		search.click_auto_suggestion();//click on Auto-suggestion
		test.log(Status.INFO, "Click on Auto suggestion");
		log.info("Click on Auto suggestion");
		Thread.sleep(3000);

		//Get Count
		int before_scroll_count=list.get_restaurant_count();
		int oldCount=0;
		int newCount=0;
		do
		{
			oldCount=list.get_restaurant_count();
			
			js.executeScript("window.scrollBy(0,7000)");
			Thread.sleep(2000);
			newCount=list.get_restaurant_count();
			
			
		}while(oldCount<newCount);
		test.info("Get Old Count of Restaurant: "+before_scroll_count);
		log.info("Get Old Count of Restaurant: "+before_scroll_count);
		
		test.info("Get New Count of Restaurant: "+newCount);
		log.info("Get New Count of Restaurant: "+newCount);
		
		Assert.assertNotEquals(before_scroll_count,newCount);//Verify old count and new count are different

		if(before_scroll_count!=newCount)
		{
			test.pass("Count is different hence page scrolled successfully, test case passed");
			log.info("Count is different hence page  scrolled successfully, test case passed");
		}
		else
		{
			test.fail("Count is same hence page is not scrolled, test case failed");
			log.info("Count is same hence page is not scrolled, test case failed");
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

