package pages;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class postsModulePage {

	static Logger log = Logger.getLogger(postsModulePage.class.getName());
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
	By Popup = By.cssSelector("body > div:nth-child(8) > div > div > div > div > div > div.components-modal__header > button > svg");
	//drop down box in view post
	static By dropDown = By.id("bulk-action-selector-top");
	//apply button on view page
	static By applyButton = By.id("doaction");
	//bulk check box
	static By bulkSelector = By.id("cb-select-all-1");


	public postsModulePage(WebDriver driver) {
		this.driver= driver;
	}
	
	public void postsFunctions () {
		
		//Locating posts menu
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(PostsMenu)).perform();
		log.info("mousehover in Posts button");

		// mouse hovering on list
		WebElement subMenu1 =driver.findElement(AllPost);
		if(subMenu1.isDisplayed() &&  subMenu1.isEnabled()) {
			action.moveToElement(subMenu1).perform();
		}
		WebElement subMenu2 =driver.findElement(AddNew);
		if(subMenu2.isDisplayed() &&  subMenu2.isEnabled()) {
			action.moveToElement(subMenu2).perform();
		}
		WebElement subMenu3 =driver.findElement(category);
		if(subMenu3.isDisplayed() &&  subMenu3.isEnabled()) {
			action.moveToElement(subMenu3).perform();
		}
		WebElement subMenu4 =driver.findElement(tags);
		if(subMenu4.isDisplayed() &&  subMenu4.isEnabled()) {
			action.moveToElement(subMenu4).perform();
		}
		log.info("Toggling between button");
		
		//finding add new and clicked
		action.moveToElement(driver.findElement(AddNew)).perform();
		WebElement add = driver.findElement(AddNew);
		if(add.isDisplayed() &&  add.isEnabled()) {
			log.info("Add new button is displayed and enabled");
			add.click();
			log.info("clicked add new button");
		}
		
		PublishPage publish = new PublishPage(driver);
		publish.clickPublish();
		
	}


	public void viewPosts() {
		//finding Posts
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(PostsMenu)).perform();
		log.info("mousehover in Posts button");


		//finding all pages from posts dropdown
		action.moveToElement(driver.findElement(AllPost)).build().perform();
		log.info("Move to All Posts button");
		driver.findElement(AllPost).click();
		log.info("Clicked all posts button");

	}


	public void CategoriesDuringPost() {
		//Locating add new option
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(PostsMenu)).perform();
		log.info("mousehover in Posts button");
		
				//finding add new and clicked
				action.moveToElement(driver.findElement(AddNew)).perform();
				WebElement add = driver.findElement(AddNew);
				if(add.isDisplayed() &&  add.isEnabled()) {
					log.info("Add new button is displayed and enabled");
					add.click();
					log.info("clicked add new button");
				}
				
				
		PublishPage publish = new PublishPage(driver);
		publish.clickCategoryDropDown();


		//Scroll down using SPACE BARz
		driver.findElement(Toolscrollbar).click();
		driver.findElement(Toolscrollbar).sendKeys(Keys.SPACE);
		log.info("scroll using space bar");

		//click on add new category button
		WebElement addncb = driver.findElement(AddNewCategoryButton);
		if(addncb.isDisplayed() &&  addncb.isEnabled()) {
			log.info("Add new category button is displayed and enabled");
			addncb.click();
			log.info("clicked add new category button");
		}
		
		
		driver.findElement(NewCategoryNameField).click();
		driver.findElement(NewCategoryNameField).sendKeys(publish.NewCategoryName,Keys.ENTER);
		log.info("category name taken named 'Human' ");
		
		
		publish.clickCategoryDropDown();
		publish.clickPublish();

	}

	
	public void categoriesThenPost() {
		//Locating categories menu
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(PostsMenu)).perform();
		log.info("mousehover in Posts button");

		//locating categories button
		WebElement c = driver.findElement(category);
		if(c.isDisplayed() &&  c.isEnabled()) {
			log.info("Add new category button is displayed and enabled");
			c.click();
			log.info("clicked add new category button");
		}

		
		CategoryPage CategoryPage = new CategoryPage(driver);
		driver.findElement(AddNew).click();


		PublishPage publish = new PublishPage(driver);
		publish.clickCategoryDropDown();

		CategoryPage.selectCategory();
		publish.clickCategoryDropDown();
		publish.clickPublish();

		}
	
	public static void deletePosts(WebDriver driver) {

		try{
		while(driver.findElement(bulkSelector).isDisplayed()) {

		WebElement drop = driver.findElement(dropDown);
		Select dropdown = new Select(drop);
		// dropdown.selectByIndex(2); // index starts from 0
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
