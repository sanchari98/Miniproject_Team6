package pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class posts {
	static Logger log = Logger.getLogger(posts.class.getName());
	WebDriver driver;

	//finding Posts
	By PostsMenu = By.id("menu-posts");
	//Locating all post menu
	By AllPost = By.linkText("All Posts");
	//Locating add new page option
	By AddNew = By.linkText("Add New");
	//Locating categories menu
	By category = By.linkText("Categories");
	//Locating tag option
	By tags = By.linkText("Tags");
	//Locating Add new category button under category drop down
	By AddNewCategoryButton = By.xpath("//*[@id=\"editor\"]/div[1]/div[1]/div[2]/div[3]/div/div[3]/div[3]/button");

	By Toolscrollbar = By.xpath("//*[@id='editor']/div[1]/div[1]/div[2]/div[3]");
	//New category text area
	By NewCategoryNameField = By.xpath("//*[@id='editor-post-taxonomies__hierarchical-terms-input-0']");
	//drop down box in view post
	static By dropDown = By.id("bulk-action-selector-top");
	//apply button on view page
	static By applyButton = By.id("doaction");
	//bulk check box
	static By bulkSelector = By.id("cb-select-all-1");
	// no data on Table 
	By deleteConfirm = By.className("colspanchange");
	// table row
	static By tablerows = By.xpath("//*[@id='the-list']/tr");

	By visible = By.xpath("//*[@class='no-items']");


	public posts(WebDriver driver) {
		this.driver= driver;
	}


	public void postsFunctions () {

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(PostsMenu)).perform();
		log.info("mousehover in Posts button");

		// mouse hovering on list 
		action.moveToElement(driver.findElement(AllPost)).perform();
		action.moveToElement(driver.findElement(AddNew)).perform();
		action.moveToElement(driver.findElement(category)).perform();
		action.moveToElement(driver.findElement(tags)).perform();
		action.moveToElement(driver.findElement(AddNew)).perform();

		driver.findElement(AddNew).click();

		log.info("Toggling between button");
		Publish publish = new Publish(driver);
		publish.clickPublish();
	}


	public void viewPosts() {
		//finding Posts
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(PostsMenu)).perform();
		log.info("mousehover in Posts button");

		//finding all pages from posts dropdown
		log.info("All Posts button is present and enable");
		action.moveToElement(driver.findElement(AllPost)).build().perform();
		log.info("Move to All Posts button");
		driver.findElement(AllPost).click();
		log.info("Clicked all posts button");

	}


	public void CategoriesDuringPost()  {
		//Locating add new option
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(PostsMenu)).perform();
		log.info("mousehover in Posts button");
		driver.findElement(AddNew).click();

		log.info("clicked Add New button ");


		Publish publish = new Publish(driver);
		publish.clickCategoryDropDown();


		//Scroll down using SPACE BARz
		driver.findElement(Toolscrollbar).click();
		driver.findElement(Toolscrollbar).sendKeys(Keys.SPACE);



		//click on add new category button
		driver.findElement(AddNewCategoryButton).click();

		driver.findElement(NewCategoryNameField).click();
		driver.findElement(NewCategoryNameField).sendKeys(publish.NewCategoryName,Keys.ENTER);
		publish.clickCategoryDropDown();

		publish.clickPublish();

	}	 


	public void categoriesThenPost() {
		//Locating categories menu
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(PostsMenu)).perform();
		log.info("mousehover in Posts button");

		//		
		// clicked on category tab
		driver.findElement(category).click();


		CategogyPage CategogyPage = new CategogyPage(driver);

		//clic
		driver.findElement(AddNew).click();


		Publish publish = new Publish(driver);
		// opening category drop down 
		publish.clickCategoryDropDown();

		CategogyPage.selectCategory();
		// closing category drop down
		publish.clickCategoryDropDown();
		publish.clickPublish();

	}


	public static void delectPosts(WebDriver driver) {

		try{
			while(driver.findElement(bulkSelector).isDisplayed()) {
		

			WebElement drop = driver.findElement(dropDown);
			Select dropdown = new Select(drop);
			//				dropdown.selectByIndex(2); // index starts from 0
			dropdown.selectByVisibleText("Move to Trash");
			log.info("Selected Move to Trash from drop down");


			//all file click
			WebElement selectAllFile = driver.findElement(bulkSelector);
			if(selectAllFile.isDisplayed() && selectAllFile.isEnabled()) {
				log.info("selectAllFile checkbox is displayed and enabled");
				selectAllFile.click();
				log.info("clicked checkbox of file section");
			}


			//apply button
			WebElement Apply = driver.findElement(applyButton);
			if(Apply.isDisplayed() && Apply.isEnabled()) {
				log.info("Apply button is displayed and enabled");
				Apply.click();
				log.info("clicked Apply button for Delete Permanently");
			}
		}
		}catch(NoSuchElementException e) {
			log.info("All publishs are deleted");
		}
			



	}

}

