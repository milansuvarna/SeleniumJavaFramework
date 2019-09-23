package test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
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
import Pages.RatingObj;
import Pages.SearchObj;
import Pages.SignInPageObj;

public class SearchTest extends SetupDriver{

	//public static WebDriver driver;
	public static SignInPageObj sign;
	public static GetURL get;
	public static SearchObj search;
	public static Logger log;
	public ExtentReports extent;//extent report
	public ExtentTest test;//report 
	public ExtentHtmlReporter htmlReporter;  
	JavascriptExecutor js;
	String title="Burrp!";
	String post_click_url="http://stage.burrp.com/mumbai/restaurants/mexican-cuisine";
	SoftAssert softAssertion= new SoftAssert();
	
	@BeforeMethod
	public void setenv() throws IOException
	{

		driver=SetupDriver.getDriver(); //Get Driver from SetupDriver Class
		sign=new SignInPageObj(driver);
		get=new GetURL(driver);
		search =new SearchObj(driver);
		log=LogManager.getLogger(SearchTest.class); 	//Create Object for Logger Class
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//ExtentReportResults.html");// creates a toggle for the given test, adds all log events under it
		htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();		//Create Object for ExtentReports Class   
		extent.attachReporter(htmlReporter);// create ExtentReports and attach reporter(s) 
		test = extent.createTest("Search Test", "Sample description");
		js = (JavascriptExecutor) driver;

	}

	public SearchTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
		//Constructor to handle Exception from Super class(SetupDriver)
	}

	@Test()
	public void verifySearchBox() throws InterruptedException
	{
		get.getUrl();	
		test.log(Status.INFO,"Get Url");
		log.info("Get Url");

		search.click_search_box();
		test.log(Status.INFO, "Click Search box");
		log.info("Click Search box");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		boolean beng_bool=search.bengaluru_tab();//Verify Bengaluru tab
		softAssertion.assertEquals(true, beng_bool);
		if(beng_bool==true)
		{
			test.pass("Benagaluru tab is visible, test case is passed");
			log.info("Benagaluru tab is visible, test case is passed");

		}
		else
		{
			test.fail("Benagaluru tab is not visible, test case is failed");
			log.error("Benagaluru tab is not visible, test case is failed");
		}

		boolean delhi_bool=search.delhi_tab();
		softAssertion.assertEquals(true, delhi_bool);
		if(delhi_bool==true)
		{
			test.pass("Delhi tab is visible, test case is passed");
			log.info("Delhi tab is visible, test case is passed");

		}
		else
		{
			test.fail("Delhi tab is not visible, test case is failed");
			log.error("Delhi tab is not visible, test case is failed");
		}

		boolean mumbai_bool=search.mumbai_tab();
		softAssertion.assertEquals(true, mumbai_bool);
		if(mumbai_bool==true)
		{
			test.pass("Mumbai tab is visible, test case is passed");
			log.info("Mumbai tab is visible, test case is passed");

		}
		else
		{
			test.fail("Mumbai tab is not visible, test case is failed");
			log.error("Mumbai tab is not visible, test case is failed");
		}
		boolean kolkata_bool=search.kolkata_tab();
		softAssertion.assertEquals(true, kolkata_bool);
		if(kolkata_bool==true)
		{
			test.pass("Kolkata tab is visible, test case is passed");
			log.info("Kolkata tab is visible, test case is passed");

		}
		else
		{
			test.fail("Kolkata tab is not visible, test case is failed");
			log.error("Kolkata tab is not visible, test case is failed");
		}

		boolean chennai_bool=search.chennai_tab();
		softAssertion.assertEquals(true, chennai_bool);
		if(chennai_bool==true)
		{
			test.pass("Chennai tab is visible, test case is passed");
			log.info("Chennai tab is visible, test case is passed");

		}
		else
		{
			test.fail("Chennai tab is not visible, test case is failed");
			log.error("Chennai tab is not visible, test case is failed");
		}


		boolean hyderabad_bool=search.hyderabad_tab();
		softAssertion.assertEquals(true, hyderabad_bool);
		if(hyderabad_bool==true)
		{
			test.pass("Hyderabad tab is visible, test case is passed");
			log.info("Hyderabad tab is visible, test case is passed");

		}
		else
		{
			test.fail("Hyderabad tab is not visible, test case is failed");
			log.error("Hyderabad tab is not visible, test case is failed");
		}

		boolean pune_bool=search.pune_tab();
		softAssertion.assertEquals(true, pune_bool);
		if(pune_bool==true)
		{
			test.pass("Pune tab is visible, test case is passed");
			log.info("Pune tab is visible, test case is passed");

		}
		else
		{
			test.fail("Pune tab is not visible, test case is failed");
			log.error("Pune tab is not visible, test case is failed");
		}

		//Vetrify Suggested Cafe icon

		boolean nearby_bool=search.search_nearby();
		softAssertion.assertEquals(true, nearby_bool);
		if(nearby_bool==true)
		{
			test.pass("Nearby tab is visible, test case is passed");
			log.info("Nearby tab is visible, test case is passed");

		}
		else
		{
			test.fail("Nearby tab is not visible, test case is failed");
			log.error("Nearby tab is not visible, test case is failed");
		}


		boolean cafe_bool=search.search_cafe();

		if(cafe_bool==true)
		{
			test.pass("Cafe tab is visible, test case is passed");
			log.info("Cafe tab is visible, test case is passed");

		}
		else
		{
			test.fail("Cafe tab is not visible, test case is failed");
			log.error("Cafe tab is not visible, test case is failed");
		}


		//Enter Cuisine
		search.search_cuisine();
		test.info("Enter Cuisine 'Mexican' in search box");
		log.info("Enter Cuisine 'Mexican' in search box");


		//Verify Autosuggestion
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		search.click_auto_suggestion();
		test.pass("Clicked on Auto-suggestion");
		log.info("Clicked on Auto-suggestion");

		Thread.sleep(2000);
		String currentUrl=driver.getCurrentUrl();
		softAssertion.assertEquals(currentUrl, post_click_url);
		if(currentUrl.contains("/restaurants/mexican-cuisine"))
		{
			test.pass("Auto-suggestion clicked and Listing page displayed");
			log.info("Auto-suggestion clcked and Listing page displayed");
		}
		else
		{
			test.fail("Listing page not displayed, auto-suggestion click test case failed");
			log.error("Listing page not displayed, auto-suggestion click test case failed");
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
