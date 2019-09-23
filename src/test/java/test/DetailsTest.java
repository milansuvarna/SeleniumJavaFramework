package test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Pages.BookmarkObj;
import Pages.DetailsObj;
import Pages.GetURL;
import Pages.ListingObj;
import Pages.SearchObj;
import Pages.SignInPageObj;

public class DetailsTest extends SetupDriver{


	//public static WebDriver driver;
	public static SignInPageObj sign;
	public static GetURL get;

	public static SearchObj search;
	public static ListingObj list;
	public static DetailsObj details;

	public static Logger log;
	public ExtentReports extent;//extent report
	public ExtentTest test;//report 
	public ExtentHtmlReporter htmlReporter;  
	JavascriptExecutor js;

	String title="Burrp!";
	SoftAssert softAssertion= new SoftAssert();
	
//	List<WebElement> time;
	int i;
	public static String userName=null;
	public static String password=null;
	
	@BeforeMethod
	public void setenv() throws IOException
	{

		driver=SetupDriver.getDriver(); //Get Driver from SetupDriver Class
		sign=new SignInPageObj(driver);
		get=new GetURL(driver);
		search=new SearchObj(driver);
		list=new ListingObj(driver);
		details=new DetailsObj(driver);
		log=LogManager.getLogger(DetailsTest.class); 	//Create Object for Logger Class
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//ExtentReportResults.html");// creates a toggle for the given test, adds all log events under it
		//htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();		//Create Object for ExtentReports Class   
		extent.attachReporter(htmlReporter);// create ExtentReports and attach reporter(s) 
		test = extent.createTest("Details Page", "Sample description");
		js = (JavascriptExecutor) driver;


	}

	public DetailsTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test(priority=1)
	public void detailsPage() throws InterruptedException, IOException
	{
		test.log(Status.INFO, "starting test case");
		get.getUrl();			
		test.info("Get URL");
		log.info("Get URL");

		/*sign.clickSignIn();				
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
*/



		search.click_search_box();
		test.log(Status.INFO, "Click Search box");
		log.info("Click Search box");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		search.search_cuisine();//Enter Cuisine Name
		test.log(Status.INFO, "Enter Cuisine Name");
		log.info("Enter Cuisine Name");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		search.click_auto_suggestion();//click on Auto-suggestion
		test.log(Status.INFO, "Click on Auto suggestion");
		log.info("Click on Auto suggestion");
		Thread.sleep(3000);

		details.click_Restaurant();//Click on Restaurant shown on listing page
		test.log(Status.INFO, "Click on Restaurant shown on listing page");
		log.info("Click on Restaurant shown on listing page");
		Thread.sleep(3000);

		String data=details.checkData();//Check Data in Data Page e.g Address, cuisine etc
		if(data.contains("null"))
		{
			test.fail("Restaurant data showing null");
			log.error("Restaurant data showing null");

		}
		else
		{
			test.pass("Restaurant dota Present");
			log.info("Restaurant dota present");

		}

	/*	String before_click=details.like_gettext(); //Get text for Like before click

		details.click_like_icon();
		test.log(Status.INFO, "Click on Like Icon"); //click on Like icon

		String after_click=details.likedIt_gettext(); //Get text for like after click

		if(before_click.contentEquals(after_click))
		{
			test.fail("Like icon is not clickable");
			log.error("Like clicked unsuccessfull");
			log.info("Before Like button is cliked: "+before_click);
			log.info("After Like button is cliked: "+after_click);

		}
		else
		{
			test.pass("Like icon clicked successfully");
			log.info("Like clicked successfull");
			log.info("Before Like button is cliked: "+before_click);
			log.info("After Like button is cliked: "+after_click);
		}
*/


		details.click_tnc(); //click on Tnc Terms and condition pop-up
		test.pass("Tnc linked clicked ");
		log.info("Tnc linked clicked");

		boolean tnc_popup=details.check_popup();//Verify Tnc pop-up is displayed or not
		softAssertion.assertEquals(true, tnc_popup);
		if(tnc_popup==true)
		{
			test.pass("Tnc pop-up displayed");
			log.info("Tnc pop-up displayed");
		}
		else
		{
			test.fail("Tnc pop-not dispalyed");
			log.error("Tnc pop-up not displayed");

		}

		details.click_tnc_ok_btn(); //click on Tnc OK button
		test.log(Status.INFO, "Tnc OK button clicked successfully");
		log.info("Tnc OK button clicked successfully");

		
		int time_size=details.details_timing();
		/*for(i=0;i<time_size;i++)
		{
		details.get_time();
			
		}*/
		if(time_size!=0)
		{
			test.pass("Time is displayed");
			log.info("Time is displayed");		
			Assert.assertEquals(time_size, i);
			
			
		}
		else
		{
			test.fail("Time is not displayed");
			log.error("Time is not displayed");		
			Assert.assertEquals(time_size, 0);
		}
		

		//Verify Restaurant Menu and click on next till end

		boolean chkNextMenu;
		js.executeScript("window.scrollBy(0,1000)"); //scroll down to menu tab
		Thread.sleep(3000);

		details.clickMenu();//click on Menu tab 
		test.log(Status.INFO, "Click on Menu tab");
		log.info("Click on Menu tab");

		js.executeScript("window.scrollBy(0,900)");//scroll down to menu images
		Thread.sleep(3000);


		boolean nextMenu=details.MenuNext();
		if(nextMenu)
		{
			do
			{
				details.clickMenuNext();
				chkNextMenu=details.MenuNext();
				

			}while(chkNextMenu==true);
			test.log(Status.INFO, "Click on Next Button Successfull");
			log.info("Click on Next Button Successfull");
		}

		boolean prevMenu=details.MenuPrev();
		softAssertion.assertEquals(true, prevMenu);
		if(prevMenu==true)
		{
			test.pass("Previous Menu button visible");
			log.info("Previous Menu button visible");
		}
		else
		{
			test.fail("Previous Menu button not visible");
			log.error("Previous Menu button not visible");
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


