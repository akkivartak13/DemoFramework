package Jiochat.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Jiochat.TestComponents.BaseTest;
import Jiochat.pageobjects.CartPage;
import Jiochat.pageobjects.CheckoutPage;
import Jiochat.pageobjects.LandingPage;
import Jiochat.pageobjects.OrderPage;
import Jiochat.pageobjects.ProductCatalouge;
import Jiochat.pageobjects.confirmationPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void submitOrder(HashMap <String, String> input) throws InterruptedException, IOException {		
			
		ProductCatalouge productcat = landingpage.loginApplication(input.get("email"), input.get("password"));		
		List<WebElement>products = productcat.getProductList();
		productcat.addProductToCart(input.get("product"));
		CartPage cartpage = productcat.goToCartPage();		
		Boolean match = cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.goToCheckOut();
		checkoutpage.selectCountry("india");
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(350,450)");
		confirmationPage confirmationpage = checkoutpage.submitOrder();
		String confirmMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
			
		
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	
	public void OrderHistoryTest()
	{
		ProductCatalouge productcat = landingpage.loginApplication("testdemoautomation3@mailnesia.com", "Demo@Test23");		
		OrderPage orderpage = productcat.goToOrderPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(productName));
	}
	

	
	@DataProvider
	public Object[][] getData() throws IOException
	{
			
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//Jiochat//data//PurchaseOrder.json");
		return new Object [][] {{data.get(0)},{data.get(1)}};
	}
	
	
	/*
	 * HashMap <String, String> map = new HashMap<String, String>();
	 * map.put("email", "testdemoautomation3@mailnesia.com"); map.put("password",
	 * "Demo@Test23"); map.put("product", "ZARA COAT 3");
	 * 
	 * HashMap <String, String> map1 = new HashMap<String, String>();
	 * map1.put("email", "testdemoautomation3@mailnesia.com"); map1.put("password",
	 * "Demo@Test23"); map1.put("product", "ADIDAS ORIGINAL");
	 */
	
	
	

}
