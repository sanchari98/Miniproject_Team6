package m.miniProjectMedia;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.opencsv.exceptions.CsvValidationException;
import m.miniProjectlogin.login;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

//import m.miniProjectPages.EdgeDriver;

 

public class Media {
	static Logger log = Logger.getLogger(Media.class.getName());
	
public void viewMedia(WebDriver driver,Logger log) throws InterruptedException {
        
        //Locating media menu
        WebElement med = driver.findElement(By.xpath("//div[contains(text(),'Media')]"));
        Actions action = new Actions(driver);
        action.moveToElement(med).perform();
        log.info("mousehover in  media button");
        Thread.sleep(2000);
        
        //Locating Library menu
        WebElement subMenu = driver.findElement(By.xpath("//a[contains(text(),'Library')]"));
        //Locating add new media option
        WebElement subMenu2 = driver.findElement(By.xpath("//body/div[@id='wpwrap']/div[@id='adminmenumain']/div[@id='adminmenuwrap']/ul[@id='adminmenu']/li[@id='menu-media']/ul[1]/li[3]/a[1]"));
        
        if(subMenu.isDisplayed() &&  subMenu.isEnabled()) {
            action.moveToElement(subMenu).perform();
            
        }
        if(subMenu2.isDisplayed() &&  subMenu2.isEnabled()) {
            action.moveToElement(subMenu2).perform();
          
        }
        if(subMenu.isDisplayed() &&  subMenu.isEnabled()) {
            action.moveToElement(subMenu).perform();
            
        }
        log.info("Toggling between button");
        
     //Locating add new option
      //  WebElement AddButton = driver.findElement(By.linkText("Add New"));
        WebElement AddButton = driver.findElement(By.xpath("//*[@id='menu-media']/ul/li[3]/a"));
        if(AddButton.isDisplayed() &&  AddButton.isEnabled()) {
            AddButton.click();
            log.info("Add New button is present & enable");
        }
        
        //clicking on select file
        driver.findElement(By.id("plupload-browse-button")).click();
        log.info("Clicked select button");
//      Thread.sleep(3000);
        log.info("Uploading File");
        
        //put path to your file in a clipboard
        StringSelection ss = new StringSelection("\\eclipse-workspace\\miniProjectWordPress\\files\\pslLogo.png");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        
      
        // selecting the file
        Robot robot;
		try {
			robot = new Robot();
			robot.delay(1000);
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.delay(1000);
	        robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Robot class fail");
		}
        
        log.info("file is selected");
        Thread.sleep(2000);
		
        log.info("File uploaded successfully");
        
      //check the media file in library
       driver.findElement(By.linkText("Library")).click();
       log.info("Clicked library button");
       Thread.sleep(2000);
    
        
    }

  public static void main(String[] args)   {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");  
        WebDriver driver = new ChromeDriver();
   //     log.info("login started");
//		  System.setProperty("webdriver.edge.driver", "resources/msedgedriver.exe");  
//        WebDriver driver = new EdgeDriver();
        log.info("login started");
	     login l = new login();
	     l.loginwordpress(driver,log);
         Media m = new Media();
         try {
			m.viewMedia(driver,log);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         driver.quit();
         log.info("Test ended");
    }

 

}