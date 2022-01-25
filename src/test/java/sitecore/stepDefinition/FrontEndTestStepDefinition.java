package sitecore.stepDefinition;

import io.cucumber.java.en.*;
import io.cucumber.java.eo.Do;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FrontEndTestStepDefinition{

	public static WebDriver driver;


	public static final By GDPR_CLOSE = By.cssSelector("[data-gdpr-single-choice-accept='true']");

	public static final By SEARCH_INPUT = By.id("global-enhancements-search-query");
	public static final By SEARCH_BUTTON = By.cssSelector("[data-id='gnav-search-submit-button']");

	public static final By SORT_BY_ELEMENT = By.id("sortby");
	public static final By SORT_ASCENDING = By.cssSelector("[data-sort-by='price_asc']");


	public static final By APP_PRICES = By.xpath("//p[@class='wt-text-title-01']//span[@class='currency-value']");


	void setupBrowser() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--lang=en");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(chromeOptions);
	}

	void cleanBrowser() {

		driver.quit();
	}

	@Given("^User is at \"([^\"]*)\"$")
	public void user_is_at(String URL) throws Throwable {
		setupBrowser();
		driver.get(URL);

		driver.findElement(GDPR_CLOSE).click();


	}

	@When("^Search for \"([^\"]*)\"$")
	public void search_for(String searchText) throws Throwable {
		WebElement searchInput = driver.findElement(SEARCH_INPUT);
		WebElement searchButton = driver.findElement(SEARCH_BUTTON);

		searchInput.click();
		searchInput.sendKeys(searchText);

		searchButton.click();
	}

	@When("^Sort results by price in ascending$")
	public void sort_results_by_price_in_ascending() throws Throwable {
		driver.findElement(SORT_BY_ELEMENT).click();
		driver.findElement(SORT_ASCENDING).click();

	}

	@Then("^Validate Results are sorted$")
	public void validate_Results_are_sorted() throws Throwable {



	}

	@When("^Most expensive item is added to cart$")
	public void most_expensive_item_is_added_to_cart() throws Throwable {
		List<Double> prices = new ArrayList<>();

		List<WebElement> elements = driver.findElements(APP_PRICES);




		for (WebElement element : elements) {
			try {
				prices.add(Double.parseDouble(element.getText()));
			}
			catch (NumberFormatException e){
				// log that we have a problem
			}
		}

		Double maxPrice = Collections.max(prices);

		WebElement maxPriceElement = driver.findElement(By.xpath("//span[@class='currency-value' and contains(text(),'"+maxPrice.toString()+"')]"));
		maxPriceElement.click();


	}

	@Then("^Validate expected item$")
	public void validate_expected_item() throws Throwable {
		cleanBrowser();
	}
	
}
