package Tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import Utils.DataReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import pageObjects.CartPage;
import pageObjects.LandingPage;
import pageObjects.LoginPage;

public class viewAndVerifyCartItemsTest extends BaseTest{

	WebDriverWait wait;
	String totalPrice;

	
	@Test(dataProvider = "getData")
	public void viewAndVerifyCart(HashMap<String,String> input) throws IOException, InterruptedException {

		LoginPage lp = new LoginPage(driver);
		lp.LoginApp(input.get("username"), input.get("password"));
	
		wait = new WebDriverWait(driver, null);
		
		wait.until(ExpectedConditions.alertIsPresent());
		Thread.sleep(5);
		driver.switchTo().alert().accept();

		LandingPage landingPage = new LandingPage(driver);
		//Verify the prescence of inventory list
		landingPage.waitForInventoryListToBeAppear();
				
		String firstItemName = landingPage.firstItemName();
		String secondItemName = landingPage.secondItemName();
		String item1Price = landingPage.firstItemPrice();
		String item2Price = landingPage.secondItemPrice();
		totalPrice = landingPage.totPriceOfItemsSelected();
		
		//Adding first two intems to the cart
		landingPage.addItemsToCart();
		
		CartPage cartPage = new CartPage(driver);
		//click on shopping cart
		cartPage.clickOnShoppingCart();
			
		//verifying cart list has 2 items
		cartPage.verifyNoOfItemsOnCartList();
		
		//Verifying items name and price are corrent on the cart
		Assert.assertEquals(firstItemName, "Sauce Labs Backpack");
		Assert.assertEquals(item1Price, "$29.99");
		
		Assert.assertEquals(secondItemName, "Sauce Labs Bike Light");
		Assert.assertEquals(item2Price, "$9.99");
		
	}
	
	
		
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		DataReader dr = new DataReader();
		List<HashMap<String, String>> data= dr.getJsonDataToMap(System.getProperty("user.dir")+"\\src\\main\\java\\TestData\\LoginData.json");
		
		return new Object[][] {{data.get(0)}};
	}
	
}
