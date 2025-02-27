package Jiochat.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Jiochat.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	 WebDriver driver;
	
	public LandingPage(WebDriver driver)

	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	


	@FindBy(id="userEmail")
	WebElement userEmail;
	
	
	@FindBy(id="userPassword")
	WebElement Password1;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalouge loginApplication(String email, String passwrod)
	{
		userEmail.sendKeys(email);
		Password1.sendKeys(passwrod);
		submit.click();
		ProductCatalouge productcat = new ProductCatalouge(driver);
		return productcat;
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}

}
