package m.miniProjectPosts;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import m.miniProjectPages.Pages;
//import m.miniProjectPages.EdgeDriver;
import m.miniProjectlogin.login;

public class posts {
	static Logger log = Logger.getLogger(posts.class.getName());
	
	public void postsFunctions (WebDriver driver,Logger log) throws InterruptedException, CsvValidationException, IOException {
		
		    //finding Posts
			WebElement PostsMenu = driver.findElement(By.id("menu-posts"));
			Actions action = new Actions(driver);
			action.moveToElement(PostsMenu).perform();
			log.info("mousehover in Posts button");
			Thread.sleep(2000);
	    
			 //Locating all post menu
	        WebElement subMenu = driver.findElement(By.linkText("All Posts"));
	        //Locating add new page option
	        WebElement subMenu2 = driver.findElement(By.linkText("Add New"));
	   	   //Locating categories menu
	        WebElement subMenu3 = driver.findElement(By.linkText("Categories"));
	        //Locating tag option
	        WebElement subMenu4 = driver.findElement(By.linkText("Tags"));
	        
	        
	        if(subMenu.isDisplayed() &&  subMenu.isEnabled()) {
	            action.moveToElement(subMenu).perform();
	        }
	        if(subMenu2.isDisplayed() &&  subMenu2.isEnabled()) {
	            action.moveToElement(subMenu2).perform();
	        }
	        if(subMenu3.isDisplayed() &&  subMenu3.isEnabled()) {
	            action.moveToElement(subMenu3).perform();
	        }
	        if(subMenu4.isDisplayed() &&  subMenu4.isEnabled()) {
	            action.moveToElement(subMenu4).perform();
	        }
	        if(subMenu.isDisplayed() &&  subMenu.isEnabled()) {
	            action.moveToElement(subMenu).perform();
	        }
	        log.info("Toggling between button");

	      //Locating add new option
	        WebElement AddButton = driver.findElement(By.linkText("Add New"));
	        if(AddButton.isDisplayed() &&  AddButton.isEnabled()) {
	            
	        	 log.info("Add New button is present & enable");
	        	 AddButton.click();
	        	 log.info("clicked Add New button ");
	            Thread.sleep(2000);
	        }
	     
//	      Mini Welcome window handle by click cross button
//	     Thread.sleep(2000);
//	     WebElement cross = driver.findElement(By.cssSelector("body > div:nth-child(8) > div > div > div > div > div > div.components-modal__header > button > svg"));
//		 cross.click();
		 
		 String csvFilename ="files/wordpress.csv";
		 String addtitle=null;
		 String block=null;
		 CSVReader csvRead= new CSVReader(new FileReader(csvFilename));
			String[] row= null;
			
			while((row= csvRead.readNext()) !=null)
			{
				// Title section
			addtitle= row[3];
			driver.findElement(By.id("post-title-0")).sendKeys(addtitle,Keys.TAB);// Title section
			log.info("Entered Title 'TEST'");
			
			// Type/choose a box
			block= row[4];
		    driver.findElement(By.xpath("//div[@class='block-editor-block-list__layout is-root-container']/p")).sendKeys(block,Keys.TAB);
		    log.info("Entered block 'TESTSITE'");
			}
//			 CSVReader csvRead=null;
//			 String[] row= null;
//			 try {
//		            csvRead = new CSVReader(new FileReader(csvFilename));
//		        } catch (FileNotFoundException e1) {
//		            log.info(e1);
//		        }
//			 
//			 try {
//				while((row= csvRead.readNext()) !=null)
//				{
//					addtitle1= row[3];
//					block1= row[4];
//				}
//			 }
//			 
//			 catch(IOException e) {
//		            log.info("input does not read "+e);
//		        } catch (CsvValidationException e) {
//		            // TODO Auto-generated catch block
//		            e.printStackTrace();
//		        }
			
			// clicking on publish button
			WebElement publish = driver.findElement(By.xpath("//*[@id='editor']/div[1]/div/div[1]/div/div[2]/button[2]"));
			if(publish.isDisplayed() && publish.isEnabled()) {
			log.info("publish button is present and enable");
			publish.click();
			log.info("Clicked publish button");
			Thread.sleep(2000);
			}
			
			// 2nd time click on publish button
			WebElement publish2 = driver.findElement(By.xpath("//*[@id=\'editor\']/div[1]/div/div[2]/div[4]/div[2]/div/div/div[1]/div[1]/button"));
			if(publish2.isDisplayed() && publish2.isEnabled()) {
				log.info("publish2 button is present and enable");
				publish2.click();
				log.info("Second time Clicked publish button");
				Thread.sleep(2000);
				}
			
			//view recent page by linkText
			WebElement view = driver.findElement(By.linkText("View Post"));
			if(view.isDisplayed() && view.isEnabled()) {
				log.info("view button is present and enable");
				view.click();
				log.info("Clicked view post button");
				Thread.sleep(2000);
				}
			
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// This  will scroll down the page by  200 pixel vertical		
	        js.executeScript("window.scrollBy(0,200)");
	        log.info("Window scroll by 200 pixel vertical");
			
			
			//Mouse Hover on testsite menu
		     WebElement testsitemenu = driver.findElement(By.xpath("//*[@id=\"wp-admin-bar-site-name\"]/a")); //*[@id="menu-posts"]/a/div[3]
		 	if(testsitemenu.isDisplayed() && testsitemenu.isEnabled()) {
				log.info("testsite button is present and enable");
//				action.moveToElement(SiteName).perform();
//				log.info("moved to testsite button");
				testsitemenu.click();
				log.info("Clicked testsite button");
				Thread.sleep(2000);
				}
			 
			// Selecting dashboard from dropdown
		 	WebElement dash =  driver.findElement(By.linkText("Dashboard"));
			 if(dash.isDisplayed() && dash.isEnabled()) {
					log.info("dashboard button is present and enable");
					dash.click();
					log.info("Clicked dashboard button");
					Thread.sleep(2000);
					}
	}
	 public void viewPosts(WebDriver driver,Logger log) throws InterruptedException {
					//finding Posts
					WebElement PostsMenu = driver.findElement(By.id("menu-posts"));
					Actions action = new Actions(driver);
					action.moveToElement(PostsMenu).perform();
					log.info("mousehover in Posts button");
					Thread.sleep(2000);

					//finding all pages from posts dropdown
					WebElement AllPosts = driver.findElement(By.linkText("All Posts"));
					if(AllPosts.isDisplayed() && AllPosts.isEnabled()) {
						log.info("All Posts button is present and enable");
						action.moveToElement(AllPosts).build().perform();
						log.info("Move to All Posts button");
						AllPosts.click();
						log.info("Clicked all posts button");
					}
					 				 
	 }
	 public void Categories(WebDriver driver,Logger log) throws InterruptedException, CsvValidationException, IOException {
		//Locating add new option
	        WebElement AddButton = driver.findElement(By.linkText("Add New"));
	        if(AddButton.isDisplayed() &&  AddButton.isEnabled()) {
	            
	        	 log.info("Add New button is present & enable");
	        	 AddButton.click();
	        	 log.info("clicked Add New button ");
	            Thread.sleep(2000);
	        }
	        String csvFilename ="files/wordpress.csv";
			 String addtitle1=null;
			 String block1=null;
			 CSVReader csvRead= new CSVReader(new FileReader(csvFilename));
				String[] row= null;
				
				while((row= csvRead.readNext()) !=null)
				{
					// Title section
				addtitle1= row[3];
				driver.findElement(By.id("post-title-0")).sendKeys(addtitle1);// Title section  ,Keys.TAB
				log.info("Entered Title 'TEST'");
	 }
				WebElement cat = driver.findElement(By.xpath("//*[@id='editor']/div[1]/div[1]/div[2]/div[3]/div/div[3]/div[3]/h2/button"));
				cat.click();
				
				//Scroll down using SPACE BAR
				 WebElement scrollbar = driver.findElement(By.xpath("//*[@id='editor']/div[1]/div[1]/div[2]/div[3]"));
				 scrollbar.click();
				 scrollbar.sendKeys(Keys.SPACE);
				 
				 //click on add new category button
				 WebElement addnewcat = driver.findElement(By.xpath("//*[@id=\"editor\"]/div[1]/div[1]/div[2]/div[3]/div/div[3]/div[3]/button"));
				 addnewcat.click();
				 
				 WebElement NewCategoryName = driver.findElement(By.xpath("//*[@id='editor-post-taxonomies__hierarchical-terms-input-0']"));
				 NewCategoryName.sendKeys("Human");
				 NewCategoryName.sendKeys(Keys.TAB);
				 NewCategoryName.sendKeys(Keys.TAB);
				// NewCategoryName.sendKeys(Keys.SPACE);
				 
//				 JavascriptExecutor js = (JavascriptExecutor) driver;
//					// This  will scroll down the page by  100 pixel vertical		
//			        js.executeScript("window.scrollBy(0,100)");
//			        
//				 //Scroll down using SPACE BAR
//				 WebElement scrollbar1 = driver.findElement(By.xpath("//*[@id='editor']/div[1]/div[1]/div[2]/div[3]"));
//				 scrollbar1.click();
//				 scrollbar1.sendKeys(Keys.SPACE);
	 }	 
	 
	 
	 
/*	 public void Categories(WebDriver driver,Logger log) throws InterruptedException, CsvValidationException, IOException {
		 //Locating categories menu
//	        WebElement categories = driver.findElement(By.linkText("Categories"));
//	        //WebElement categories = driver.findElement(By.linkText("//*[@id='menu-posts']/ul/li[4]/a"));
//	        if(categories.isDisplayed() && categories.isEnabled()) {
//	        	Actions action = new Actions(driver);
//				log.info("category's Name field is present and enable");
//				action.moveToElement(categories).build().perform();
//				log.info("Move to category's Name field");
//				categories.click();
//				log.info("Clicked category's Name field");
//				Thread.sleep(2000);
//	        }
//	        
		 	driver.findElement(By.linkText("Categories")).click();
		 	driver.findElement(By.id("tag-name")).click();
	        String csvFilename ="files/wordpress.csv";
	        String categoryName=null;
	   	    CSVReader csvRead= new CSVReader(new FileReader(csvFilename));
			String[] row= null;
				while((row= csvRead.readNext()) !=null)
				{
					// Title section
				categoryName= row[9];
				driver.findElement(By.xpath("//*[@id=\'tag-name\']")).sendKeys(categoryName);
				Thread.sleep(3000);	
				//driver.findElement(By.linkText("Categories")).click();
				//.sendKeys(categoryName);// Title section
				//*[@id="tag-name"]
				log.info("Entered category's Name 'Company'");
			 }
	        
				
			Thread.sleep(3000);	
	        JavascriptExecutor js = (JavascriptExecutor) driver;
			// This  will scroll down the page by  1000 pixel vertical		
	        js.executeScript("window.scrollBy(0,1000)");
	        
	        WebElement AddNewcategory = driver.findElement(By.id("submit"));
	        AddNewcategory.click();
	        
	        Thread.sleep(3000);	
	        JavascriptExecutor js1 = (JavascriptExecutor) driver;
			// This  will scroll down "UP" the page by  500 pixel vertical		
	        js1.executeScript("window.scrollBy(0,-500)");
	        
	      //Locating add new option
	        WebElement AddButton = driver.findElement(By.linkText("Add New"));
	        if(AddButton.isDisplayed() &&  AddButton.isEnabled()) {
	            AddButton.click();
	            log.info("Add New button is present & enable");
	            Thread.sleep(2000);
	        }
	        
	 }
	 
	 public void categoriesThenPost(WebDriver driver,Logger log) throws CsvValidationException, IOException, InterruptedException {

		 String csvFilename ="files/wordpress.csv";
		 String addtitle1=null;
		 String block1=null;
		 CSVReader csvRead= new CSVReader(new FileReader(csvFilename));
			String[] row= null;
			
			while((row= csvRead.readNext()) !=null)
			{
				// Title section
			addtitle1= row[3];
			driver.findElement(By.id("post-title-0")).sendKeys(addtitle1);// Title section  ,Keys.TAB
			log.info("Entered Title 'TEST'");
			
			WebElement cat = driver.findElement(By.xpath("//*[@id='editor']/div[1]/div[1]/div[2]/div[3]/div/div[3]/div[3]/h2/button"));
			cat.click();
			cat.sendKeys(Keys.TAB);
			cat.sendKeys(Keys.TAB);
			//driver.findElement(By.linkText("Company")).click();
			
			
			
			driver.findElement(By.xpath("//*[@id='editor']/div[1]/div[1]/div[2]/div[3]")).click();
//			Thread.sleep(2000);	
//	        JavascriptExecutor js = (JavascriptExecutor) driver;
//			// This  will scroll down the page by  200 pixel vertical		
//	        js.executeScript("window.scrollBy(0,200)");
			
			// Type/choose a box
			block1= row[4];
		    driver.findElement(By.xpath("//div[@class='block-editor-block-list__layout is-root-container']/p")).sendKeys(block1,Keys.TAB);
		    log.info("Entered block 'TESTSITE'");
			}
	 }
	 */
	public static void main(String[] args) throws CsvValidationException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		 System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		 WebDriver	driver = new ChromeDriver();
		 log.info("login started");
//		  System.setProperty("webdriver.edge.driver", "resources/msedgedriver.exe");  
//	        WebDriver driver = new EdgeDriver();
//		 log.info("login started");
		 login l = new login();
		 l.loginwordpress(driver,log);
		 posts p = new posts();
		 p.postsFunctions(driver,log);
		 p.viewPosts(driver,log);
		 p.Categories(driver,log);
//		 p.categoriesThenPost(driver,log);
		// driver.quit();
		 log.info("Test ended");
	}
;
}
