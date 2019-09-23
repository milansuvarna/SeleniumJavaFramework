package test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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

import Pages.DetailsObj;
import Pages.FilterObj;
import Pages.GetURL;
import Pages.ListingObj;
import Pages.SearchObj;
import Pages.SignInPageObj;

public class FilterTest extends SetupDriver{

	//WebDriver driver=SetupDriver.getDriver();

	public static SignInPageObj sign;
	public static GetURL get;

	public static SearchObj search;
	public static ListingObj list;
	public static DetailsObj details;
	public static FilterObj filter;

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
		details=new DetailsObj(driver);
		filter=new FilterObj(driver);
		log=LogManager.getLogger(FilterTest.class); 	//Create Object for Logger Class
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//ExtentReportResults.html");// creates a toggle for the given test, adds all log events under it
		htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();		//Create Object for ExtentReports Class   
		extent.attachReporter(htmlReporter);// create ExtentReports and attach reporter(s) 
		test = extent.createTest("Filter Page", "Sample description");
		js = (JavascriptExecutor) driver;


	}


	public FilterTest() throws IOException
	{

	}

	@Test(priority=1)
	public void VerifyFilter() throws InterruptedException
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

		//verifyFilterApplied

		int oldCount=0;
		int newCount=0;
		int post_filter_count=0;
		do
		{
			oldCount=list.get_restaurant_count(); 

			js.executeScript("window.scrollBy(0,5000)");
			Thread.sleep(5000);
			newCount=list.get_restaurant_count(); 

		}while(oldCount<newCount);

		test.info("Get Count of Restaurant: "+newCount);
		log.info("Get Count of Restaurant: "+newCount);

		filter.click_filter();		//Click on Burrp More Filter
		test.info("Click on Burrp More Filter");
		log.info("Click on Burrp More Filter");

		post_filter_count=list.get_restaurant_count(); //Get Count post Filter
		test.info("Get Count of Restaurant post filter applied: "+post_filter_count);
		log.info("Get Count of Restaurant post filter applied: "+post_filter_count);

		Thread.sleep(3000);
		Assert.assertNotEquals(newCount, post_filter_count);

		if(post_filter_count!=newCount)
		{
			test.pass("Count is different hence filter applied successfully");
			log.info("Count is different hence filter applied successfully");
		}
		else
		{
			test.fail("Count is same hence filter not appiled");
			log.info("Count is same hence filter not appiled");
		}

		//verify Url Post Filter is applied
		String currentUrl=filter.getCurrenturl();
		if(currentUrl.contains("?offer_subscriptio"))
		{
			test.pass("URL Contain ?Offer_Subscription, Test Case Passed");
			log.info("URL Contain ?Offer_Subscription, Test Case Passed: "+currentUrl);
		}
		else
		{
			test.fail("URL does not contain ?offer_subscription, Test Case Failed");
			log.error("URL does not contain ?offer_subscription, Test Case Failed: "+currentUrl);
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

