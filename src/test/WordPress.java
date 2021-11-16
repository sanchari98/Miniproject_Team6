package test;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.Pages.Pages;
import com.Posts.PostTest;
import com.login.Login;
import com.media.Media;
import com.opencsv.exceptions.CsvValidationException;

public class WordPress {
	String baseUrl = "http://localhost/wordpress/wp-login.php";
	WebDriver driver;
	Logger log = Logger.getLogger(WordPress.class);
	
//	@Test
//	public void TestLogin() throws CsvValidationException, IOException {
//		log.info("******************************Launching Login Test****************************");
//		Login l = new Login();
//	    l.login(driver, log);
//	    log.info("******************************LogIn Test complete**************************");
//	}
//		
//	@Test
//	public void TestMedia() throws InterruptedException, AWTException {
//		log.info("******************************Launching Media Test****************************");
//		Media media =new Media();
//	    media.viewMedia(driver, log);
//	    log.info("******************************Media Test complete**************************");
//	}
//	
//	@Test
//	public void TestPost() throws CsvValidationException, IOException, InterruptedException {
//		PostTest post = new PostTest();
//		post.mouseOver(driver);
////		driver.navigate().refresh();
//	}
//	
//	@Test
//	public void TestPage() throws CsvValidationException, IOException, InterruptedException {
//		Pages page = new Pages();
//		page.addPages(driver);
//		page.viewPages(driver);
//	}
	
	
	
	
	@BeforeClass
	public void beforeClass() {
		log.info("*****************************************************************");
		log.info("***********************Starting Testing***************************");
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		driver=new ChromeDriver();
		log.info("launching Browser");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
		log.info("launching Wordpress");
	}
	
	@AfterClass
	public void afterClass() throws InterruptedException {
		log.info("*****************************************************************");
		log.info("***********************Finish Testing***************************");
		Thread.sleep(2000);
		driver.quit();
		log.info("***********************Quiting Browser***************************");
		
	}
}