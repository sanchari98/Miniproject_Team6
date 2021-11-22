package test;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.loginPage;
import pages.postsModulePage;

import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;

public class postsTest {
	static Logger log = Logger.getLogger(loginTest.class);
	String baseUrl = "http://localhost/testsite/wp-login.php";
	String expectedTitle= "Log In ‹ testsite — WordPress";
	String ExpectedTitle= "Log In ‹ testsite — WordPress";
	WebDriver driver;

	@Test
	public void aUncategoriesedPosts() {
		loginPage l = new loginPage(driver);
		l.loginWordPress(0,1);

		log.info("******************************Posts Test*************************");
		postsModulePage p = new postsModulePage(driver);
		log.info("****************************** post function Test*************************");
		p.postsFunctions();
		p.viewPosts();
	}

	@Test
	public void bCategoryThenPosts() {
		loginPage l = new loginPage(driver);
		l.loginWordPress(0,1);
		log.info("******************************Posts Test*************************");
		postsModulePage p = new postsModulePage(driver);
		log.info("******************************categoriesThenPost Test*************************");
		p.categoriesThenPost();
		p.viewPosts();

	}

	@Test
	public void cCatgoryDuringPosts() {
		loginPage l = new loginPage(driver);
		l.loginWordPress(0,1);
		log.info("******************************CategoriesDuringPost Test*************************");
		postsModulePage p = new postsModulePage(driver);
		p.CategoriesDuringPost();
		p.viewPosts();	

	}

	@Test
	public void dDeletePosts() {
		loginPage l = new loginPage(driver);
		l.loginWordPress(0,1);
		postsModulePage p = new postsModulePage(driver);
		p.viewPosts();
		postsModulePage.deletePosts(driver);
	}

	@BeforeMethod
	public void beforeMethod() {
		log.info("==================================================================");
		log.info("=======================Start Of Testing===========================");

		//		WebDriverManager.chromedriver().setup();
		//		driver= new ChromeDriver();
		//		log.info("login started");

		WebDriverManager.edgedriver().setup();
		driver= new EdgeDriver();
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


	} 
	@AfterMethod
	public void afterMethod() {
		log.info("==================================================================");
		log.info("=======================End Of Testing===========================");
		driver.quit();
		log.info("=======================Test ended=======================");
	}

}

