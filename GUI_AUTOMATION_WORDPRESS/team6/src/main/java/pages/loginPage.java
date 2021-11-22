package pages;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class loginPage {
	static Logger log = Logger.getLogger(loginPage.class.getName());
	By userName = By.id("user_login");
	By passWord = By.id("user_pass");
	By loginButton = By.id("wp-submit");
	By dashboard = By.className("wp-menu-name");
	WebDriver driver;
	String expectedTitle= "Dashboard ‹ testsite — WordPress";


	public loginPage(WebDriver driver) {
		this.driver=driver;
	}

	public void loginWordPress(int i, int j) {
		String csvFilename ="files/wordpress.csv";
		String username=null;
		String password=null;
		CSVReader csvRead=null;
		String[] row= null;
		{
		
	    try {
            csvRead = new CSVReader(new FileReader(csvFilename));
        } catch (FileNotFoundException e1) {
            log.info(e1);
        }
	    
	    try {
	    	
		while((row= csvRead.readNext()) !=null)
		{
			username= row[i];
			password= row[j];
		}
	 }
	    catch(IOException e) {
            log.info("File does not read "+e);
        } catch (CsvValidationException e) {
            
            e.printStackTrace();
        }
	}
		
		//username field
		WebElement userN= driver.findElement(userName);
		if(userN.isDisplayed() &&  userN.isEnabled())
        {	
		log.info("username field is present and enable");
		userN.sendKeys(username);
		log.info("Username "+username);
        }
		
		//password field
		WebElement pass= driver.findElement(passWord);
		if(pass.isDisplayed() &&  pass.isEnabled())
        {	
		log.info("password field is present and enable");
		pass.sendKeys(password);
		log.info("Password "+password);
        }
		
		//login button 
		WebElement submit = driver.findElement(loginButton);
		if(submit.isDisplayed() &&  submit.isEnabled())
        {	
		log.info("Log In button present and enable");
		submit.click();
		log.info("Log In button clicked");
        }
		
		if(driver.getTitle().equals(expectedTitle)) {
			log.info("Log In successful" +driver.getTitle());
			}
			else {
			log.info("Log In unsuccessful"+driver.getTitle());
			}
		
		 //clicked dashboard
			WebElement dash = driver.findElement(dashboard);
			if(dash.isDisplayed() &&  dash.isEnabled())
				log.info("Dashboard button present and enable");
				dash.click();
				log.info("Clicked Dashboard");
}
}