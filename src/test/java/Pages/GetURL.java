package Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;



public class GetURL{
	WebDriver driver=null;
	static String url=null;

	public GetURL(WebDriver driver)
	{
		this.driver=driver;
	}

	public void getUrl() throws InterruptedException

	{
		driver.get(url);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.navigate().refresh();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
}
