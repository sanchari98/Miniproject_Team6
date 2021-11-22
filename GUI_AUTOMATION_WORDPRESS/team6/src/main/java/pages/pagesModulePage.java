package pages;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class pagesModulePage {
	static Logger log = Logger.getLogger(pagesModulePage.class.getName());
	WebDriver driver;	
	By Pages = By.id("menu-pages");
	By AllPages = By.linkText("All Pages");
	By AddNew = By.linkText("Add New");
	By AddButton = By.xpath("//*[@id='menu-pages']/ul/li[3]/a");
	By Title = By.id("post-title-0");
	By Type = By.xpath("//div[@class='block-editor-block-list__layout is-root-container']/p");
	By publish = By.xpath("//*[@id=\"editor\"]/div[1]/div[1]/div[1]/div/div[2]/button[2]");
	By publish2 = By.xpath("//*[@id=\"editor\"]/div[1]/div[1]/div[2]/div[4]/div[2]/div/div/div[1]/div[1]/button");
	By view = By.linkText("View Page");
	By testsitemenu = By.xpath("//*[@id=\"wp-admin-bar-site-name\"]/a");
	By dash = By.linkText("Dashboard");



	public pagesModulePage(WebDriver driver) {
		this.driver=driver;
	}

	public void PagesAll() {

		//Locating pages menu
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(Pages)).perform();
		log.info("mousehover in  Pages button");

		//Locating all pages menu
		WebElement subMenu = driver.findElement(AllPages);
		//Locating add new page option
		WebElement subMenu2 = (driver.findElement(AddNew));

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
		WebElement add = driver.findElement(AddButton);
		if(add.isDisplayed() &&  add.isEnabled()) {
			log.info("Add new button is displayed and enabled");
			add.click();
			log.info("clicked add new button");
		}
	}	

	public void publish() {
		PublishPage pPages = new PublishPage(driver);
		pPages.clickPublish();
	}

	public void viewPages() {
		//Locating pages menu
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(Pages)).perform();
		log.info("mousehover in  Pages button");

		//Locating all pages menu
		WebElement subMenu = driver.findElement(AllPages);
		if(subMenu.isDisplayed() &&  subMenu.isEnabled()) {
			log.info("All page button is displayed and enabled");
			subMenu.click();
			log.info("clicked all pages button");
		}
	}
	
	

}




