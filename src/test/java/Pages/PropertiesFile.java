package Pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import test.Blog;
import test.BookmarkTest;
import test.DetailsTest;
import test.InviteReferTest;
import test.LikeTest;
import test.MyCheckinTest;
import test.MyOfferTest;
import test.RatingTest;
import test.SetupDriver;
import test.SignInTest;


public class PropertiesFile {
	
	
	public static void getProperties() throws IOException
	{
				
		Properties prop=new Properties();
		String projectPath=System.getProperty("user.dir"); //Set Path for Driver i.e User/milansuvarna.../SeleniumJavaFramework
		InputStream input=new FileInputStream(projectPath+"/src/test/java/config/config.properties");
		prop.load(input);
		
		String browser=prop.getProperty("browser");
		String user=prop.getProperty("user");//Set User name
		String pass=prop.getProperty("pass");// Set Password
		String url=prop.getProperty("url");
		
		System.out.println(browser);
		
		SetupDriver.browserName=browser;
		SignInTest.userName=user;
		SignInTest.password=pass;
		BookmarkTest.userName=user;
		BookmarkTest.password=pass;
		DetailsTest.userName=user;
		DetailsTest.password=pass;
		InviteReferTest.userName=user;
		InviteReferTest.password=pass;
		LikeTest.userName=user;
		LikeTest.password=pass;
		MyCheckinTest.userName=user;
		MyCheckinTest.password=pass;
		MyOfferTest.userName=user;
		MyOfferTest.password=pass;
		RatingTest.userName=user;
		RatingTest.password=pass;
		Blog.userName=user;
		Blog.password=pass;
		GetURL.url=url;
		
		
		
		
		
		
		
		
		
		
		
		
		

}
}
