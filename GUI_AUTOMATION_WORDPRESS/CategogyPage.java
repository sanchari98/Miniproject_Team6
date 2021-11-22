package pages;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CategogyPage {

	static Logger log = Logger.getLogger(CategogyPage.class.getName());
	WebDriver driver;
	By CategoryName = By.id("tag-name");
	By AddNewcategory = By.id("submit");
	By CategoryOptions = By.xpath("//*[@class=\"components-panel__body is-opened\"]/div[1]/div/div/div/label");
	
	
	String categoryName=null;
	String csvFilename ="files/wordpress.csv";
	CSVReader csvRead = null;
	String[] row= null;
	
	
	public CategogyPage(WebDriver driver) {
		this.driver=driver;

		
		try {
			csvRead = new CSVReader(new FileReader(csvFilename));
			
			try {
				while((row= csvRead.readNext()) !=null)
				{
					// Title section
					categoryName = row[9];
					
					//sending category name from CSV file
					driver.findElement(CategoryName).sendKeys(categoryName);

					log.info("Entered category's Name 'Company'");
				}
			} catch (CsvValidationException e) {
				log.info("csv file problem");
			} catch (IOException e) {
				log.info("IOException occur");
			}
			
		} catch (FileNotFoundException e1) {
			log.info("csv file not found");
		}
		
		//clicking Add new category button
		driver.findElement(AddNewcategory).click();
		log.info("clicked on add new category");
	}

	public void selectCategory() {
		List<WebElement> check = driver.findElements(CategoryOptions);
		log.info("Reading all category options present on category");
		for(WebElement ele :check) {
			if(ele.getText().equals(categoryName)){
				if(!ele.isSelected()) {
					ele.click();
					log.info("Category selected");
					break;
				}
			}
		}
	}


}
