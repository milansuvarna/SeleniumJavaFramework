package com.test.BookmarkTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Pages.BookmarkObj;
import Pages.GetURL;
import Pages.SignInPageObj;
import junit.framework.Assert;
import test.SetupDriver;

public class BookmarkTest extends SetupDriver {
	WebDriver driver=SetupDriver.getDriver();
	SignInPageObj sign=new SignInPageObj(driver);
	GetURL get=new GetURL(driver);
	static Logger log=LogManager.getLogger(BookmarkTest.class);
	String title="Burrp!";
	ExtentReports extent = new ExtentReports();
	BookmarkObj bookmark=new BookmarkObj(this.driver);

	public BookmarkTest() throws IOException
	{
		//Constructor to handle Exception thrown from Super class(SetupDriver)
	}

	@Test(priority=1)
	public void verify_Bookmark() throws InterruptedException

	{
		try
		{

			// start reporters
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");

			// create ExtentReports and attach reporter(s)

			extent.attachReporter(htmlReporter);

			// creates a toggle for the given test, adds all log events under it    
			ExtentTest report = extent.createTest("Bookmark Page", "Sample description");
			report.log(Status.INFO, "starting test case");

			get.getUrl();
			report.pass("Get Url");
			log.info("Get Url");

			sign.clickSignIn();
			report.pass("Click Sign In");
			log.info("Click Sign In");

			sign.clickGoogle();
			report.pass("Click Google");
			log.info("Click Google");

			sign.enterUserName("milan.suvarna@burrp.com");
			report.pass("Enter Email Id");
			log.info("Enter Email Id");

			sign.clickUserNameNext();
			report.pass("Click User Next Button");
			log.info("Click User Next Button");

			sign.enterPassword("aditya1987");
			report.pass("Enter Password");
			log.info("Password");

			sign.clickPassNext();
			report.pass("Click Password Next Button");
			log.info("Click Password Next Button");

			sign.click_HamburgerMenu();
			report.pass("Click Hamburger Menu");
			log.info("Click Hamburger Menu");

			bookmark.click_bookmark_tab();
			report.pass("Click Bookmark Menu");
			log.info("Click Bookmark Menu");

		}
		catch(Exception exp)
		{
			System.out.println("Get Message"+exp.getMessage());
			System.out.println("Get Cause"+exp.getCause());
			exp.printStackTrace();
		}

	}		
	@Test(priority=2)
	public void verifyBookmarkHeader()
	{

		boolean bookmark_boolean=bookmark.verify_bookmark_header();
	}
	@Test(priority=3)
	public void verifyBookmarkData()
	{
		int bookmark_data=bookmark.bookmark_img();
		if( bookmark_data!=0)
		{
			bookmark.click_bookmark_img();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			String bookmark_title=bookmark.get_title();
			Assert.assertNotSame("Not Same", title, bookmark_title);
		}
		else
		{
			boolean noData_boolean=bookmark.no_data();
			Assert.assertEquals(true, noData_boolean);
		}

	}

	@Test(priority=4)
	public void closeDriver()
	{
		// calling flush writes everything to the log file
		extent.flush();			
		sign.closeDriver();


	}


}

