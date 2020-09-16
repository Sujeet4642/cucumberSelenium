package com.visionit.automation.stepdefs;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class StepDefs {
	
	WebDriver driver;
	String base_url = "https://amazon.in";
	 int implicit_wait_timeout_in_sec = 20;
	 
	@Given("User opened browser")
	public void user_opened_browser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(implicit_wait_timeout_in_sec, TimeUnit.SECONDS);
	   }

	@Given("User navigated to the home appilation url")
	public void user_navigated_to_the_home_appilation_url() {
		driver.get(base_url);
		String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String actual = driver.getTitle();
		Assert.assertEquals("Page Tile Valdation", expected, actual);
	   
	}
	@When("User search for product {string}")
	public void user_search_for_product(String productName) {
		 //Wait and Search for product
	    WebDriverWait wait = new WebDriverWait(driver,20);
	    WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));
	    
	    searchBox.sendKeys(productName);
	    driver.findElement(By.xpath("//input[@value='Go']")).click();
	}
	@Then("Search result is displayed")
	public void search_result_is_displayed() {
		WebDriverWait wait1 = new WebDriverWait(driver,20);
		wait1.until(ExpectedConditions.titleIs("Amazon.in : laptop"));
		Assert.assertEquals("Page Title Validation", "Amazon.in : laptop", driver.getTitle());
	    
	}

	@When("User click on anyn product")
	public void user_click_on_anyn_product() {
	 List<WebElement> listOfProducts = driver.findElements(By.xpath("//a[@class='a-link-normal a-text-normal']"));
	 listOfProducts.get(0).click();
	}


	@Then("Product Description is displayed in new tab")
	public void product_description_is_displayed_in_new_tab() {
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> It = handles.iterator(); 
		String Orginal = It.next();
		String prodDesc = It.next();
		 driver.switchTo().window(prodDesc);
		 
		 WebElement clickedproductTitle = driver.findElement(By.id("productTitle"));
		 Assert.assertEquals("Product Title", true, clickedproductTitle.isDisplayed());
		 
		 WebElement addToCart = driver.findElement(By.id("add-to-cart-button"));
		 Assert.assertEquals("Add To Cartrt Button", true, addToCart.isDisplayed());
		 
		 driver.switchTo().window(Orginal);
		 
	}

}
