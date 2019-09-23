import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Pages.pageObjSignIn;

public class BrowserTest {
	static WebDriver driver=null;
	
	public static void main(String[] args) throws InterruptedException {
		
		String projectPath=System.getProperty("user.dir"); //Set Path for Driver i.e User/milansuvarna.../SeleniumJavaFramework
	
		System.setProperty("webdriver.chrome.driver", projectPath+"//driver/chromedriver/chromedriver");//To Set path for Chrome Driver
		
		
		driver=new ChromeDriver();
		//WebDriver driver=new FirefoxDriver();
		
		clickSignInLink();
		signInGoogle();
		closeDriver();
		
	}
		public static void clickSignInLink() throws InterruptedException
		{
		driver.get("http://stage.burrp.com/mumbai");
		Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		pageObjSignIn.findSignIn(driver).click();
		Thread.sleep(5000);
		
		}
		
		
		public static void signInGoogle() throws InterruptedException
		{
			Thread.sleep(5000);
			pageObjSignIn.findGoogle(driver).click();
			
			System.out.println(driver.getTitle());
			
			Set<String> windowIds= driver.getWindowHandles();
			System.out.println("Window Ids"+windowIds);
			
			
			
			Iterator<String> iter=windowIds.iterator();
			
			String mainwindow=iter.next();
			String childwindow=iter.next();
			
			
			driver.switchTo().window(childwindow);
			Thread.sleep(5000);
			String s1=driver.getTitle();
			
			System.out.println(s1);
			
			if(s1=="")
			{
				driver.switchTo().window(mainwindow);
				pageObjSignIn.findGoogle(driver).click();
				driver.switchTo().window(childwindow);
				}
			
			pageObjSignIn.email(driver).sendKeys("milan.suvarna@burrp.com");
			pageObjSignIn.userNext(driver).click();
			Thread.sleep(3000);
			pageObjSignIn.pass(driver).clear();
			pageObjSignIn.pass(driver).sendKeys("aditya1987");
			pageObjSignIn.passNext(driver).click();
			
			Thread.sleep(5000);
			driver.switchTo().window(mainwindow);
		}
		
		public static void closeDriver()
		{
			driver.close();
		}

}
