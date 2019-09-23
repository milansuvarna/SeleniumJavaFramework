package test;

import java.awt.Toolkit;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import Pages.PropertiesFile;

public class SetupDriver {

	public static  WebDriver driver;
	public static String browserName=null;

	public static WebDriver getDriver() throws IOException 
	{

		PropertiesFile.getProperties();

		String projectPath=System.getProperty("user.dir"); //Set Path for Driver i.e User/milansuvarna.../SeleniumJavaFramework

		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", projectPath+"//driver/chromedriver/chromedriver");//To Set path for Chrome Driver

			driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", projectPath+"//driver/geckodriver/geckodriver");//To Set path for Firefox Driver

			driver=new FirefoxDriver();
		}
		
		else
		{
			driver=new SafariDriver();
					
		}

		/*Toolkit tl=Toolkit.getDefaultToolkit();
		int width=1280;//(int)tl.getScreenSize().getWidth();
		int height=800;//(int)tl.getScreenSize().getHeight();
		driver.manage().window().setSize(new Dimension(width,height));// Set Window Height and Width
	*/
		driver.manage().window().fullscreen();
		return driver;
	}
	

	
	
	/*public static WebDriver getDriver() {
		System.out.println("Get Driver: "+driver);
		return driver;
	}*/

}


