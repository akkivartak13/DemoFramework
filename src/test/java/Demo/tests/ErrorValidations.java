package Jiochat.tests;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import Jiochat.TestComponents.BaseTest;
import Jiochat.pageobjects.CartPage;
import Jiochat.pageobjects.ProductCatalouge;

public class ErrorValidations extends BaseTest {
	
	@Test(groups = {"ErrorHandling"})
	public void LogInErrorvalidtion() throws InterruptedException
	{
		//String productName = "ZARA COAT 3";
	
		landingpage.loginApplication("testdemoautomation3@mailnesia.com", "Demo@Tes11t23");	
		Assert.assertEquals("Incorrect email password.", landingpage.getErrorMessage());
		
	}
	
	
	@Test
	public void ProductErrorValidation() throws InterruptedException
	{
		String productName = "ZARA COAT 3";			
		ProductCatalouge productcat = landingpage.loginApplication("testdemoautomation3@mailnesia.com", "Demo@Test23");	
		
		List<WebElement>products = productcat.getProductList();
		productcat.addProductToCart(productName);
		CartPage cartpage = productcat.goToCartPage();		
		Boolean match = cartpage.VerifyProductDisplay("ZARA COAT 3");
		AssertJUnit.assertTrue(match);
	}

}
