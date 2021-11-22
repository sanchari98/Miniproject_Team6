package test;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.Media;
import pages.Pages;
import pages.login;
import pages.posts;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
public class WordPress {
	static Logger log = Logger.getLogger(WordPress.class);
	String baseUrl = "http://localhost/wordpress/wp-login.php";
	String expectedTitle= "Log In ‹ testsite — WordPress";
	String ExpectedTitle= "Log In ‹ testsite — WordPress";
	WebDriver driver;



//	@Test
//	public void aloginWithCorrectCredentials() {
//		login l = new login(driver);
//		l.clickSubmit(0,1);
//
//
//
//	}
//
//
//
//	@Test
//	public void bloginWithIncorrectUsername() {
//		login l = new login(driver);
//		l.clickSubmit(4,1);
//	}
//
//
//
//	@Test
//	public void cloginWithIncorrectPassword() {
//		login l = new login(driver);
//		l.clickSubmit(0,3);
//	}



//	@Test
//	public void dloginWithIncorrectUsernameAndPassword() {
//		login l = new login(driver);
//		l.clickSubmit(4,3);
//	}
//
//
//
	@Test
	public void eWordPressPosts() {
		login l = new login(driver);
		l.clickSubmit(0,1);

		log.info("******************************Posts Test*************************");
		posts p = new posts(driver);
//		log.info("****************************** post function Test*************************");
//		p.postsFunctions();
//		p.viewPosts();
//
//
//
//		log.info("******************************categoriesThenPost Test*************************");
//		p.categoriesThenPost();
//		p.viewPosts();
//
//
//
//		log.info("******************************CategoriesDuringPost Test*************************");
//		p.CategoriesDuringPost();
		p.viewPosts();
		posts.delectPosts(driver);
	}
//
//

//	@Test
//	public void fWordPressMedia() {
//		login l = new login(driver);
//		l.clickSubmit(0,1);
//		log.info("******************************Media Test*************************");
//		Media m = new Media(driver);
//		m.upload();
//		m.Selectfile();
//		m.deleteMedia();
//	}



//	@Test
//	public void gWordPressPages() {
//		login l = new login(driver);
//		l.clickSubmit(0,1);
//		log.info("******************************Pages Test*************************");
//		Pages pages = new Pages(driver);
//		pages.PagesAll();
//		pages.publish();
//		pages.viewPages();
//		posts.delectPosts(driver);
//	}
//



	@BeforeMethod
	public void beforeMethod() {
		log.info("==================================================================");
		log.info("=======================Start Of Testing===========================");
//		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		 WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		// System.setProperty("webdriver.edge.driver", "resources/msedgedriver.exe");
		// driver = new EdgeDriver();
		log.info("login started");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
		log.info("Navigated to http://localhost/testsite/wp-login.php i.e Login window opened ");
		if(driver.getTitle().equals(expectedTitle)) {
			log.info("Title matched "+ExpectedTitle);
		}
		else {
			log.info("Title does not matched");
		}




	} @AfterMethod
	public void afterMethod() {
		log.info("==================================================================");
		log.info("=======================End Of Testing===========================");
		driver.quit();
		log.info("=======================Test ended=======================");
	}



}