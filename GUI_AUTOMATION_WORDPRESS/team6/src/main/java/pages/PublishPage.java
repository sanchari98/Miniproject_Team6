package pages;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class PublishPage {
	static Logger log = Logger.getLogger(PublishPage.class.getName());
	WebDriver driver;
	By Popup = By.cssSelector("body > div:nth-child(8) > div > div > div > div > div > div.components-modal__header > button > svg");
	By Title = By.id("post-title-0");
	By block = By.xpath("//div[@class='block-editor-block-list__layout is-root-container']/p");
	By publish1 = By.xpath("//*[@id='editor']/div[1]/div/div[1]/div/div[2]/button[2]");
	By publish2 = By.xpath("//*[@id=\'editor\']/div[1]/div/div[2]/div[4]/div[2]/div/div/div[1]/div[1]/button");
	By view = By.xpath("//*[@id=\"editor\"]/div[1]/div/div[2]/div[4]/div[2]/div/div/div[2]/div/div[2]/div[2]/a");
	By testsitemenu = By.xpath("//*[@id=\"wp-admin-bar-site-name\"]/a");
	By PostTag = By.xpath("//*[@id='editor']/div[1]/div[1]/div[2]/div[3]/div/div[2]/ul/li[1]/button");
	By CategoryDropDown = By.xpath("//*[@id=\"editor\"]/div[1]/div[1]/div[2]/div[3]/div/div[3]/div[3]/h2/button");


	//read input from .csv file
	String csvFilename ="files/wordpress.csv";
	String addtitle =null;
	String blockText = null;
	String NewCategoryName = null;
	String[] row = null;
	CSVReader csvRead =null;


	public PublishPage(WebDriver driver) {
		this.driver= driver;
		
		try {
			// MINI-window/pop up handle by click cross button
			driver.findElement(Popup).click();
			log.info("Pop up is opened");
			
		}	
		catch(NoSuchElementException e) {
			log.info("Pop up is not opened");
		}

		try{
			csvRead= new CSVReader(new FileReader(csvFilename));
			try {
				while((row= csvRead.readNext()) !=null)
				{
					//Title section
					addtitle= row[3];
					driver.findElement(Title).sendKeys(addtitle,Keys.TAB);
					log.info("Entered Title 'TEST'");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);



					// Type/choose a box
					blockText= row[4];
					driver.findElement(block).sendKeys(blockText);
					log.info("Entered block 'TESTSITE'");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					// category name during post
					NewCategoryName = row[10];
				}
			}catch (CsvValidationException e) {
				log.info("csv file problem");
			}catch (IOException e) {
				log.info("IOException occur");
			}
		}
		catch(FileNotFoundException e) {
			log.info("csv file is not present");
		}

		driver.findElement(PostTag).click();
		log.info("clicked on post tag");
	}




	public void clickCategoryDropDown() {
		driver.findElement(CategoryDropDown).click();
		log.info("clicked on Category drop down");
	}


	public void clickPublish() {
		// clicking on publish button
		WebElement publish = driver.findElement(publish1);
		if(publish.isDisplayed() && publish.isEnabled()) {
			log.info("publish button is present and enable");
			publish.click();
			log.info("Clicked publish button");
		}


		// 2nd time click on publish button
		WebElement publishh = driver.findElement(publish2);
		if(publishh.isDisplayed() && publishh.isEnabled()) {
			log.info("publish button is present and enable");
			publishh.click();
			log.info("Second time Clicked publish button");
		}


		//view recent page by linkText
		WebElement viewPost = driver.findElement(view);
		if(viewPost.isDisplayed() && viewPost.isEnabled()) {
			log.info("view button is present and enable");
			viewPost.click();
			log.info("Clicked view post button");
		}


		JavascriptExecutor js = (JavascriptExecutor) driver;
		// This will scroll down the page by 200 pixel vertical
		js.executeScript("window.scrollBy(0,200)");
		log.info("Window scroll by 200 pixel vertical");



		//Mouse Hover on testsite menu
		WebElement tsmenu = driver.findElement(testsitemenu);
		if(tsmenu.isDisplayed() && tsmenu.isEnabled()) {
			log.info("testsite button is present and enable");
			tsmenu.click();
			log.info("Clicked testsite button");


		}
	}



}