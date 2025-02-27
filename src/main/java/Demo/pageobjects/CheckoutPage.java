package Jiochat.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Jiochat.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css ="[placeholder = 'Select Country']")
	WebElement country;
	
	
	@FindBy(css = ".action__submit")
	WebElement submit;
	
	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) throws InterruptedException
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
	
		
		
	}
	
	public confirmationPage submitOrder() throws InterruptedException
	{
		Thread.sleep(5000);
		submit.click();
		return new confirmationPage(driver);
		
	}
	
	
	
	
	
	
	
}
