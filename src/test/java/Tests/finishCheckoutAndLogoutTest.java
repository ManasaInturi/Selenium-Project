package Tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import Utils.DataReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.LogoutPage;

public class finishCheckoutAndLogoutTest extends BaseTest{

	WebDriverWait wait;
	String totalPrice;

	
	@Test(dataProvider = "getData")
	public void FinishAndLogout(HashMap<String,String> input) throws IOException, InterruptedException {

		LoginPage lp = new LoginPage(driver);
		lp.LoginApp(input.get("username"), input.get("password"));
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
//		wait.until(ExpectedConditions.alertIsPresent());
//		driver.switchTo().alert().accept();
		
		boolean presentFlag = false;

		  try {

		   // Check the presence of alert
		   Alert alert = driver.switchTo().alert();
		   // Alert present; set the flag
		   presentFlag = true;
		   // if present consume the alert
		   alert.accept();

		  } catch (NoAlertPresentException ex) {
		   // Alert not present
		   ex.printStackTrace();
		  }

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
		
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		//click on Checkout
		checkoutPage.clickOnCheckoutBtn();
		
		//Enter details on the checkout page and click on continue
		checkoutPage.enterDetailsAndContinue();
		
		//continue to checkout overview page
		
		checkoutPage.waitForOverviewPage();
		
		//Verify correct items are listed in overview
		String firstItem = driver.findElement(By.xpath("(//div[normalize-space()='Sauce Labs Backpack'])[1]")).getText();
		Assert.assertEquals(firstItem, firstItemName);
		
		String secondItem = driver.findElement(By.xpath("(//div[normalize-space()='Sauce Labs Bike Light'])[1]")).getText();
		Assert.assertEquals(secondItem, secondItemName);
		
		//very the total price is correct
		
		String Price = driver.findElement(By.xpath("(//div[@class='summary_subtotal_label'])[1]")).getText().split("$")[1];
		Assert.assertEquals(totalPrice, Price);
		
		//Click on Finish
		
		LogoutPage logoutPage = new LogoutPage(driver);
		logoutPage.clickOnFinish();
			
		//Validate the checkout complete and successful text and logout
		
		WebElement checkoutComplete = driver.findElement(By.xpath("(//span[@class='title'])[1]"));
		wait.until(ExpectedConditions.visibilityOf(checkoutComplete));
		Assert.assertEquals(checkoutComplete.getText(), "Checkout: Complete!");
		
		driver.findElement(By.xpath("(//h2[normalize-space()='Thank you for your order!'])[1]")).getText().equalsIgnoreCase("Thank you for your order!");
		
		driver.findElement(By.xpath("(//button[normalize-space()='Back Home'])[1]")).click();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//div[@class='inventory_list'])[1]"))));		
		driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
		// Logout
		logoutPage.logout();
		
		//Verify it landed on Login page
		logoutPage.verifyLoginPageAppears();
		
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		DataReader dr = new DataReader();
		List<HashMap<String, String>> data= dr.getJsonDataToMap(System.getProperty("user.dir")+"\\src\\main\\java\\TestData\\LoginData.json");
		
		return new Object[][] {{data.get(0)}};
	}
	
}
