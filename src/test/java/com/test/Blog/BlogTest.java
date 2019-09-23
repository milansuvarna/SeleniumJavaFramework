package com.test.Blog;

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

import Pages.BlogObj;
import Pages.GetURL;
import Pages.LikeObj;
import Pages.SignInPageObj;
import junit.framework.Assert;
import test.Blog;
import test.SetupDriver;

public class BlogTest extends SetupDriver {
	WebDriver driver=SetupDriver.getDriver();
	String title="Burrp!";
	SignInPageObj sign=new SignInPageObj(driver);
	LikeObj like=new LikeObj(driver);
	GetURL get=new GetURL(driver);
	static Logger log=LogManager.getLogger(Blog.class);
	

	
	public  BlogTest() throws InterruptedException, IOException//Constructor to handle Exception from Super class(SetupDriver)
	{
		
	}
	
	
	@Test
	public void verifyBlogPage() throws InterruptedException, IOException
	{
		SignInPageObj sign=new SignInPageObj(this.driver);
		GetURL get=new GetURL(this.driver);
		
		// start reporters
	    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");

	    // create ExtentReports and attach reporter(s)
	    ExtentReports extent = new ExtentReports();
	    extent.attachReporter(htmlReporter);

	    // creates a toggle for the given test, adds all log events under it    
	    ExtentTest report = extent.createTest("MyBlog Test", "Sample description");

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
		
		BlogObj blog=new BlogObj(this.driver);
		
		
	
		blog.click_HamburgerMenu();
		report.pass("Click Hamburger Menu");
		log.info("Click Hamburger Menu");
		Thread.sleep(3000);
		
		blog.click_Blog_Menu();
		report.pass("Click Blog Menu");
		log.info("Click Blog Menu");
		
		blog.refresh_page();
		
		String blogTitle=blog.get_title();
		Assert.assertEquals("Burrp!", blogTitle);
		report.pass("Assert Blog Title");
		log.info("Assert Blog Title");
		
		
		boolean search=blog.verify_searchbox();
		Assert.assertEquals(true, search);
		report.pass("Assert Blog Search Box");
		log.info("Assert Blog Search Box");
		
		boolean explore=blog.verify_explore();
		Assert.assertEquals(true, explore);
		report.pass("Assert Explore Text");
		log.info("Assert Explore Text");
		
		sign.closeDriver();
		report.pass("Close Driver");
		log.info("Close Driver");

		// calling flush writes everything to the log file
        extent.flush();

		
		
		
	}
	

}
