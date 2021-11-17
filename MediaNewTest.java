package wordpressDemo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;

public class MediaNewTest {
  WebDriver driver;
  
  @BeforeMethod
  public void beforeMethod() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver","./resources2/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost/wordpress-demo/wp-admin/");
		WebElement unm = driver.findElement(By.id("user_login"));
		unm.sendKeys("admin");
		WebElement pswd = driver.findElement(By.id("user_pass"));
		pswd.sendKeys("admin");
		driver.findElement(By.id("wp-submit")).click();
		WebElement med=driver.findElement(By.xpath("//div[contains(text(),'Media')]"));
		Actions action = new Actions(driver);
		action.moveToElement(med).perform();
		WebElement mainmenu=driver.findElement(By.xpath("//div[contains(text(),'Media')]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(mainmenu);
		WebElement subMenu = driver.findElement(By.xpath("//a[contains(text(),'Library')]"));
		
		WebElement subMenu2 = driver.findElement(By.xpath("//body/div[@id='wpwrap']/div[@id='adminmenumain']/div[@id='adminmenuwrap']/ul[@id='adminmenu']/li[@id='menu-media']/ul[1]/li[3]/a[1]"));
		actions.moveToElement(subMenu);
		actions.moveToElement(subMenu2);
		Thread.sleep(5000);
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		
		
		driver.findElement(By.xpath("//body/div[@id='wpwrap']/div[@id='wpcontent']/div[@id='wpbody']/div[@id='wpbody-content']/div[@id='wp-media-grid']/a[1]")).click();
		driver.findElement(By.id("__wp-uploader-id-1")).click();//sendKeys("C:\\Users\\bristi_mazumdar\\OneDrive - Persistent Systems Limited\\Pictures\\Screenshots\\Screenshot (12).png");
		
   }
  @Test
  public void f() {
	  
  }
  
  

  @AfterMethod
  public void afterMethod() {
	  
  }

}
