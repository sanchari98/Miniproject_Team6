package pages;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

public class Media {
	WebDriver driver;
	static Logger log = Logger.getLogger(Media.class.getName());
	By mediaButton = By.xpath("//div[contains(text(),'Media')]");
	By Library = By.xpath("//a[contains(text(),'Library')]"); 
	By AddMenuMedia = By.xpath("//body/div[@id='wpwrap']/div[@id='adminmenumain']/div[@id='adminmenuwrap']/ul[@id='adminmenu']/li[@id='menu-media']/ul[1]/li[3]/a[1]");
	By AddButton = By.xpath("//*[@id='menu-media']/ul/li[3]/a");
	By SelectFile = By.id("plupload-browse-button");
	By gridview = By.id("view-switch-grid");
	By listview = By.className("view-list");

	By dropdown = By.id("bulk-action-selector-top");
	By selectFile = By.id("cb-select-all-1");
	By applyButton = By.id("doaction");
	By tableText = By.className("colspanchange");
	
	String deleteconfirm = "No media files found.";

	public Media(WebDriver driver) {
		this.driver=driver;
	}

	public void upload() {

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(mediaButton)).perform();
		log.info("mousehover in media button");


		//Locating Library menu
		WebElement subMenu = driver.findElement(Library);
		//Locating add new media option
		WebElement subMenu2 = (driver.findElement(AddMenuMedia));


		if(subMenu.isDisplayed() && subMenu.isEnabled()) {
			action.moveToElement(subMenu).perform();


		}
		if(subMenu2.isDisplayed() && subMenu2.isEnabled()) {
			action.moveToElement(subMenu2).perform();


		}
		if(subMenu.isDisplayed() && subMenu.isEnabled()) {
			action.moveToElement(subMenu).perform();


		}
		log.info("Toggling between button");


		//Locating add new option
		WebElement add = driver.findElement(AddMenuMedia);
		if(add.isDisplayed() && add.isEnabled()) {
			log.info("Add new option is displayed and enabled");
			add.click();
			log.info("clicked add new option");
		}


		// clicking in add new
		WebElement addButton = driver.findElement(AddButton);
		if(addButton.isDisplayed() && addButton.isEnabled()) {
			log.info("Add new option is displayed and enabled");
			addButton.click();
			log.info("clicked add new option");
		}
	}


	public void Selectfile() {
		//select file button
		WebElement select = driver.findElement(SelectFile);
		if(select.isDisplayed() && select.isEnabled()) {
			log.info("select file button is displayed and enabled");
			select.click();
			log.info("clicked select file button");
			log.info("Uploading File");
		}

		//choose file
		StringSelection ss = new StringSelection("eclipse-workspace\\WordPress\\files\\pslLogo.png");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


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
			log.info("file is selected");
			robot.delay(1000);
			robot.keyRelease(KeyEvent.VK_ENTER);
			log.info("File uploaded successfully");
			robot.delay(1000);
		}
		catch (AWTException e)
		{
			e.printStackTrace();
		}


		//click library button to see uploaded file
		WebElement lib = driver.findElement(Library);
		if(lib.isDisplayed() && lib.isEnabled()) {
			log.info("library button option displayed and enabled");
			lib.click();
			log.info("clicked library button");
		}

		try {
			//grid view at library(By default it shows in list format)
			WebElement grid = driver.findElement(gridview);
			if(grid.isDisplayed() && grid.isEnabled()) {
				log.info("grid view button option displayed and enabled");
				grid.click();
				log.info("clicked grid view button");
			}
		}catch(NoSuchElementException e) {
			log.info("already in grid view");
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


		try{
			//list view at library(By default it shows in list format)
			WebElement list = driver.findElement(listview);
			if(list.isDisplayed() && list.isEnabled()) {
				log.info("grid view button option displayed and enabled");
				list.click();
				log.info("clicked grid view button");
			}
		}catch(NoSuchElementException e) {
			log.info("Already in list view");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	public void deleteMedia() {
		try{
			//list view at library(By default it shows in list format)
			WebElement list = driver.findElement(listview);
			if(list.isDisplayed() && list.isEnabled()) {
				log.info("grid view button option displayed and enabled");
				list.click();
				log.info("clicked grid view button");
			}
		}catch(NoSuchElementException e) {
			log.info("Already in list view");
		}

		//all file click
		WebElement selectAllFile = driver.findElement(selectFile);
		if(selectAllFile.isDisplayed() && selectAllFile.isEnabled()) {
			log.info("selectAllFile checkbox is displayed and enabled");
			selectAllFile.click();
			log.info("clicked checkbox of file section");
		}

		//drop down selection
		WebElement drop = driver.findElement(dropdown);
		Select dropdown = new Select(drop);
//		dropdown.selectByIndex(1); // index starts from 0
		dropdown.selectByVisibleText("Delete permanently");
		log.info("Selected Delete permanently from drop down");


		//apply button
		WebElement Apply = driver.findElement(applyButton);
		if(Apply.isDisplayed() && Apply.isEnabled()) {
			log.info("Apply button is displayed and enabled");
			Apply.click();
			log.info("clicked Apply button for Delete Permanently");
		}
		
		driver.switchTo().alert().accept();
		
		
		if(driver.findElement(tableText).getText().equals(deleteconfirm)) {
			log.info("Successfully deleted all media post");
		}
		

	}

}